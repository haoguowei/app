Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initEmployeeEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveEmployee.do");//新增编辑用户

    //-----------------权限相关 end-----------


    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("name", getById("name"));
        gridStore.setBaseParam("idCard", getById("idCard"));
        gridStore.setBaseParam("status", 100);

        gridStore.reload();
    };

    this.shengheF = function (id) {
        document.getElementById("hidEmpId").value = id;
        sqWindow.show();
    };

    var sqWindow = new com.custom.Window({
        title: '成为正式员工审核',
        width: 500,
        height: 250,
        contentEl: 'sqWindow',
        buttons: [{
            text: '通过',
            handler: function () {
                var hidEmpId = document.getElementById("hidEmpId").value;
                var descr = document.getElementById("descr").value;

                var url = "passEmpF.do?id=" + hidEmpId
                    + "&descr=" + descr;

                passOrNot(url);
            }
        }, {
            text: '不通过',
            handler: function () {
                var hidEmpId = document.getElementById("hidEmpId").value;
                var descr = document.getElementById("descr").value;

                var url = "notPassEmpF.do?id=" + hidEmpId
                    + "&descr=" + descr;

                passOrNot(url);
            }
        }, {
            text: '关闭',
            handler: function () {
                sqWindow.hide();
            }
        }]
    });

    function passOrNot(url) {
        Ext.Ajax.request({
            url: url,
            success: function (response) {
                var resp = Ext.util.JSON.decode(response.responseText);
                if (resp.success) {
                    alert("操作成功！");
                    sqWindow.hide();
                    searchFunc();
                } else {
                    alert("操作失败：" + resp.resultTipMsg);
                }
            }
        });
    }

    sqWindow.on("beforeshow", function () {
        document.getElementById("descr").value = '';
    });

    //列表数据
    var gridStore = new Ext.data.JsonStore({
        url: 'searchEmployee.do',
        root: 'resultList',
        remoteSort: false,
        totalProperty: 'total',
        fields: [
            {name: 'id'},
            {name: 'name'},
            {name: 'idCard'},
            {name: 'shenqing'},
            {name: 'status'},
            {name: 'projectsName'},
            {name: 'entryDate'},
            {name: 'leaveDate'},
            {name: 'phone'},
            {name: 'jobType'},
            {name: 'safeType'},
            {name: 'idCard'},
            {name: 'jobTypeStr'},
            {name: 'genderStr'},
            {name: 'hetong'},
            {name: 'birthDate'},
            {name: 'age'},
            {name: 'hetongStr'},
            {name: 'remark'}
        ],
        baseParams: {
            limit: PAGESIZE,
            projectsId: '',
            name: '',
            status: '',
            idCard: ''
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
            {width: 1, header: '所属项目', align: 'left', sortable: false, dataIndex: 'projectsName'},
            {width: 2, header: '姓名', align: 'left', sortable: false, dataIndex: 'name'},
            {width: 1, header: '性别', align: 'left', sortable: false, dataIndex: 'genderStr'},
            {width: 2.5, header: '身份证', align: 'left', sortable: false, dataIndex: 'idCard'},
            {width: 2.5, header: '出生日期', align: 'left', sortable: false, dataIndex: 'birthDate'},
            {width: 1.5, header: '手机号', align: 'left', sortable: false, dataIndex: 'phone'},
            {width: 1, header: '职位', align: 'left', sortable: false, dataIndex: 'jobTypeStr'},
            {width: 2.5, header: '申请说明', align: 'left', sortable: false, dataIndex: 'shenqing'},
            {
                width: 2,
                header: '操作',
                align: 'center',
                sortable: false,
                dataIndex: 'id',
                renderer: function (val, cell, record) {
                    var str = genButton("审核", 'shengheF(' + val + ')');
                    return str;
                }
            }
        ]
    });

    new Ext.Viewport({
        layout: 'border',
        items: [{
            region: 'north',
            title: '人事审核',
            border: false,
            height: 75,
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
            tbar: ['->',
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