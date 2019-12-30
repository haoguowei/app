Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initFinanceEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveFinance.do");//新增编辑用户

    //-----------------权限相关 end-----------

    var fromDIV = new com.custom.DateField({
        renderTo: 'fromDIV',
        format: 'Y-m',
        name: 'fromDay',
        value: getById("hideA"),
        id: 'fromDay'
    });
    var endDIV = new com.custom.DateField({
        renderTo: 'endDIV',
        format: 'Y-m',
        name: 'endDay',
        value: getById("hideB"),
        id: 'endDay'
    });

    this.searchFunc = function () {
        gridStore.setBaseParam("fromDay", getById("fromDay"));
        gridStore.setBaseParam("endDay", getById("endDay"));
        gridStore.setBaseParam("projectsId", getById("projectsId"));

        gridStore.reload({
            callback: function (r, options, success) {
                if (success) {
                    Ext.Ajax.request({
                        url: 'initFinanceHeJi.do',
                        success: function (response) {
                            var resp = Ext.util.JSON.decode(response.responseText);
                            if (resp.success) {
                                Ext.getCmp("huizong_show").setText(resp.info);
                            } else {
                                Ext.getCmp("huizong_show").setText("合计数据获取失败！");
                            }
                        }
                    });
                }
            }
        });
    };

    this.updateF = function (id) {
        location.href = "initFinanceEdit.do?id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchFinance.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'projectsName'},
            {name: 'incomeAmount'},
            {name: 'payoutAmount'},
            {name: 'profit'},
            {name: 'creater'},
            {name: 'upDay'},
            {name: 'remark'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            fromDay: '',
            endDay: ''
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
            {width: 2, header: '收入金额(元)', align: 'left', sortable: false, dataIndex: 'incomeAmount'},
            {width: 2, header: '支出金额(元)', align: 'left', sortable: false, dataIndex: 'payoutAmount'},
            {width: 2, header: '净流量(元)', align: 'left', sortable: false, dataIndex: 'profit'},
            {
                width: 2,
                header: '录入时间',
                align: 'left',
                sortable: false,
                dataIndex: 'upDay',
                renderer: function (val, cell, record) {
                    if (val == null || val == '') {
                        return '';
                    }
                    return new Date(val).format("Y-m");
                }
            },

            {width: 2, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {width: 2, header: '录入人', align: 'left', sortable: false, dataIndex: 'creater'},
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
            title: '收支清单',
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
            bbar: ['->', {
                id: 'huizong_show',
                xtype: 'label',
                text: ''
            }],
            tbar: [{
                text: '录入收支',
                id: 'bt_add',
                handler: function (b, e) {
                    location.href = "initFinanceEdit.do";
                }
            }, '-', {
                text: '导出清单',
                id: 'bt_ext',
                handler: function (b, e) {
                    alert("功能开发中...");
                }
            }, '->',
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