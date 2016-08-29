Ext.onReady(function(){
	Ext.QuickTips.init();
	
	this.searchFunc=function(){
		gridStore.load();
	};
	
	//列表数据
	var gridStore = new Ext.data.JsonStore({
	    url : 'searchPrivileges.do',
	    root : 'resultList',
	    remoteSort: false, 
	    totalProperty:'total',
	    fields: [
	       {name:'id'},
	       {name:'name'},
	       {name:'url'},
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
				{width:1,header:'ID', align:'left',sortable:false, dataIndex:'id'},
				{width:1,header:'权限名称', align:'left',sortable:false, dataIndex:'name'},
				{width:1,header:'权限地址', align:'left',sortable:false, dataIndex:'url'},
				{width:3,header:'权限描述', align:'left',sortable:false, dataIndex:'intro'}
		]
	}); 
	
	new Ext.Viewport({
		layout : 'border',
		items : [{
			title:'权限管理',
	        region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [grid]
		}]
	});
	
	searchFunc();
});