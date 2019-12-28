<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@include file="../../import.jsp" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>
    </title>
    <!-- 上传图片 -->
    <link rel="stylesheet" type="text/css" href="<%=syspath%>/utils/upload/FileUploadField.css"/>
    <script type="text/javascript" src="<%=syspath%>/utils/upload/FileUploadField.js"></script>
    <script type="text/javascript" src="<%=syspath%>/utils/upload/FileUpLoadWindow.js"></script>

    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>
    <script type="text/javascript" src="<%=syspath%>/js/assets.js"></script>
</head>
<body>
<div id="search_div_id">
    <table class="Mytable">
        <tr>
            <td class="Myfont">
                资产名称：
                <input style="margin-right: 20px;" type="text" name="name" id="name">
                资产编号：
                <input style="margin-right: 20px;width: 200px;" type="text" name="number" id="number">
                资产类型：
                <select id="type" name="type">
                    <option value="0">请选择...
                        <c:forEach items="${assetsTypeMap }" var="itm">
                    <option value="${itm.key}">${itm.value}
                        </c:forEach>
                </select>

                <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
            </td>
        </tr>
    </table>
</div>

</body>
</html>
