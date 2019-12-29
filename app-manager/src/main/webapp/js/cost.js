Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initYYCostEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveYYCost.do");//新增编辑用户

    //-----------------权限相关 end-----------
    var entryDateStartDIV = new com.custom.DateField({
        renderTo: 'entryDateStartDIV',
        format: 'Y-m-d',
        name: 'enterDateStart',
        value: '',
        id: 'enterDateStart'
    });
    var entryDateEndDIV = new com.custom.DateField({
        renderTo: 'entryDateEndDIV',
        format: 'Y-m-d',
        name: 'enterDateEnd',
        value: '',
        id: 'enterDateEnd'
    });

    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("name", getById("name"));
        gridStore.setBaseParam("enterDateStart", getById("enterDateStart"));
        gridStore.setBaseParam("enterDateEnd", getById("enterDateEnd"));
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
            {name: 'projectsName'},
            {name: 'assetsInfo'},
            {name: 'employeeName'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            enterDateStart: '',
            enterDateEnd: '',
            name: ''
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
            {width: 2, header: '消费司机', align: 'left', sortable: false, dataIndex: 'employeeName'},
            {width: 3, header: '消费资产', align: 'left', sortable: false, dataIndex: 'assetsInfo'},
            {
                width: 2,
                header: '消费日期',
                align: 'left',
                sortable: false,
                dataIndex: 'enterDate',
                renderer: function (val, cell, record) {
                    if (val == null || val == '') {
                        return '';
                    }
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
                    if (val == null || val == '') {
                        return '';
                    }

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
            height: 120,
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
                    text: '录入消费信息',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initYYCostEdit.do";
                    }
                }, '-', {
                    text: '导出消费',
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