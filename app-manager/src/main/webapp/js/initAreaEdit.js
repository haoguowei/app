Ext.onReady(function(){
	Ext.QuickTips.init();
	

	this.save = function() {
		if (_isNull(document.getElementById("name").value)) {
			alert("请填写区域名称！");
			document.getElementById("name").focus();
			return false;
		}
	};

	new Ext.Viewport({
		layout : 'fit',
		items : [ {
			title : '区域管理编辑',
			bodyStyle : 'padding:5px',
			border : false,
			frame : false,
			autoScroll : true,
			contentEl : 'div_panel_id'
		} ]
	});
});
