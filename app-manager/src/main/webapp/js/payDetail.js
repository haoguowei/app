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
            {name: 'fixAmount'},
            {name: 'jiabanAmount'},
            {name: 'jixiaoAmount'},
            {name: 'jiangjinAmount'},
            {name: 'fakuanAmount'},
            {name: 'totalAmount'},
            {name: 'payedAmount'},
            {name: 'payStatus'},
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
                width: 2.5, header: '工资单', align: 'left', sortable: false, dataIndex: 'id',
                renderer: function (val, cell, record) {
                    return record.data.projectsName + "-" + new Date(record.data.payMonth).format("Y年m月");
                }
            },
            {width: 2, header: '固定工资', align: 'right', sortable: false, dataIndex: 'fixAmount'},
            {width: 2, header: '加班费', align: 'right', sortable: false, dataIndex: 'jiabanAmount'},
            {width: 2, header: '绩效', align: 'right', sortable: false, dataIndex: 'jixiaoAmount'},
            {width: 2, header: '奖金', align: 'right', sortable: false, dataIndex: 'jiangjinAmount'},
            {width: 2, header: '罚款', align: 'right', sortable: false, dataIndex: 'fakuanAmount'},
            {width: 2, header: '应发工资', align: 'right', sortable: false, dataIndex: 'totalAmount'},
            {width: 2, header: '已发工资', align: 'right', sortable: false, dataIndex: 'payedAmount'},
            {width: 2, header: '支付状态', align: 'right', sortable: false, dataIndex: 'payStatus'},
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