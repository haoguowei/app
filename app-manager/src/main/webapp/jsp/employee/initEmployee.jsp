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
    <script type="text/javascript" src="<%=syspath%>/js/employee.js"></script>
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

                姓名：
                <input style="margin-right: 20px;" type="text" name="name" id="name">
                身份证号：
                <input style="margin-right: 20px;width: 200px;" type="text" name="idCard" id="idCard">
            </td>
        </tr>
        <tr>
            <td class="Myfont">
                <div style="float:left; ">入职日期，从：</div>
                <div id="entryDateStartDIV" style="float:left; "></div>
                <div style="float:left; ">到：</div>
                <div id="entryDateEndDIV" style="float:left; "></div>
            </td>
        </tr>
        <tr>
            <td class="Myfont">
                <div style="float:left; ">离职日期，从：</div>
                <div id="leaveDateStartDIV" style="float:left; "></div>
                <div style="float:left; ">到：</div>
                <div id="leaveDateEndDIV" style="float:left; "></div>

                <div style="float:left; ">
                    <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
                </div>
            </td>
        </tr>
    </table>
</div>

</body>
</html>
