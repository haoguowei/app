<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="import.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>left</title>
	<script type="text/javascript">
	Ext.onReady(function(){
		
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		
		//普通菜单树
		var tree = new com.custom.TreePanel({
			id : 'tree',
			renderTo : 'menuTree',
			border : false,
			width : 180,
			height: document.documentElement.clientHeight,
			root : {
				id : 0,
				text : 'APP信息管理',
				href : '#',
				hrefTarget:'layout_page_right',
				leaf : false,
				checked : null
			},
			loader : new Ext.tree.TreeLoader( {
				dataUrl : "getSysMenuTree.do"
			})
		});
		//根节点展开
		tree.getRootNode().expand();
		//页面渲染
		tree.render(); 
	});
	</script>
</head>
<body>
	<div id="menuTree"></div>
</body>
</html>