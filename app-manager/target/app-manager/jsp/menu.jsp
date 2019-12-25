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
<title></title>
<script type="text/javascript" src="<%=syspath%>/js/menu.js"></script>
</head>
<body>
	
	<!-- 编辑窗 -->
	<div id="menuWindow" class="windowDiv">
		<input type="hidden" id="hidMenuId" name="hidMenuId" value="">
		<input type="hidden" id="hidMenuParentId" name="hidMenuParentId" value="">
		<table>
			<tbody>
				<tr>
					<td>菜单名:</td>
					<td>
						<input id="menuName" name="menuName" value="">
					</td>
				</tr>
				<tr>
					<td>菜单URL:</td>
					<td>
						<input id="menuUrl" name="menuUrl" value="">
					</td>
				</tr>
				<tr>
					<td>排序:</td>
					<td>
						<input id="menuSort" name="menuSort" value="">
					</td>
				</tr>
			</tbody>
		</table>
	</div>

</body>
</html>