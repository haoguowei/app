Ext.onReady(function(){
	Ext.QuickTips.init();
	
	var menuId = document.getElementById("menuId").value;
	var title = '《' + document.getElementById("menuName").value + '》的权限';
	
	//-----------------权限相关 start-----------
	var urlSavePrivilege = "savePrivilege.do";
	var urlDelPrivilege = "deletePrivilege.do";
	
	//是否有权限
	var isPRISavePrivilege = isHavePRI(urlSavePrivilege);//编辑菜单下的权限
	var isPRIDelPrivilege = isHavePRI(urlDelPrivilege); //删除菜单下的权限

	//-----------------权限相关 end-----------
	
	
	//编辑权限窗口
	var pWindow = new com.custom.Window({
		width : 460,
		height : 220,
		contentEl : 'pWindow',
		buttons : [{
			text : '保存',
			handler : function(){
				var pName = document.getElementById("pName").value;
				var pUrl = document.getElementById("pUrl").value;
				
				if (_isNull(pName)) {
					alert("请填写权限名！");
					document.getElementById("pName").focus();
					return false;
				}
				if (!_regInput(pName)) {
					alert("权限名含有非法字符！");
					document.getElementById("pName").focus();
					return false;
				}
				
				if (_isNull(pUrl)) {
					alert("请填写权限URL！");
					document.getElementById("pUrl").focus();
					return false;
				}
				
				if (!regexVerify('letter2',pUrl)) {
					alert("权限URL含有非法字符！");
					document.getElementById("pUrl").focus();
					return false;
				}
				
				
				var url = urlSavePrivilege + "?pId=" + document.getElementById("hidPriId").value
					+"&menuId=" + menuId
					+"&pName=" + pName
					+"&pUrl=" + pUrl;
					
				Ext.Ajax.request( {
					url : url,
					success : function(response){
						var resp = Ext.util.JSON.decode(response.responseText);
						if(resp.success){
							alert("操作成功！");
							pWindow.hide();
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
				pWindow.hide();
			}
		}]
	});

	this.reset = function(){
		document.getElementById("hidPriId").value = 0;
		document.getElementById("pName").value = '';
		document.getElementById("pUrl").value = '';
	};
		
	//查询
	this.searchFunc = function() {
		gridStore.load();
	};
	
	//新增
	this.addF = function(bt) {
		reset();
		pWindow.setTitle("新增权限");
		pWindow.show(bt);
	};
	
	//更新
	this.updateF = function(bt,privilegeId) {
		Ext.Ajax.request( {
			url : 'getPrivilegeById.do?privilegeId=' + privilegeId,
			success : function(response){
				var resp = Ext.util.JSON.decode(response.responseText);
				reset();
				
				document.getElementById("hidPriId").value = resp.id;
				document.getElementById("pName").value = resp.name;
				document.getElementById("pUrl").value = resp.url;
				
				pWindow.setTitle("编辑权限:" + resp.id + "-" + resp.name);
				pWindow.show(bt);
			}
		});
	};
	
	//删除
	this.deleteF = function(bt, privilegeId) {
		if (confirm("确定要删除该权限吗？")) {
			Ext.Ajax.request({
				url : urlDelPrivilege + '?privilegeId=' + privilegeId,
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

	// 列表数据
	var gridStore = new Ext.data.JsonStore({
		url : 'searchPrivileges.do?menuId=' +( _isNull(menuId) ? 0 : menuId),
		root : 'resultList',
		remoteSort : false,
		totalProperty : 'total',
		fields : [ {
			name : 'id'
		}, {
			name : 'name'
		}, {
			name : 'url'
		} ]
	});
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{header:'权限名称', align:'left',sortable:false, dataIndex:'name'},
				{header:'权限URL', align:'left',sortable:false, dataIndex:'url'},
				{header:'操作', align:'left',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					var str = '';
					if(isPRISavePrivilege){ //权限
						str += genButton("修改","updateF(this,"+val+")");
					}
					if(isPRIDelPrivilege){ //权限
						str += genButton("删除","deleteF(this,"+val+")");
					}
					return str;
				}}
				]
	}); 


		
	new Ext.Viewport({
		layout : 'border',
		items : [ {
			title : title,
			region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [ grid ],
			tbar : [ {
				text : '新增权限',
				id:'bt_add',
				handler : function(b, e) {
					addF(this);
				}
			} ]
		} ]
	});
	
	if(!isPRISavePrivilege){//权限
		Ext.getCmp("bt_add").hide();  
	}

	searchFunc();
});