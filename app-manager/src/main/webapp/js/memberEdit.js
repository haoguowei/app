Ext.onReady(function(){
	Ext.QuickTips.init();
	
	this.save = function(){
		if(_isNull($("name").value)){
			alert("请填写登录名！");
			$("name").focus();
			return false;
		}
		if(_isNull($("showName").value)){
			alert("请填写显示名！");
			$("showName").focus();
			return false;
		}
		if($("roleId").value == 0){
			alert("请选择角色！");
			return false;
		}
		if(_isNull($("hideId").value) || $("hideId").value == 0){
			if(_isNull($("pwd").value)){
				alert("请填写登录密码！");
				$("pwd").focus();
				return false;
			}
			if(_isNull($("pwd2").value)){
				alert("请再次填写登录密码！");
				$("pwd2").focus();
				return false;
			}
			if($("pwd").value != $("pwd2").value){
				alert("两次输入的密码不一致，请重新填写！");
				$("pwd2").focus();
				return false;
			}
		}
	};
	
	new Ext.Viewport({
		layout : 'fit',
		items : [{
			title : '系统用户信息编辑',
			bodyStyle:'padding:5px', 
			border : false,
			frame : false,
			autoScroll : true,
			contentEl : 'div_panel_id'
		}]
	});
});
