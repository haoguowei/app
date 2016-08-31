Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var menuWindow = new com.custom.Window({
		width : 460,
		height : 250,
		contentEl : 'menuWindow',
		buttons : [{
			text : '保存',
			handler : function(){
				var url = "saveMenu.do?menuId="+$("hidMenuId").value
					+"&menuParentId="+$("hidMenuParentId").value
					+"&menuName="+$("menuName").value
					+"&menuSort=" + $("menuSort").value
					+"&menuUrl="+$("menuUrl").value;
					
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
	

	this.searchFunc = function() {
		gridStore.load();
	};
	
	this.reset = function(){
		$("hidMenuId").value = 0;
		$("hidMenuParentId").value = '';
		$("menuName").value = '';
		$("menuUrl").value = '';
		$("menuSort").value = '';
	};
	
	this.setWindowValue = function(obj){
		reset();
		$("hidMenuId").value = obj.id;
		$("hidMenuParentId").value = obj.parent;
		$("menuName").value = obj.name;
		$("menuUrl").value = obj.url;
		$("menuSort").value = obj.sort;
	};

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
	
	this.addF = function(bt,parentId) {
		reset();
		$("hidMenuId").value = 0;
		$("hidMenuParentId").value = parentId;
		
		menuWindow.setTitle("新增菜单");
		menuWindow.show(bt);
	};
	
	this.toPrivileges = function(menuId) {
		location.href = "initPrivileges.do?menuId=" + menuId;
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
						return '<b>##### '+val+' #####</b>'
					}
					return val;
				}},
				{header:'菜单URL', align:'left',sortable:false, dataIndex:'url'},
				{header:'排序', align:'center',sortable:false, dataIndex:'sort'},
				{header:'操作', align:'left',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					var str = '';
					if(record.data.parent == 0){
						str += genButton("修改","updateF(this,"+val+")");
						str += genButton("新增子菜单","addF(this, "+val+")");
					}else{
						str += genButton("修改","updateF(this,"+val+")");
						str += genButton("菜单权限","toPrivileges("+val+")");
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
				handler : function(b, e) {
					addF(this, 0);
				}
			} ]
		} ]
	});
	
	searchFunc();
});