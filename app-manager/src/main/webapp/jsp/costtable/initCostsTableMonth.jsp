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
            height: 30px;
        }

        h4 {
            color: blue;
        }

    </style>
    <title>
    </title>
    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>

</head>
<body>
<h1>月度费用</h1>
<hr>

<h4>
    报表月份：

</h4>


<table style="height: 40px;">
    <tr>
        <th style="width: 350px;" colspan="3">
            核算项
        </th>
        <c:forEach items="${projectsList }" var="project">
            <th style="width: 90px;">
                    ${project.name }
            </th>
        </c:forEach>
    </tr>
</table>
<div style="overflow: auto;margin-bottom: 15px;" id="m_div_id">
    <table>
        <tr>
            <td style="width: 350px;" colspan="3">
                合同收入
            </td>
            <c:forEach items="${projectsList }" var="project">
                <td align="right" style="width: 90px;">
                        ${WebUtils.getIncomeAmount(project.id, incomeTable)}
                </td>
            </c:forEach>
        </tr>

        <c:forEach items="${allTypeList }" var="typeItem">
            <tr>
                <td align="left" type1="${typeItem.id }">
                        ${typeItem.name1 }
                </td>
                <td align="left" type2="${typeItem.id }">
                        ${typeItem.name2 }
                </td>
                <td align="left" type3="${typeItem.id }">
                        ${typeItem.name3 }
                </td>

                <c:forEach items="${projectsList }" var="project">
                    <td align="right">
                            ${WebUtils.getCostAmount(project.id, typeItem.id, typeItem.leafIds, costTable)}
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
<script type="text/javascript">
    var height = $(window).height() - 170;
    document.getElementById('m_div_id').style.height = height + "px";


</script>
</html>