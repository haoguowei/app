<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作失败</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/utils/myUtil.js"></script>
</head>
<body>
	<h1>操作失败！请重试，或联系管理员！</h1><hr>
	<h3><span style="color: red;">${msg}</span></h3>
	<h4><a href="javascript:void(0)" onclick="_back()" style="text-decoration:none;">返回</a></h4>
</body>
</html>