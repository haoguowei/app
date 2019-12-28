Ext.onReady(function () {
    Ext.QuickTips.init();

    var upDayDIV = new com.custom.DateField({
        renderTo: 'upDayDIV',
        format: 'Y-m',
        name: 'upDay',
        value: getById('hideUpDay'),
        id: 'upDay'
    });


    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }
        if (_isNull(getById("upDay"))) {
            alert("请填写清单时间！");
            document.getElementById("upDay").focus();
            return false;
        }
        if (!regexVerify('tingke', getById("incomeAmount"))) {
            alert("单价请填写正确的收入金额！");
            document.getElementById("incomeAmount").focus();
            return false;
        }
        if (!regexVerify('tingke', getById("payoutAmount"))) {
            alert("单价请填写正确的支出金额！");
            document.getElementById("payoutAmount").focus();
            return false;
        }
    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑收支信息',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

