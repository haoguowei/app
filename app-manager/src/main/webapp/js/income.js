Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initIncomeEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveIncome.do");//新增编辑用户

    //-----------------权限相关 end-----------
    var dateStartDIV = new com.custom.DateField({
        renderTo: 'dateStartDIV',
        format: 'Y-m-d',
        name: 'dateStart',
        value: '',
        id: 'dateStart'
    });
    var dateEndDIV = new com.custom.DateField({
        renderTo: 'dateEndDIV',
        format: 'Y-m-d',
        name: 'dateEnd',
        value: '',
        id: 'dateEnd'
    });

    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("dateStart", getById("dateStart"));
        gridStore.setBaseParam("dateEnd", getById("dateEnd"));

        gridStore.reload();
        // Ext.getCmp("huizong_show").setText("...");
        //
        // gridStore.reload({
        //     callback: function (r, options, success) {
        //         if (success) {
        //             Ext.Ajax.request({
        //                 url: 'initOtherCostHeJi.do',
        //                 success: function (response) {
        //                     var resp = Ext.util.JSON.decode(response.responseText);
        //                     if (resp.success) {
        //                         Ext.getCmp("huizong_show").setText(resp.info);
        //                     } else {
        //                         Ext.getCmp("huizong_show").setText("合计数据获取失败！");
        //                     }
        //                 }
        //             });
        //         }
        //     }
        // });
    };

    this.updateF = function (id) {
        location.href = "initIncomeEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchIncome.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'incomeDay'},
            {name: 'projectsName'},
            {name: 'incomeAmount'},
            {name: 'contractNumb'},
            {name: 'contractAmount'},
            {name: 'jiafang'},
            {name: 'jiafangInfo'},
            {name: 'remark'},
            {name: 'contractNumb'},
            {name: 'contractAmount'},
            {name: 'creater'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            dateStart: '',
            dateEnd: ''
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
            {width: 2, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {
                width: 3,
                header: '回款日期',
                align: 'left',
                sortable: false,
                dataIndex: 'incomeDay',
                renderer: function (val, cell, record) {
                    if (val == null || val == '') {
                        return '';
                    }
                    return new Date(val).format("Y-m-d");
                }
            },
            {width: 3, header: '回款金额(元)', align: 'right', sortable: false, dataIndex: 'incomeAmount'},
            {width: 3, header: '合同编号', align: 'left', sortable: false, dataIndex: 'contractNumb'},
            {width: 3, header: '合同金额(元)', align: 'right', sortable: false, dataIndex: 'contractAmount'},
            {width: 3, header: '甲方', align: 'right', sortable: false, dataIndex: 'jiafang'},
            {width: 3, header: '甲方联系方式', align: 'left', sortable: false, dataIndex: 'jiafangInfo'},
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
            title: '收入管理',
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
                height: 20,
                text: ''
            }],
            tbar: [
                {
                    text: '新增收入',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initIncomeEdit.do";
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