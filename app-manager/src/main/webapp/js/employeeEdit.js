
Ext.onReady(function () {
    Ext.QuickTips.init();

    var hideEntryDateDiv = new com.custom.DateField({
        renderTo: 'entryDateDiv',
        format: 'Y-m-d',
        name: 'entryDate',
        value: getById('hideEntryDateDiv'),
        id: 'entryDate'
    });
    var hideLeaveDateDiv = new com.custom.DateField({
        renderTo: 'leaveDateDiv',
        format: 'Y-m-d',
        name: 'leaveDate',
        value: getById('hideLeaveDateDiv'),
        id: 'leaveDate'
    });


    this.save = function () {
        if (_isNull(getById("name"))) {
            alert("请填写姓名！");
            document.getElementById("name").focus();
            return false;
        }
        if (_isNull(getById("phone"))) {
            alert("请填写手机号！");
            document.getElementById("phone").focus();
            return false;
        }
        if (!regexVerify('intege1', getById("phone"))) {
            alert("手机号请填写正确的数字！");
            document.getElementById("phone").focus();
            return false;
        }
        if (_isNull(getById("idCard"))) {
            alert("请填写身份证号！");
            document.getElementById("idCard").focus();
            return false;
        }
        var cardResult = getById("idCard");
        if (cardResult == true || cardResult == 'true') {
        } else {
            alert(cardResult);
            $("idCard").focus();
            return false;
        }

        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }

        if (_isNull(getById("jobType")) || getById("jobType") == 0) {
            alert("请选择职位！");
            document.getElementById("jobType").focus();
            return false;
        }


        if (_isNull(getById("entryDate"))) {
            alert("请填写入职日期！");
            document.getElementById("entryDate").focus();
            return false;
        }
        if (!isDate(getById("entryDate"))) {
            alert("入职日期格式错误！");
            document.getElementById("entryDate").focus();
            return false;
        }
        if (!_isNull(getById("leaveDate")) && !isDate(getById("leaveDate"))) {
            alert("离职日期格式错误！");
            document.getElementById("leaveDate").focus();
            return false;
        }
    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑员工信息',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});
