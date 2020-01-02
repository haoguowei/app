


<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@include file="../../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

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

    <script type="text/javascript" src="<%=syspath%>/js/finance.js"></script>
</head>
<body>

<input type="hidden" id="hideA" name="hideA" value="${startDay }">
<input type="hidden" id="hideB" name="hideB" value="${endDay }">

<div id="search_div_id">
    <table class="Mytable">
        <tr>
            <td class="Myfont">
                <div style="float:left; ">选择项目：</div>
                <div style="float:left; ">
                    <select id="projectsId" name="projectsId">
                            <c:forEach items="${projectsList }" var="itm">
                        <option value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </div>

                <div style="float:left; ">清单时间，从：</div>
                <div id="fromDIV" style="float:left; "></div>
                <div style="float:left; ">到：</div>
                <div id="endDIV" style="float:left; "></div>

                <div style="float:left; ">
                    <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
                </div>
            </td>
        </tr>
    </table>
</div>
</body>
</html>
