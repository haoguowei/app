Ext.onReady(function(){
	Ext.QuickTips.init();
	var menuId = $("menuId").value;
	
	var menuName = $("menuName").value;
	var title = _isNull(menuName)?'系统所有权限':('《' + menuName + '》的权限');

		
	this.searchFunc = function() {
		gridStore.load();
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
		}, {
			name : 'intro'
		} ]
	});
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{width:1,header:'ID', align:'left',sortable:false, dataIndex:'id'},
				{width:1,header:'权限名称', align:'left',sortable:false, dataIndex:'name'},
				{width:1,header:'权限地址', align:'left',sortable:false, dataIndex:'url'}
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
			items : [ grid ]
		} ]
	});

	searchFunc();
});