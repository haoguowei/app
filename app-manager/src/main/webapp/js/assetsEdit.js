Ext.onReady(function () {
    Ext.QuickTips.init();

    var buyTimeDiv = new com.custom.DateField({
        renderTo: 'buyTimeDiv',
        format: 'Y-m-d',
        name: 'buyTime',
        value: getById('hideBuyTimeDiv'),
        id: 'buyTime'
    });

    this.save = function () {
        if (_isNull(getById("projects")) || getById("projects") == 0) {
            alert("请选择所属项目！");
            document.getElementById("projects").focus();
            return false;
        }
        if (_isNull(getById("type")) || getById("type") == 0) {
            alert("请选择资产类型！");
            document.getElementById("type").focus();
            return false;
        }
        if (_isNull(getById("name"))) {
            alert("请填写资产名称！");
            document.getElementById("name").focus();
            return false;
        }

        if (_isNull(getById("number"))) {
            alert("请填写资产编号！");
            document.getElementById("number").focus();
            return false;
        }
        if (_isNull(getById("owner")) || getById("owner") == 0) {
            alert("请选择责任人！");
            document.getElementById("owner").focus();
            return false;
        }
        if (_isNull(getById("buyTime"))) {
            alert("请填写采购时间！");
            document.getElementById("buyTime").focus();
            return false;
        }
        if (!isDate(getById("buyTime"))) {
            alert("采购时间格式错误！");
            document.getElementById("buyTime").focus();
            return false;
        }

        if (!regexVerify('num1', getById("quantity"))) {
            alert("数量请填写正确的数字！");
            document.getElementById("quantity").focus();
            return false;
        }
        if (!regexVerify('num1', getById("quoQuantity"))) {
            alert("现况数量请填写正确的数字！");
            document.getElementById("quoQuantity").focus();
            return false;
        }
        if (!regexVerify('num1', getById("staging"))) {
            alert("分期月数请填写正确的数字！");
            document.getElementById("staging").focus();
            return false;
        }
        if (!regexVerify('tingke', getById("price"))) {
            alert("单价请填写正确的金额！");
            document.getElementById("price").focus();
            return false;
        }
        if (!regexVerify('tingke', getById("purTax"))) {
            alert("购置税请填写正确的金额！");
            document.getElementById("purTax").focus();
            return false;
        }
    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑资产信息',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});