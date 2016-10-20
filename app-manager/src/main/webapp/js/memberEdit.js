Ext.onReady(function(){
	Ext.QuickTips.init();
	

	this.save = function() {
		if (_isNull(document.getElementById("name").value)) {
			alert("请填写登录名！");
			document.getElementById("name").focus();
			return false;
		}
		if (_isNull(document.getElementById("showName").value)) {
			alert("请填写显示名！");
			document.getElementById("showName").focus();
			return false;
		}
		if (document.getElementById("roleId").value == 0) {
			alert("请选择角色！");
			return false;
		}
		if (_isNull(document.getElementById("hideId").value) || document.getElementById("hideId").value == 0) {
			if (_isNull(document.getElementById("pwd").value)) {
				alert("请填写登录密码！");
				document.getElementById("pwd").focus();
				return false;
			}
			if (_isNull(document.getElementById("pwd2").value)) {
				alert("请再次填写登录密码！");
				document.getElementById("pwd2").focus();
				return false;
			}
			if (document.getElementById("pwd").value != document.getElementById("pwd2").value) {
				alert("两次输入的密码不一致，请重新填写！");
				document.getElementById("pwd2").focus();
				return false;
			}
		}
	};

	new Ext.Viewport({
		layout : 'fit',
		items : [ {
			title : '系统用户信息编辑',
			bodyStyle : 'padding:5px',
			border : false,
			frame : false,
			autoScroll : true,
			contentEl : 'div_panel_id'
		} ]
	});
});
