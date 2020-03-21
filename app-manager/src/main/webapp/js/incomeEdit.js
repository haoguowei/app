Ext.onReady(function () {
    Ext.QuickTips.init();

    var payDayDiv = new com.custom.DateField({
        renderTo: 'incomeDayDiv',
        format: 'Y-m',
        name: 'incomeDay',
        value: getById('hideIncomeDay'),
        id: 'incomeDay'
    });


    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }

        if (_isNull(getById("incomeDay"))) {
            alert("请填写回款所属月份！");
            document.getElementById("incomeDay").focus();
            return false;
        }


        if (_isNotNull(getById("amount")) && !regexVerify('tingke', getById("amount"))) {
            alert("填写正确的金额！");
            document.getElementById("amount").focus();
            return false;
        }


    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑收入信息',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

