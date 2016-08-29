<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@include file="../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
	<script type="text/javascript" src="<%=syspath%>/js/memberEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
<form name="form1" action="saveMember.do" method="post" onsubmit="return save()">
	<input type="hidden" id="hideId" name="hideId" value="${member.id }">
	<table class="Mytable">
		<tr>
			<td width="100px">登录名:<span style="color: red">*</span></td>
			<td>
				<input type="text"  class="Mytext" name="name" id="name" value="${member.name }" <c:if test="${not empty member }">readonly="readonly"</c:if>>
			</td>
		</tr>
		<tr>
			<td>显示名:<span style="color: red">*</span></td>
			<td>
				<input type="text"  class="Mytext" name="showName" id="showName" value="${member.showName }">
			</td>
		</tr>
		<tr>
			<td>选择角色:<span style="color: red">*</span></td>
			<td>
				<select id="roleId" name="roleId">
					<option value="0">请选择...
					<c:forEach items="${roles }" var="role">
						<option <c:if test="${role.id == member.roleId }">selected="selected"</c:if>  value="${role.id}">${role.name}
					</c:forEach>
				</select>
			</td>
		</tr>
		<tr>
			<td>电话:</td>
			<td>
				<input type="text"  class="Mytext" name="phone" id="phone" value="${member.phone }">
			</td>
		</tr>
		<tr>
			<td>邮箱:</td>
			<td>
				<input type="text"  class="Mytext" name="email" id="email" value="${member.email }">
			</td>
		</tr>
		<c:if test="${empty member}">
		<tr>
			<td>密码:<span style="color: red">*</span></td>
			<td>
				<input type="password"  class="Mytext" name="pwd" id="pwd" value="">
			</td>
		</tr>
		<tr>
			<td>再次输入密码:<span style="color: red">*</span></td>
			<td>
				<input type="password"  class="Mytext" name="pwd2" id="pwd2" value="">
			</td>
		</tr>
		</c:if>
		
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