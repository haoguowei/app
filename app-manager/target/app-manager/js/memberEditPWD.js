Ext.onReady(function(){
	Ext.QuickTips.init();
	
	this.save = function() {
		if (_isNull(document.getElementById("oldpwd").value)) {
			alert("请填写旧密码！");
			document.getElementById("oldpwd").focus();
			return false;
		}
		if (_isNull(document.getElementById("pwd").value)) {
			alert("请填写新密码！");
			document.getElementById("pwd").focus();
			return false;
		}
		if (_isNull(document.getElementById("pwd2").value)) {
			alert("请再次填写新密码！");
			document.getElementById("pwd2").focus();
			return false;
		}
		if (document.getElementById("pwd").value != document.getElementById("pwd2").value) {
			alert("两次输入的新密码不一致，请重新填写！");
			document.getElementById("pwd2").focus();
			return false;
		}
	};
	

	new Ext.Viewport({
		layout : 'fit',
		items : [ {
			title : '修改用户' + document.getElementById("hideName").value + '密码',
			bodyStyle : 'padding:5px',
			border : false,
			frame : false,
			autoScroll : true,
			contentEl : 'div_panel_id'
		} ]
	});
});
