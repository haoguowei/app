Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initCostsEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveCosts.do");//新增编辑用户

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
        gridStore.setBaseParam("enterDateStart", getById("enterDateStart"));
        gridStore.setBaseParam("enterDateEnd", getById("enterDateEnd"));

        Ext.getCmp("huizong_show").setText("汇总信息...");
        gridStore.reload({
            callback: function (r, options, success) {
                if (success) {
                    Ext.Ajax.request({
                        url: 'initCostHeJi.do',
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
        location.href = "initCostsEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchCosts.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'enterDate'},
            {name: 'status'},
            {name: 'type1'},
            {name: 'type2'},
            {name: 'type3'},
            {name: 'amount'},
            {name: 'numb'},
            {name: 'useful'},
            {name: 'remark'},
            {name: 'creater'},
            {name: 'createTime'},
            {name: 'updateTime'},
            {name: 'projects'},
            {name: 'projectsName'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            enterDateStart: '',
            enterDateEnd: ''
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
            title: '费用管理',
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
            bbar: ['->', {
                id: 'huizong_show',
                xtype: 'label',
                height: 20,
                text: ''
            }],
            tbar: [
                {
                    text: '录入费用',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initCostsEdit.do";
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