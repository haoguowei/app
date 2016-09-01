Ext.onReady(function(){
	Ext.QuickTips.init();

	//查询
	this.searchFunc = function() {
		gridStore.load();
	};
	
	//定义窗口
	var win = new WindowPrivileges("treeDiv");
	
	//分配权限
	this.setRolePrivileges = function(bt,roleId) {
		win.show(bt);
	};
	
	
	//列表数据
	var gridStore = new Ext.data.JsonStore({
	    url : 'searchRoles.do',
	    root : 'resultList',
	    remoteSort: false, 
	    totalProperty:'total',
	    fields: [
	       {name:'id'},
	       {name:'name'},
	       {name:'intro'}
        ]
    });
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{width:1,header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{width:1,header:'角色', align:'center',sortable:false, dataIndex:'name'},
				{width:3,header:'角色描述', align:'left',sortable:false, dataIndex:'intro'},
				{width:2,header:'操作', align:'center',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
					return genButton("分配权限", 'setRolePrivileges(this,'+val+')');
				}}
		]
	}); 
	
	new Ext.Viewport({
		layout : 'border',
		items : [{
			title:'角色管理',
	        region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [grid]
		}]
	});
	
	searchFunc();
});