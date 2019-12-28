Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initYYCostEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveYYCost.do");//新增编辑用户

    //-----------------权限相关 end-----------

    this.searchFunc = function () {
        gridStore.setBaseParam("status", getById("status"));
        gridStore.setBaseParam("enterDate", getById("enterDate"));
        gridStore.reload();
    };

    this.updateF = function (id) {
        location.href = "initYYCostEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchYYCost.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'enterDate'},
            {name: 'number'},
            {name: 'projectsName'},
            {name: 'quantity'},
            {name: 'quoQuantity'},
            {name: 'type'},
            {name: 'owner'},
            {name: 'typeStr'},
            {name: 'buyTime'}
        ],
        baseParams: {
            limit: PAGESIZE,
            enterDate: '',
            status: ''
        }
    });

    var grid = new com.custom.GridPanel({
        store: gridStore,
        region: 'center',
        frame: false,
        border: false,
        autoHeight: true,
        columns: [
            {width: 1, header: 'ID', align: 'center', sortable: false, dataIndex: 'id'},
            {width: 2, header: '资产名称', align: 'left', sortable: false, dataIndex: 'name'},
            {width: 2, header: '资产编号', align: 'left', sortable: false, dataIndex: 'number'},
            {width: 2, header: '类型', align: 'left', sortable: false, dataIndex: 'typeStr'},
            {width: 2, header: '数量', align: 'right', sortable: false, dataIndex: 'quantity'},
            {width: 2, header: '现况数量', align: 'right', sortable: false, dataIndex: 'quoQuantity'},
            {
                width: 2,
                header: '采购时间',
                align: 'left',
                sortable: false,
                dataIndex: 'buyTime',
                renderer: function (val, cell, record) {
                    return new Date(val).format("Y-m-d");
                }
            },
            {width: 3, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {
                width: 2,
                header: '操作',
                align: 'center',
                sortable: false,
                dataIndex: 'id',
                renderer: function (val, cell, record) {
                    var str = '';
                    // str += genButton("查看", 'viewF(' + val + ')');
                    if (urlEditValid) {//权限
                        str += genButton("修改", 'updateF(' + val + ')');
                    }

                    return str;
                }
            }
        ]
    });

    new Ext.Viewport({
        layout: 'border',
        items: [{
            region: 'north',
            title: '资产信息',
            border: false,
            height: 80,
            keys: {
                key: Ext.EventObject.ENTER,
                fn: function (btn, e) {
                    searchFunc();
                }
            },
            contentEl: 'search_div_id'
        }, {
            region: 'center',
            frame: false,
            border: true,
            autoScroll: true,
            items: [grid],
            tbar: [
                {
                    text: '录入资产',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initAssetsEdit.do";
                    }
                }, '-', {
                    text: '导出资产',
                    id: 'bt_exp',
                    handler: function (b, e) {
                        alert("该功能正在开发中...");
                    }
                },
                '->',
                new Ext.PagingToolbar({
                    pageSize: PAGESIZE,
                    store: gridStore,
                    style: {'border': 0},
                    displayInfo: true
                })]
        }]
    });

//	if(!isPRISaveMember){//权限
//		Ext.getCmp("bt_add").hide();
//	}

    searchFunc();
});