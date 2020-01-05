Ext.onReady(function () {
    Ext.QuickTips.init();

    var payDayDiv = new com.custom.DateField({
        renderTo: 'payDayDiv',
        format: 'Y-m-d',
        name: 'payDay',
        value: getById('hidePayDiv'),
        id: 'payDay'
    });


    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }

        if (_isNull(getById("payDay"))) {
            alert("请填写消费日期！");
            document.getElementById("payDay").focus();
            return false;
        }
        if (!isDate(getById("payDay"))) {
            alert("消费日期格式错误！");
            document.getElementById("payDay").focus();
            return false;
        }

        if (_isNull(getById("payType")) || getById("payType") == 0) {
            alert("请选择消费类型！");
            document.getElementById("payType").focus();
            return false;
        }


        if (_isNotNull(getById("payAmount")) && !regexVerify('tingke', getById("payAmount"))) {
            alert("消费金额请填写正确的金额！");
            document.getElementById("payAmount").focus();
            return false;
        }


    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑日常消费',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

