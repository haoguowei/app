Ext.onReady(function () {
    Ext.QuickTips.init();


    this.save = function () {
        if (_isNull(getById("employeeId")) || getById("employeeId") == 0) {
            alert("请选择员工！");
            document.getElementById("employeeId").focus();
            return false;
        }

        if (_isNotNull(getById("fixAmount")) && !regexVerify('tingke', getById("fixAmount"))) {
            alert("固定工资请填写正确的金额！");
            document.getElementById("fixAmount").focus();
            return false;
        }

        if (_isNotNull(getById("jiabanAmount")) && !regexVerify('tingke', getById("jiabanAmount"))) {
            alert("加班费请填写正确的金额！");
            document.getElementById("jiabanAmount").focus();
            return false;
        }
        if (_isNotNull(getById("jixiaoAmount")) && !regexVerify('tingke', getById("jixiaoAmount"))) {
            alert("绩效费请填写正确的金额！");
            document.getElementById("jixiaoAmount").focus();
            return false;
        }

        if (_isNotNull(getById("jiangjinAmount")) && !regexVerify('tingke', getById("jiangjinAmount"))) {
            alert("奖金请填写正确的金额！");
            document.getElementById("jiangjinAmount").focus();
            return false;
        }

        if (_isNotNull(getById("fakuanAmount")) && !regexVerify('tingke', getById("fakuanAmount"))) {
            alert("罚款请填写正确的金额！");
            document.getElementById("fakuanAmount").focus();
            return false;
        }
    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑工资明细',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

