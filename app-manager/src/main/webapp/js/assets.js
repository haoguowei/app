Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initAssetsEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveAssets.do");//新增编辑用户

    //-----------------权限相关 end-----------
    var buyTimeStartDIV = new com.custom.DateField({
        renderTo: 'buyTimeStartDIV',
        format: 'Y-m-d',
        name: 'buyTimeStart',
        value: '',
        id: 'buyTimeStart'
    });
    var buyTimeEndDIV = new com.custom.DateField({
        renderTo: 'buyTimeEndDIV',
        format: 'Y-m-d',
        name: 'buyTimeEnd',
        value: '',
        id: 'buyTimeEnd'
    });

    // this.exportFunc = function () {
    //     var projectsId = getById("projectsId");
    //     var name = getById("name");
    //     var type = getById("type");
    //
    //     var buyTimeStart = getById("buyTimeStart");
    //     var buyTimeEnd = getById("buyTimeEnd");
    //
    //     if (_isNull(buyTimeStart)) {
    //         alert("导出资产时请设置采购时间的搜索范围！");
    //         document.getElementById("buyTimeStart").focus();
    //         return;
    //     }
    //     if (_isNull(buyTimeEnd)) {
    //         alert("导出资产时请设置采购时间的搜索范围！");
    //         document.getElementById("buyTimeEnd").focus();
    //         return;
    //     }
    //
    //     searchFunc();
    //     location.href = "exportAssets.do?name=" + name
    //         + "&projectsId=" + projectsId
    //         + "&number=" + number
    //         + "&type=" + type
    //         + "&buyTimeStart=" + buyTimeStart
    //         + "&buyTimeEnd=" + buyTimeEnd;
    // }

    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("name", getById("name"));
        gridStore.setBaseParam("type", getById("type"));

        gridStore.setBaseParam("brand", getById("brand"));
        gridStore.setBaseParam("carType", getById("carType"));

        gridStore.setBaseParam("buyTimeStart", getById("buyTimeStart"));
        gridStore.setBaseParam("buyTimeEnd", getById("buyTimeEnd"));

        Ext.getCmp("huizong_show").setText("汇总信息...");
        gridStore.reload({
            callback: function (r, options, success) {
                if (success) {
                    Ext.Ajax.request({
                        url: 'initAssetsHeJi.do',
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
        location.href = "initAssetsEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchAssets.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'name'},
            {name: 'number'},
            {name: 'brandStr'},
            {name: 'carTypeStr'},
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
            projectsId: '',
            carType: '',
            brand: '',
            name: '',
            type: '',
            buyTimeStart: '',
            buyTimeEnd: ''
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
            {width: 1.5, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {width: 2, header: '资产名称', align: 'left', sortable: false, dataIndex: 'name'},
            {width: 2, header: '资产编号', align: 'left', sortable: false, dataIndex: 'number'},
            {width: 2, header: '类型', align: 'left', sortable: false, dataIndex: 'typeStr'},
            {width: 2, header: '车型', align: 'left', sortable: false, dataIndex: 'carTypeStr'},
            {width: 2, header: '品牌', align: 'left', sortable: false, dataIndex: 'brandStr'},
            {width: 2, header: '数量', align: 'right', sortable: false, dataIndex: 'quantity'},
            {width: 2, header: '现况数量', align: 'right', sortable: false, dataIndex: 'quoQuantity'},
            {
                width: 2,
                header: '采购时间',
                align: 'left',
                sortable: false,
                dataIndex: 'buyTime',
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
            title: '车辆管理',
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
                text: ''
            }],
            tbar: [
                {
                    text: '录入车辆资产',
                    id: 'bt_add',
                    handler: function (b, e) {
                        location.href = "initAssetsEdit.do";
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