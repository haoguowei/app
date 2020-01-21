Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initPayEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("savePay.do");//新增编辑用户

    //-----------------权限相关 end-----------
    var entryDateStartDIV = new com.custom.DateField({
        renderTo: 'entryDateStartDIV',
        format: 'Y-m',
        name: 'startDate',
        value: '',
        id: 'startDate'
    });
    var entryDateEndDIV = new com.custom.DateField({
        renderTo: 'entryDateEndDIV',
        format: 'Y-m',
        name: 'endDate',
        value: '',
        id: 'endDate'
    });

    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("status", getById("status"));
        gridStore.setBaseParam("startDate", getById("startDate"));
        gridStore.setBaseParam("endDate", getById("endDate"));
        gridStore.reload();
    };

    this.updateF = function (id) {
        location.href = "initPayEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchPay.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'payMonth'},
            {name: 'projectsName'},
            {name: 'payStatus'},
            {name: 'totalMan'},
            {name: 'totalAmount'},
            {name: 'payedAmount'},
            {name: 'yuAmount'},
            {name: 'remark'},
            {name: 'creater'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            status: '',
            startDate: '',
            endDate: ''
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
            {
                width: 2,
                header: '日期',
                align: 'left',
                sortable: false,
                dataIndex: 'payMonth',
                renderer: function (val, cell, record) {
                    if (val == null || val == '') {
                        return '';
                    }
                    return new Date(val).format("Y-m");
                }
            },
            {width: 2, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {width: 2, header: '总人数', align: 'right', sortable: false, dataIndex: 'totalMan'},
            {width: 2, header: '总工资额', align: 'right', sortable: false, dataIndex: 'totalAmount'},
            {width: 2, header: '已发金额', align: 'right', sortable: false, dataIndex: 'payedAmount'},
            {width: 2, header: '未发金额', align: 'right', sortable: false, dataIndex: 'yuAmount'},
            {width: 1, header: '全部发放', align: 'left', sortable: false, dataIndex: 'assetsInfo'},

            {width: 2, header: '备注', align: 'left', sortable: false, dataIndex: 'remark'},
            {width: 1, header: '创建人', align: 'left', sortable: false, dataIndex: 'creater'},
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
            title: '工资单管理',
            border: false,
            height: 70,
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
            bbar: ['->', {
                id: 'huizong_show',
                xtype: 'label',
                height: 20,
                text: ''
            }],
            tbar: [
                {
                    text: '创建工资单',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initPayEdit.do";
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