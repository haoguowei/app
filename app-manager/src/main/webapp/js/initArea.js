Ext.onReady(function(){

	Ext.QuickTips.init();

	var uploadId = 0;

	//-----------------权限相关 start-----------

	var urlEditValid = "initAreaEdit.do";

	//是否有权限
	var isPRISaveMember = isHavePRI("saveArea.do");//新增编辑用户

	//-----------------权限相关 end-----------

	this.searchFunc = function() {
		gridStore.load();
	};

	this.updateF = function(id) {
		location.href = "initAreaEdit.do?id=" + id;
	};


	//列表数据
	var gridStore = new Ext.data.JsonStore({
	    url : 'searchArea.do',
	    root : 'resultList',
	    remoteSort: false,
	    totalProperty:'total',
	    fields: [
	       {name:'id'},
	       {name:'name'},
	       {name:'remark'}
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
				{width:2,header:'区域名称', align:'left',sortable:false, dataIndex:'name'},
				{width:3,header:'备注', align:'left',sortable:false, dataIndex:'remark'},
				{width:2,header:'操作', align:'center',sortable:false, dataIndex:'id',renderer:function(val,cell,record){
                					var str = '';
                					if(urlEditValid){//权限
                						str += genButton("修改",'updateF('+val+')');
                					}
                					return str;
                				}}
		]
	});

	new Ext.Viewport({
		layout : 'border',
		items : [{
			title:'区域管理',
	        region : 'center',
			frame : false,
			border : true,
			autoScroll : true,
			items : [grid],
			tbar : [{
						text : '新增区域',
						id:'bt_add',
						handler : function(b,e){
							location.href = "initAreaEdit.do";
						}
					},'->',
					new Ext.PagingToolbar({
			            store: gridStore,
			            style : {'border' : 0},
			            displayInfo: true
			        })]
		}]
	});

//	if(!isPRISaveMember){//权限
//		Ext.getCmp("bt_add").hide();
//	}

	searchFunc();
});