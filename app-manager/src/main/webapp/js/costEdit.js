Ext.onReady(function () {
    Ext.QuickTips.init();

    var enterDateDiv = new com.custom.DateField({
        renderTo: 'enterDateDiv',
        format: 'Y-m-d',
        name: 'enterDate',
        value: getById('hideEnterDateDiv'),
        id: 'enterDate'
    });

    this.selectType2 = function () {
        var type1 = getById("type1");
        $("#type2").empty();
        $("#type2").append("<option value='0'>请选择...</option>");

        $("#type3").empty();
        $("#type3").append("<option value='0'>请选择...</option>");

        if (type1 == 0) {
            return;
        }

        Ext.Ajax.request({
            url: 'getTypeListByParentId.do?parentId=' + type1,
            success: function (response) {
                var resp = Ext.util.JSON.decode(response.responseText);
                if (resp.success) {
                    resp.info.forEach(function (v) {
                        $("#type2").append("<option value='" + v.id + "'>" + v.name + "</option>");
                    })
                } else {
                    alert("消费类型获取失败");
                }
            }
        });
    };

    this.selectType3 = function () {
        var type2 = getById("type2");
        $("#type3").empty();
        $("#type3").append("<option value='0'>请选择...</option>");

        if (type2 == 0) {
            return;
        }

        Ext.Ajax.request({
            url: 'getTypeListByParentId.do?parentId=' + type2,
            success: function (response) {
                var resp = Ext.util.JSON.decode(response.responseText);
                if (resp.success) {
                    resp.info.forEach(function (v) {
                        $("#type3").append("<option value='" + v.id + "'>" + v.name + "</option>");
                    })
                } else {
                    alert("消费类型获取失败");
                }
            }
        });
    };


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

        var type1 = getById("type1");
        var type2 = getById("type2");
        var type3 = getById("type3");

        if (_isNull(type1) || type1 == 0) {
            alert("请选择费用类型！");
            document.getElementById("type1").focus();
            return false;
        }
        if (_isNull(type2) || type2 == 0) {
            alert("请选择费用类型！");
            document.getElementById("type2").focus();
            return false;
        }
        if (_isNull(type3) || type3 == 0) {
            alert("请选择费用类型！");
            document.getElementById("type3").focus();
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

