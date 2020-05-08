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
        gridStore.setBaseParam("type1", getById("type1"));
        gridStore.setBaseParam("type2", getById("type2"));
        gridStore.setBaseParam("type3", getById("type3"));
        gridStore.reload();
    };

    this.updateF = function (id) {
        location.href = "initCostsEdit.do?id=" + id;
    };

    this.viewF = function (id) {
        // location.href = "initAssetsEdit.do?view=1&id=" + id;
    };

    this.selectType2 = function () {
        var type1 = getById("type1");
        $("#type2").empty();
        $("#type3").empty();

        Ext.Ajax.request({
            url: 'getTypeListByParentId.do?parentId=' + type1,
            success: function (response) {
                var resp = Ext.util.JSON.decode(response.responseText);
                if (resp.success) {
                    $("#type2").append("<option value='0'>请选择...</option>");
                    resp.info.forEach(function (v) {
                        $("#type2").append("<option value='" + v.id + "'>" + v.name + "</option>");
                    })
                } else {
                    alert("消费类型获取失败");
                }
            }
        });
    };

    this.selectType3 = function () {
        var type2 = getById("type2");
        $("#type3").empty();

        Ext.Ajax.request({
            url: 'getTypeListByParentId.do?parentId=' + type2,
            success: function (response) {
                var resp = Ext.util.JSON.decode(response.responseText);
                if (resp.success) {
                    $("#type3").append("<option value='0'>请选择...</option>");
                    resp.info.forEach(function (v) {
                        $("#type3").append("<option value='" + v.id + "'>" + v.name + "</option>");
                    })
                } else {
                    alert("消费类型获取失败");
                }
            }
        });
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
            {name: 'type1Str'},
            {name: 'type2Str'},
            {name: 'type3Str'},
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
            type1: '',
            type2: '',
            type3: '',
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
                header: '费用日期',
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
                width: 4,
                header: '费用类型',
                align: 'left',
                sortable: false,
                dataIndex: 'type3',
                renderer: function (val, cell, record) {
                    var a = record.data.type1Str;
                    var b = record.data.type2Str;
                    var c = record.data.type3Str;

                    if ((a == null || a == '') && (b == null || b == '') && (c == null || c == '')) {
                        return '';
                    }
                    return a + "-" + b + "-" + c;
                }
            },
            {width: 2, header: '费用金额(元)', align: 'right', sortable: false, dataIndex: 'amount'},
            {
                width: 2,
                header: '费用状态',
                align: 'left',
                sortable: false,
                dataIndex: 'status',
                renderer: function (val, cell, record) {
                    if (val == null || val == '' || val == 0 || val == '0') {
                        return '录入';
                    } else if (val == 1 || val == '1') {
                        return '提交';
                    }
                }
            },
            {width: 2, header: '费用单号', align: 'left', sortable: false, dataIndex: 'numb'},
            {width: 2, header: '费用用途', align: 'left', sortable: false, dataIndex: 'useful'},
            {width: 2, header: '创建人', align: 'left', sortable: false, dataIndex: 'creater'},
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