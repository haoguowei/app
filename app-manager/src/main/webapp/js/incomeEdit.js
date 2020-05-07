Ext.onReady(function () {
    Ext.QuickTips.init();

    var payDayDiv = new com.custom.DateField({
        renderTo: 'incomeDayDiv',
        format: 'Y-m-d',
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
            alert("请填写回款日期！");
            document.getElementById("incomeDay").focus();
            return false;
        }

        if (_isNull(getById("incomeAmount"))) {
            alert("请填写回款金额！");
            document.getElementById("incomeAmount").focus();
            return false;
        }
        if (_isNull(getById("contractAmount"))) {
            alert("请填写合同金额！");
            document.getElementById("contractAmount").focus();
            return false;
        }


        if (_isNotNull(getById("incomeAmount")) && !regexVerify('tingke', getById("incomeAmount"))) {
            alert("填写正确的回款金额！");
            document.getElementById("incomeAmount").focus();
            return false;
        }

        if (_isNotNull(getById("contractAmount")) && !regexVerify('tingke', getById("contractAmount"))) {
            alert("填写正确的合同金额！");
            document.getElementById("contractAmount").focus();
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

