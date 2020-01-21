Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initPayDetailEdit.do";

    //-----------------权限相关 end-----------

    this.searchFunc = function () {
        gridStore.setBaseParam("payId", getById("payId"));
        gridStore.load();
    };

    this.updateF = function (id) {
        location.href = "initPayDetailEdit.do?id=" + id;
    };


    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchPayDetail.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'payId'},
            {name: 'projectsName'},
            {name: 'remark'}
        ],
        baseParams: {
            payId: getById("payId")
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
            {width: 3, header: '备注', align: 'left', sortable: false, dataIndex: 'remark'},
            {
                width: 2,
                header: '操作',
                align: 'center',
                sortable: false,
                dataIndex: 'id',
                renderer: function (val, cell, record) {
                    var str = '';
                    if (urlEditValid) {//权限
                        str += genButton("修改", 'updateF(' + val + ')');
                    }
                    return str;
                }
            }
        ]
    });

    var title = getById("projectsName") + getById("payMonth") + "员工工资明细(" + getById('payId') + ")";
    new Ext.Viewport({
        layout: 'border',
        items: [{
            title: title,
            region: 'center',
            frame: false,
            border: true,
            autoScroll: true,
            items: [grid],
            tbar: [{
                text: '选择员工',
                id: 'bt_add',
                handler: function (b, e) {
                    //TODO
                }
            }, '->',
                new Ext.PagingToolbar({
                    store: gridStore,
                    style: {'border': 0},
                    displayInfo: true
                })]
        }]
    });

    searchFunc();
});