<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<script type="text/javascript" src="<%=syspath%>/js/initAreaEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
<form name="form1" action="saveArea.do" method="post" onsubmit="return save()">
	<input type="hidden" id="hideId" name="hideId" value="${areaDO.id }">

	<table class="Mytable">
		<tr>
			<td width="100px">区域名称:<span style="color: red">*</span></td>
			<td>
				<input type="text"  class="Mytext" name="name" id="name" value="${areaDO.name }">
			</td>
		</tr>
		<tr>
			<td>备注</td>
			<td>
				<input type="text"  class="Mytext" name="remark" id="remark" value="${areaDO.remark }">
			</td>
		</tr>

		<tr>
			<td>&nbsp;</td>
			<td colspan="2">
				<input type="submit" value="确定" class="Mybotton">
				<input type="button" value="取消" class="Mybotton" onclick="_back()">
			</td>
		</tr>
	</table>
</form>
</div>
</html>