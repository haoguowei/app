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
            width: 1200px;
            max-width: 2000px;
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
<h1>综合费用</h1>
<hr>

<h4>
    选择项目：
    <select id="projectsId" name="projectsId">
        <c:forEach items="${projectsList }" var="itm">
        <option
                <c:if test="${itm.id == projectsId }">selected="selected"</c:if>
                value="${itm.id}">${itm.name}
            </c:forEach>
    </select>

    报表月份：
    <select id="fromYear" name="fromYear">
        <c:forEach items="${yearList }" var="itm">
            <option
                    <c:if test="${itm == fromYear }">selected="selected"</c:if>
                    value="${itm }">${itm }</option>
        </c:forEach>
    </select>年
    <select id="fromMonth" name="fromMonth">
        <c:forEach items="${monthList }" var="itm">
            <option
                    <c:if test="${itm == fromMonth }">selected="selected"</c:if>
                    value="${itm }">${itm }</option>
        </c:forEach>
    </select>月
    ~
    <select id="toYear" name="toYear">
        <c:forEach items="${yearList }" var="itm">
            <option
                    <c:if test="${itm == toYear }">selected="selected"</c:if>
                    value="${itm }">${itm }</option>
        </c:forEach>
    </select>年
    <select id="toMonth" name="toMonth">
        <c:forEach items="${monthList }" var="itm">
            <option
                    <c:if test="${itm == toMonth }">selected="selected"</c:if>
                    value="${itm }">${itm }</option>
        </c:forEach>
    </select>月
    <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
    <input style="margin-left: 20px;" type="button" value="导出Excel" class="Mybotton" onclick="exportCostTable()">
</h4>


<div style="overflow: auto;margin-bottom: 15px;" id="m_div_id">
    <table>
        <tr>
            <th style="width: 350px;" colspan="3">
                核算项
            </th>
            <c:forEach items="${allMonth }" var="itm">
                <th style="width: 170px;" colspan="2">
                        ${WebUtils.getMonthName(itm) }
                </th>
            </c:forEach>
        </tr>
        <tr style="background-color: #E6E6FA;">
            <td colspan="3">
                合同收入
            </td>
            <c:forEach items="${allMonth }" var="month">
                <td align="right" style="width: 84px;">
                        ${WebUtils.getMshouru(month, incomeTable)}
                </td>
                <td align="right">
                    占比
                </td>
            </c:forEach>
        </tr>

        <c:forEach items="${allTypeList }" var="typeItem">
            <tr style="${typeItem.background} ">
                <td align="left" colspan="${typeItem.colpan}" rowspan="${typeItem.rowspan}">
                        ${typeItem.name1 }
                </td>

                <c:if test="${typeItem.id > 0}">
                    <td align="left" colspan="${typeItem.colpan}" rowspan="${typeItem.rowspan}">
                            ${typeItem.name2 }
                    </td>
                    <td align="left" colspan="${typeItem.colpan}" rowspan="${typeItem.rowspan}">
                            ${typeItem.name3 }
                    </td>
                </c:if>
                <c:if test="${typeItem.id < 0}">
                    <td align="center" colspan="2">
                            ${typeItem.name2 }
                    </td>
                </c:if>


                <c:forEach items="${allMonth }" var="month">
                    <td align="right">
                        <c:set var="tmp"
                               value="${WebUtils.getCostAmount(month, typeItem.id, typeItem.leafIds, costTable, incomeTable)}"/>
                            ${tmp}
                    </td>
                    <td align="right">
                            ${WebUtils.getZhanbi(month, tmp, incomeTable)}
                    </td>
                </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
<script type="text/javascript">
    var height = $(window).height() - 130;
    document.getElementById('m_div_id').style.height = height + "px";

    function searchFunc() {
        var fromYear = document.getElementById("fromYear").value;
        var fromMonth = document.getElementById("fromMonth").value;
        var toYear = document.getElementById("toYear").value;
        var toMonth = document.getElementById("toMonth").value;
        var projectsId = document.getElementById("projectsId").value;
        window.location.href = "initCostsTable.do?fromYear=" + fromYear
            + "&fromMonth=" + fromMonth
            + "&toYear=" + toYear
            + "&toMonth=" + toMonth
            + "&projectsId=" + projectsId
            + "&t=" + new Date().getTime();
    }

    function exportCostTable() {
        var fromYear = document.getElementById("fromYear").value;
        var fromMonth = document.getElementById("fromMonth").value;
        var toYear = document.getElementById("toYear").value;
        var toMonth = document.getElementById("toMonth").value;
        var projectsId = document.getElementById("projectsId").value;
        window.location.href = "exportCostTable.do?fromYear=" + fromYear
            + "&fromMonth=" + fromMonth
            + "&toYear=" + toYear
            + "&toMonth=" + toMonth
            + "&projectsId=" + projectsId
            + "&t=" + new Date().getTime();
    }

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            searchFunc();
        }
    });

</script>
</html>