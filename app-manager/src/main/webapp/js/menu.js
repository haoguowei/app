Ext.onReady(function(){
	Ext.QuickTips.init();
	
	//-----------------权限相关 start-----------
	var urlSaveMenu = "saveMenu.do";
	var urlDelMenu = "deleteMenu.do";
	var urlInitPrivileges = "initPrivileges.do";
	
	///////////  是否有权限  ///////////
	var isPRISaveMenu = isHavePRI(urlSaveMenu);//新增编辑菜单功能
	var isPRIDelMenu = isHavePRI(urlDelMenu); //删除菜单功能
	var isPRIInitPrivileges = isHavePRI(urlInitPrivileges); //访问菜单的权限页
	
	//-----------------权限相关 end-----------
	
	
	var menuWindow = new com.custom.Window({
		width : 460,
		height : 250,
		contentEl : 'menuWindow',
		buttons : [{
			text : '保存',
			handler : function(){
				var menuName = document.getElementById("menuName").value;
				var menuUrl = document.getElementById("menuUrl").value;
				var menuSort = document.getElementById("menuSort").value;
				
				if (_isNull(menuName)) {
					alert("请填写菜单名！");
					document.getElementById("menuName").focus();
					return false;
				}
				if (!_regInput(menuName)) {
					alert("菜单名含有非法字符！");
					document.getElementById("menuName").focus();
					return false;
				}
				
				if (_isNull(menuUrl)) {
					alert("请填写菜单URL！");
					document.getElementById("menuUrl").focus();
					return false;
				}
				
				if(document.getElementById("hidMenuParentId").value != 0){
					if (menuUrl != null && !regexVerify('letter2',menuUrl)) {
						alert("菜单URL含有非法字符！");
						document.getElementById("menuUrl").focus();
						return false;
					}
				}else{
					menuUrl = '';
				}
				
				if (_isNull(menuSort)) {
					alert("请填写菜单排序！");
					document.getElementById("menuSort").focus();
					return false;
				}
				if (!regexVerify('num1',menuSort)) {
					alert("菜单排序只能使用数字！");
					document.getElementById("menuSort").focus();
					return false;
				}
				
				var url = urlSaveMenu + "?menuId=" + document.getElementById("hidMenuId").value
					+"&menuParentId=" + document.getElementById("hidMenuParentId").value
					+"&menuName=" + menuName
					+"&menuSort=" + menuSort
					+"&menuUrl=" + menuUrl;
					
				Ext.Ajax.request( {
					url : url,
					success : function(response){
						var resp = Ext.util.JSON.decode(response.responseText);
						if(resp.success){
							alert("操作成功！");
							menuWindow.hide();
							searchFunc();
						}else{
							alert("操作失败：" + resp.resultTipMsg);
						}
					}
				});
			}
		},{
			text : '关闭',
			handler : function(){
				menuWindow.hide();
			}
		}]
	});
	
	//编辑一级菜单时，只读
	menuWindow.on("beforeshow",function(){
		if(document.getElementById("hidMenuParentId").value == 0){
			document.getElementById("menuUrl").value = '#';
			document.getElementById("menuUrl").readOnly = true;
		}else{
			document.getElementById("menuUrl").readOnly = false;
		}
    });
	
	this.reset = function(){
		document.getElementById("hidMenuId").value = 0;
		document.getElementById("hidMenuParentId").value = '';
		document.getElementById("menuName").value = '';
		document.getElementById("menuUrl").value = '';
		document.getElementById("menuSort").value = '';
	};
	
	this.setWindowValue = function(obj){
		reset();
		document.getElementById("hidMenuId").value = obj.id;
		document.getElementById("hidMenuParentId").value = obj.parent;
		document.getElementById("menuName").value = obj.name;
		document.getElementById("menuUrl").value = obj.url;
		document.getElementById("menuSort").value = obj.sort;
	};
	
	//查询
	this.searchFunc = function() {
		gridStore.load();
	};

	//更新
	this.updateF = function(bt,menuId) {
		Ext.Ajax.request( {
			url : 'getMenuById.do?menuId=' + menuId,
			success : function(response){
				var resp = Ext.util.JSON.decode(response.responseText);
				setWindowValue(resp);
				menuWindow.setTitle("编辑菜单:" + resp.id + "-" + resp.name);
				menuWindow.show(bt);
			}
		});
	};
	
	//新增
	this.addF = function(bt,parentId) {
		reset();
		document.getElementById("hidMenuId").value = 0;
		document.getElementById("hidMenuParentId").value = parentId;
		
		menuWindow.setTitle("新增菜单");
		menuWindow.show(bt);
	};
	
	//删除
	this.deleteF = function(bt,menuId) {
		if (confirm("确定要删除该菜单吗？其子菜单及权限也将被删除！")) {
			Ext.Ajax.request({
				url : urlDelMenu + '?menuId=' + menuId,
				success : function(response) {
					var resp = Ext.util.JSON.decode(response.responseText);
					if (resp.success) {
						alert("操作成功！");
						searchFunc();
					} else {
						alert("操作失败：" + resp.resultTipMsg);
					}
				}
			});
		}
	};
	
	this.toPrivileges = function(menuId) {
		location.href = urlInitPrivileges + "?menuId=" + menuId;
	};
	
	//列表数据
	var gridStore = new Ext.data.JsonStore({
		url : 'searchMenus.do',
		root : 'resultList',
		remoteSort : false,
		totalProperty : 'total',
		fields : [ {
			name : 'id'
		}, {
			name : 'parent'
		}, {
			name : 'name'
		}, {
			name : 'url'
		}, {
			name : 'sort'
		} ]
	});
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		viewConfig:{
			forceFit: true, //宽度自适应
			getRowClass:function(record,rowIndex,rowParams,store){
				if(record.data.parent == 0){
					return "row-color-yellow"; 
				}
			}
		},
		columns: [ 
		        {header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{header:'菜单', align:'left',sortable:false, dataIndex:'name',renderer:function(val,cell,record){
					if(record.data.parent == 0){
						return '<b>=== '+val+' ===</b>'
					}
					return val;
				}},
				{header:'菜单URL', align:'left',sortable:false, dataIndex:'url'},
				{header:'排序', align:'center',sortable:false, dataIndex:'sort'},
				{header:'操作', align:'left',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					var str = '';
					if(isPRISaveMenu){//权限
						str += genButton("修改","updateF(this,"+val+")");
					}
					if(record.data.parent == 0){
						if(isPRIDelMenu){//权限
							str += genButton("删除菜单及子菜单","deleteF(this,"+val+")");
						}
						if(isPRISaveMenu){//权限
							str += genButton("新增子菜单","addF(this, "+val+")");
						}
					}else{
						if(isPRIDelMenu){//权限
							str += genButton("删除","deleteF(this,"+val+")");
						}
						if(isPRIInitPrivileges){//权限
							str += genButton("菜单权限","toPrivileges("+val+")");
						}
					}
					return str;
				}}
		]
	}); 
	


	new Ext.Viewport({
		layout : 'border',
		items : [ {
			title : '菜单管理',
			region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [ grid ],
			tbar : [ {
				text : '新增一级菜单',
				id : 'bt_add',
				handler : function(b, e) {
					addF(this, 0);
				}
			} ]
		} ]
	});
	
	if(!isPRISaveMenu){//权限
		Ext.getCmp("bt_add").hide();  
	}
	
	searchFunc();
});