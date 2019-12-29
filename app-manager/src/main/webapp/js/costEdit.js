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
            alert("请填写消费日期！");
            document.getElementById("enterDate").focus();
            return false;
        }
        if (!isDate(getById("enterDate"))) {
            alert("消费日期格式错误！");
            document.getElementById("enterDate").focus();
            return false;
        }
        if (_isNull(getById("employeeId")) || getById("employeeId") == 0) {
            alert("请选择消费司机！");
            document.getElementById("employeeId").focus();
            return false;
        }
        if (_isNull(getById("assetId")) || getById("assetId") == 0) {
            alert("请选择消费资产！");
            document.getElementById("assetId").focus();
            return false;
        }


        if (_isNotNull(getById("startMileage")) && !regexVerify('intege1', getById("startMileage"))) {
            alert("日末里程数请填写正确的数字！");
            document.getElementById("startMileage").focus();
            return false;
        }
        if (_isNotNull(getById("endMileage")) && !regexVerify('intege1', getById("endMileage"))) {
            alert("日末里程数请填写正确的数字！");
            document.getElementById("endMileage").focus();
            return false;
        }
        if (_isNotNull(getById("workload")) && !regexVerify('intege1', getById("workload"))) {
            alert("作业量请填写正确的数字！");
            document.getElementById("workload").focus();
            return false;
        }
        if (_isNotNull(getById("fuel")) && !regexVerify('intege1', getById("fuel"))) {
            alert("加油量请填写正确的数字！");
            document.getElementById("fuel").focus();
            return false;
        }

        if (_isNotNull(getById("fuelAmount")) && !regexVerify('tingke', getById("fuelAmount"))) {
            alert("加油金额请填写正确的金额！");
            document.getElementById("fuelAmount").focus();
            return false;
        }
        if (_isNotNull(getById("baoyangAmount")) && !regexVerify('tingke', getById("baoyangAmount"))) {
            alert("保养费用请填写正确的金额！");
            document.getElementById("baoyangAmount").focus();
            return false;
        }
        if (_isNotNull(getById("fixAmount")) && !regexVerify('tingke', getById("fixAmount"))) {
            alert("维修费用请填写正确的金额！");
            document.getElementById("fixAmount").focus();
            return false;
        }

        if (_isNotNull(getById("shiguTimes")) && !regexVerify('intege1', getById("shiguTimes"))) {
            alert("事故次数请填写正确的数字！");
            document.getElementById("shiguTimes").focus();
            return false;
        }

        if (_isNotNull(getById("shiguAmount")) && !regexVerify('tingke', getById("shiguAmount"))) {
            alert("事故金额请填写正确的金额！");
            document.getElementById("shiguAmount").focus();
            return false;
        }
        if (_isNotNull(getById("shiguOutAmount")) && !regexVerify('tingke', getById("shiguOutAmount"))) {
            alert("事故保险外赔偿金额请填写正确的金额！");
            document.getElementById("shiguOutAmount").focus();
            return false;
        }
        if (_isNotNull(getById("baoxianAmount")) && !regexVerify('tingke', getById("baoxianAmount"))) {
            alert("保险费用请填写正确的金额！");
            document.getElementById("baoxianAmount").focus();
            return false;
        }
        if (_isNotNull(getById("yearCheckAmount")) && !regexVerify('tingke', getById("yearCheckAmount"))) {
            alert("年检费用请填写正确的金额！");
            document.getElementById("yearCheckAmount").focus();
            return false;
        }

    };

    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '编辑消费信息',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});

