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
        location.href = "initPayDetailEdit.do?payId=" + getById("payId") + "&id=" + id;
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
            {name: 'payMonth'},
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
            {
                width: 2, header: '名称', align: 'left', sortable: false, dataIndex: 'id',
                renderer: function (val, cell, record) {
                    return record.data.projectsName + "-" + new Date(record.data.payMonth).format("Y年m月");
                }
            },
            {width: 2, header: '备注', align: 'left', sortable: false, dataIndex: 'remark'},
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

    var title = getById("projectsName") + "-" + getById("payMonth") + "，工资明细(ID-" + getById('payId') + ")";
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
                text: '新增员工工资',
                id: 'bt_add',
                handler: function (b, e) {
                    location.href = "initPayDetailEdit.do?payId=" + getById("payId") + "&id=" + 0 + "&flag=0";
                }
            }, '-', {
                text: '新增外部人员工资',
                id: 'bt_add_2',
                handler: function (b, e) {
                    location.href = "initPayDetailEdit.do?payId=" + getById("payId") + "&id=" + 0 + "&flag=100";
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