Ext.onReady(function () {
    Ext.QuickTips.init();

    var enterDateDiv = new com.custom.DateField({
        renderTo: 'enterDateDiv',
        format: 'Y-m',
        name: 'payMonth',
        value: getById('hideEnterDateDiv'),
        id: 'payMonth'
    });


    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }

        if (_isNull(getById("payMonth"))) {
            alert("请填写工资月份！");
            document.getElementById("payMonth").focus();
            return false;
        }
        if (!isDate(getById("payMonth"))) {
            alert("工资日期格式错误！");
            document.getElementById("payMonth").focus();
            return false;
        }
    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑工资单',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

