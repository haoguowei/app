
Ext.onReady(function(){
	Ext.QuickTips.init();


	this.save = function() {
		if (_isNull(document.getElementById("name").value)) {
			alert("请填写项目名称！");
			document.getElementById("name").focus();
			return false;
		}

		if (document.getElementById("areaId").value == 0) {
        			alert("请选择所属区域！");
        			return false;
        		}
	};

	new Ext.Viewport({
		layout : 'fit',
		items : [ {
			title : '项目管理编辑',
			bodyStyle : 'padding:5px',
			border : false,
			frame : false,
			autoScroll : true,
			contentEl : 'div_panel_id'
		} ]
	});
});
