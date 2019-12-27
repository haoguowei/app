
Ext.onReady(function () {
    Ext.QuickTips.init();


    this.save = function () {
        if (_isNull(getById("name"))) {
            alert("请填写姓名！");
            document.getElementById("name").focus();
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
