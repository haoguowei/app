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
	<script type="text/javascript" src="<%=syspath%>/js/privileges.js"></script>
</head>
<body>
	<!-- 所属菜单信息 -->
	<input type="hidden" id="menuName" name="menuName" value="${menu.name }">
	<input type="hidden" id="menuId" name="menuId" value="${menu.id }">
	
	
	<!-- 编辑窗 -->
	<div id="pWindow" class="windowDiv">
		<input type="hidden" id="hidPriId" name="hidPriId" value="">
		<table>
			<tbody>
				<tr>
					<td>权限名:</td>
					<td>
						<input id="pName" name="pName" value="">
					</td>
				</tr>
				<tr>
					<td>权限URL:</td>
					<td>
						<input id="pUrl" name="pUrl" value="">
					</td>
				</tr>
			</tbody>
		</table>
	</div>
</body>
</html>