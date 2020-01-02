Ext.onReady(function () {
    Ext.QuickTips.init();

    var fromDIV = new com.custom.DateField({
        renderTo: 'fromDIV',
        format: 'Y-m',
        height: 20,
        name: 'fromDate',
        value: getById('hideStartDate'),
        id: 'fromDate'
    });
    var endDIV = new com.custom.DateField({
        renderTo: 'endDIV',
        format: 'Y-m',
        height: 20,
        name: 'endDate',
        value: getById('hideEndDate'),
        id: 'endDate'
    });

    this.searchFunc = function () {
        var fromDate = getById("fromDate");
        var endDate = getById("endDate");

        if (_isNull(fromDate)) {
            alert("请选择搜索的开始时间！");
            document.getElementById("fromDate").focus();
            return;
        }
        if (_isNull(endDate)) {
            alert("请选择搜索的结束时间！");
            document.getElementById("endDate").focus();
            return;
        }
        location.href = "initKanban.do?fromDate=" + fromDate + "&endDate=" + endDate;
    };


    new Ext.Viewport({
        layout: 'fit',
        items: [{
            title: '收支汇总',
            region: 'center',
            bodyStyle: 'padding:5px',
            border: true,
            frame: false,
            autoScroll: true,
            contentEl: 'div_panel_id'
        }]
    });
});