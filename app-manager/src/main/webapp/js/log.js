Ext.onReady(function(){
	Ext.QuickTips.init();
	
	this.searchFunc=function(){
		gridStore.setBaseParam("name", $("name").value);
		gridStore.load();
	};
	
	//列表数据
	var gridStore = new Ext.data.JsonStore({
	    url : 'searchLogs.do',
	    root : 'resultList',
	    remoteSort: false, 
	    totalProperty:'total',
	    fields: [
	       {name:'id'},
	       {name:'operatorTime'},
	       {name:'operator'},
	       {name:'description'}
        ],
        baseParams : {
        		limit : 50,
        		name :''
		}
    });
	
	var grid = new com.custom.GridPanel({
		store : gridStore,
		region : 'center',
		frame : false,
		border : false,
		autoHeight:true,
		columns: [ 
				{width:1,header:'ID', align:'center',sortable:false, dataIndex:'id'},
				{width:1.5,header:'操作人', align:'left',sortable:false, dataIndex:'operator'},
				{width:1.5,header:'操作时间', align:'center',sortable:false, dataIndex:'operatorTime',renderer:function(val){
					return Ext.util.Format.date(val,'Y-m-d H:i:s');
				}},
				{width:6,header:'描述', align:'left',sortable:false, dataIndex:'description',renderer:function(val){
					return '<span title = "'+val+'">'+Ext.util.Format.ellipsis(val, 85)+'</span>';
				}}
		]
	}); 
	
	new Ext.Viewport({
		layout : 'border',
		items : [{
			region : 'north',
			title :  "操作日志",
			border : false,
			height : 75,
			keys : {
				key : Ext.EventObject.ENTER,
				fn : function(btn,e){
					searchFunc();
				}
			},
			contentEl : 'search_div_id'
		},{
	        region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [grid],
			tbar : ['->',new Ext.PagingToolbar({
			            pageSize: 50,
			            store: gridStore,
			            style : {'border' : 0},
			            displayInfo: true
			        })]
		}]
	});
	
	searchFunc();
});