<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>
</title>
	<script type="text/javascript" src="<%=syspath%>/js/window-privileges.js"></script>
	<script type="text/javascript" src="<%=syspath%>/js/role.js"></script>
</head>
<body>

	<input type="hidden" id="hidRoleId" name="hidRoleId" value="">
	
	<!-- 权限树 -->
	<div id="treeDiv"></div>
	
	<!-- 编辑窗 -->
	<div id="pWindow" class="windowDiv">
		<input type="hidden" id="hidRoleIdEdit" name="hidRoleIdEdit" value="">
		<table>
			<tbody>
				<tr>
					<td>角色名:</td>
					<td>
						<input id="rName" name="rName" value="">
					</td>
				</tr>
				<tr>
					<td>角色描述:</td>
					<td>
						<input id="rIntro" name="rIntro" value="">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>