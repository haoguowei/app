Ext.onReady(function () {

    Ext.QuickTips.init();

    var uploadId = 0;

    //-----------------权限相关 start-----------

    var urlEditValid = "initEmployeeEdit.do";

    //是否有权限
    var isPRISaveMember = isHavePRI("saveEmployee.do");//新增编辑用户

    //-----------------权限相关 end-----------

    var entryDateStartDIV = new com.custom.DateField({
        renderTo: 'entryDateStartDIV',
        format: 'Y-m-d',
        name: 'entryDateStart',
        value: '',
        id: 'entryDateStart'
    });
    var entryDateEndDIV = new com.custom.DateField({
        renderTo: 'entryDateEndDIV',
        format: 'Y-m-d',
        name: 'entryDateEnd',
        value: '',
        id: 'entryDateEnd'
    });

    var leaveDIV = new com.custom.DateField({
        renderTo: 'leaveDIV',
        format: 'Y-m-d',
        name: 'leaveDate',
        value: '',
        id: 'leaveDate'
    });

    this.searchFunc = function () {
        gridStore.setBaseParam("projectsId", getById("projectsId"));
        gridStore.setBaseParam("name", getById("name"));
        gridStore.setBaseParam("idCard", getById("idCard"));
        gridStore.setBaseParam("entryDateStart", getById("entryDateStart"));
        gridStore.setBaseParam("entryDateEnd", getById("entryDateEnd"));
        gridStore.setBaseParam("status", 1);

        gridStore.reload();
    };

    this.updateF = function (id) {
        location.href = "initEmployeeEdit.do?id=" + id;
    };

    this.leaveF = function (id) {
        document.getElementById("hidEmpId").value = id;
        sqWindow.show();
    };

    var sqWindow = new com.custom.Window({
        title: '员工离职',
        width: 500,
        height: 250,
        contentEl: 'sqWindow',
        buttons: [{
            text: '确定',
            handler: function () {
                var hidEmpId = document.getElementById("hidEmpId").value;
                var leaveDate = document.getElementById("leaveDate").value;
                if (_isNull(leaveDate)) {
                    alert('请选择离职时间');
                    return;
                }
                var url = "leaveF.do?id=" + hidEmpId
                    + "&leaveDate=" + leaveDate;

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
        }, {
            text: '关闭',
            handler: function () {
                sqWindow.hide();
            }
        }]
    });

    sqWindow.on("beforeshow", function () {
        document.getElementById("leaveDate").value = '';
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
            entryDateStart: '',
            entryDateEnd: '',
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
            {width: 3, header: '身份证', align: 'left', sortable: false, dataIndex: 'idCard'},
            {width: 1.5, header: '出生日期', align: 'left', sortable: false, dataIndex: 'birthDate'},
            {width: 1.5, header: '手机号', align: 'left', sortable: false, dataIndex: 'phone'},
            {width: 1, header: '职位', align: 'left', sortable: false, dataIndex: 'jobTypeStr'},
            {
                width: 1.5,
                header: '入职时间',
                align: 'left',
                sortable: false,
                dataIndex: 'entryDate',
                renderer: function (val, cell, record) {
                    return new Date(val).format("Y-m-d");
                }
            },

            {width: 2, header: '保险类型', align: 'left', sortable: false, dataIndex: 'safeType'},
            {width: 1, header: '入职合同', align: 'left', sortable: false, dataIndex: 'hetongStr'},
            {
                width: 2,
                header: '操作',
                align: 'center',
                sortable: false,
                dataIndex: 'id',
                renderer: function (val, cell, record) {
                    var str = '';
                    str += genButton("修改", 'updateF(' + val + ')');
                    str += genButton("离职", 'leaveF(' + val + ')');
                    return str;
                }
            }
        ]
    });

    new Ext.Viewport({
        layout: 'border',
        items: [{
            region: 'north',
            title: '员工管理',
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
            tbar: [
                //     {
                //     text: '新增员工',
                //     id: 'bt_add',
                //     handler: function (b, e) {
                //         location.href = "initEmployeeEdit.do";
                //     }
                // },
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