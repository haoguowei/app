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
                选择项目：
                <select id="projectsId" name="projectsId">
                    <c:forEach items="${projectsList }" var="itm">
                    <option value="${itm.id}">${itm.name}
                        </c:forEach>
                </select>

                资产名称：
                <select id="nameId" name="nameId">
                    <option value="0">请选择...
                        <c:forEach items="${carNameMap }" var="itm">
                    <option value="${itm.key}">${itm.value}
                        </c:forEach>
                </select>

                车型：
                <select id="carType" name="carType">
                    <option value="0">请选择...
                        <c:forEach items="${carTypeMap }" var="itm">
                    <option
                            value="${itm.key}">${itm.value}
                        </c:forEach>
                </select>

                品牌：
                <select id="brand" name="brand">
                    <option value="0">请选择...
                        <c:forEach items="${carBrandMap }" var="itm">
                    <option
                            value="${itm.key}">${itm.value}
                        </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td class="Myfont">
                <div style="float:left; ">采购时间，从：</div>
                <div id="buyTimeStartDIV" style="float:left; "></div>
                <div style="float:left; ">到：</div>
                <div id="buyTimeEndDIV" style="float:left; "></div>

                <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
                <%--                <input style="margin-left: 50px;" type="button" value="导出资产" class="Mybotton" onclick="exportFunc()">--%>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
