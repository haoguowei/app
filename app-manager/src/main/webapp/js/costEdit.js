Ext.onReady(function () {
    Ext.QuickTips.init();

    var enterDateDiv = new com.custom.DateField({
        renderTo: 'enterDateDiv',
        format: 'Y-m-d',
        name: 'enterDate',
        value: getById('hideEnterDateDiv'),
        id: 'enterDate'
    });


    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }
        if (_isNull(getById("enterDate"))) {
            alert("请填写费用日期！");
            document.getElementById("enterDate").focus();
            return false;
        }
        if (!isDate(getById("enterDate"))) {
            alert("费用日期格式错误！");
            document.getElementById("enterDate").focus();
            return false;
        }
        if (_isNull(getById("amount"))) {
            alert("请填写费用金额！");
            document.getElementById("amount").focus();
            return false;
        }
        if (_isNotNull(getById("amount")) && !regexVerify('tingke', getById("amount"))) {
            alert("填写正确的费用金额！");
            document.getElementById("amount").focus();
            return false;
        }

    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑费用',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

