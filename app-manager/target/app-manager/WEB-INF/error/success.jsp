<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>操作成功</title>
<script type="text/javascript" src="<%=request.getContextPath()%>/utils/myUtil.js"></script>
</head>
<body>
	<h1>操作成功</h1><hr>
	<c:if test="${not empty title }">
		<div style="display:">
			<span id="timer" style="color: red;">3</span> <span>秒后自动跳转到</span> 
			<a href="${url}" style="text-decoration:none;">《${title}》</a>
		</div>

		<script type="text/javascript">
			var total = 2;
			setTimeout(execute, 1000);
			function execute() {
				if (total < 0) {
					window.location.href = '${url}';
				} else {
					document.getElementById('timer').innerHTML = total--;
					setTimeout(execute, 1000);
				}
			}
		</script>
	</c:if>

</body>
</html>