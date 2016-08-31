Ext.onReady(function(){
	Ext.QuickTips.init();
	

	this.searchFunc = function() {
		gridStore.load();
	};

	this.updateF = function(menuId) {
		alert(menuId);
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
		        {header:'ID', align:'left',sortable:false, dataIndex:'id'},
				{header:'菜单', align:'center',sortable:false, dataIndex:'name',renderer:function(val,cell,record){
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
						str += genButton("修改","updateF("+val+")");
						str += genButton("新增子菜单","");
					}else{
						str += genButton("修改","updateF("+val+")");
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

				}
			} ]
		} ]
	});
	
	searchFunc();
});