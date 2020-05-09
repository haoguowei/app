<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%
    String syspath = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body {
            font: 12px Verdana, Arial, Helvetica, sans-serif;
        }

        table {
            border: 1px solid #99bbe8;
            border-collapse: collapse;
            text-align: center;
        }

        table td {
            border: 1px solid #ededed;
            height: 30px;
        }

        table th {
            border: 1px solid #99bbe8;
            background-color: #dfe8f6;
            padding: 5px;
            height: 30px;
        }

        h3 {
            color: blue;
        }

        h5 {
            color: red;
        }

        select {
            font: 10px Verdana, Arial, Helvetica, sans-serif;
            width: 140px;
            margin: 10px 0px 10px 0px;
        }

        img {
            cursor: pointer;
            margin-left: 5px;
            margin-right: 5px;
        }
    </style>
    <title>
    </title>
    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>

</head>
<body>
<h1>月度费用</h1>

<h4>报表月份：</h4>

<table>
    <tr>
        <th style="width: 280px;">
            核算项
        </th>
        <c:forEach items="${projectsList }" var="project">
            <th style="width: 80px;">
                    ${project.name }
            </th>
        </c:forEach>
    </tr>

    <tr>
        <td align="left">
            合同收入
        </td>
        <c:forEach items="${projectsList }" var="project">
            <td align="right">
                    ${WebUtils.getIncomeAmount(project.id, incomeTable)}
            </td>
        </c:forEach>
    </tr>


</table>
</body>
</html>