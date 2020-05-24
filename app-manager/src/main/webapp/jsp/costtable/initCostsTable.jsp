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

        .mytable {
            border: 1px solid #99bbe8;
            border-collapse: collapse;
            text-align: center;
            width: 1200px;
            max-width: 2000px;
        }

        .mytable td {
            border: 1px solid #ededed;
            height: 30px;
        }

        .mytable th {
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

    <link rel="stylesheet" type="text/css" href="<%=syspath%>/ext-3.2.1/resources/css/ext-all.css"/>
    <script type="text/javascript" src="<%=syspath%>/ext-3.2.1/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=syspath%>/ext-3.2.1/ext-all.js"></script>
    <script type="text/javascript" src="<%=syspath%>/ext-3.2.1/ext-lang-zh_CN.js"></script>
    <script type="text/javascript" src="<%=syspath%>/ext-3.2.1/ext-copy.js"></script>

    <script type="text/javascript" src="<%=syspath%>/utils/custom.js"></script>
    <script type="text/javascript" src="<%=syspath%>/utils/myUtil.js"></script>
    <script type="text/javascript" src="<%=syspath%>/utils/validatorRegex.js"></script>

    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>

</head>
<body>
<h1 style="font-size: 25px;">综合费用</h1>
<hr>

<input type="hidden" id="hideFromDateDiv" name="hideFromDateDiv" value="${fromDate }">
<input type="hidden" id="hideToDateDiv" name="hideToDateDiv" value="${toDate }">

<table>
    <tr>
        <td>
            <div style="float:left; "> 选择项目：</div>
            <div style="float:left; ">
                <select id="projectsId" name="projectsId">
                    <c:forEach items="${projectsList }" var="itm">
                    <option
                            <c:if test="${itm.id == projectsId }">selected="selected"</c:if>
                            value="${itm.id}">${itm.name}
                        </c:forEach>
                </select>
            </div>
            <div style="float:left; "> 报表日期：</div>
            <div id="fromDateDiv" style="float:left; "></div>
            <div style="float:left; ">~</div>
            <div id="toDateDiv" style="float:left; "></div>
            <div style="float:left; "><input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton"
                                             onclick="searchFunc()">
            </div>
            <div style="float:left; "><input style="margin-left: 20px;" type="button" value="导出Excel" class="Mybotton"
                                             onclick="exportCostTable()"></div>

        </td>
    </tr>
</table>

<div style="overflow: auto;margin-bottom: 15px;" id="m_div_id">
    <table class="mytable">
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
    var height = $(window).height() - 90;
    document.getElementById('m_div_id').style.height = height + "px";

    function searchFunc() {
        var projectsId = document.getElementById("projectsId").value;
        var fromDate = document.getElementById("fromDate").value;
        var toDate = document.getElementById("toDate").value;

        if (fromDate == null || fromDate == '') {
            alert("请选择开始日期");
            return;
        }
        if (toDate == null || toDate == '') {
            alert("请选择结束日期");
            return;
        }

        window.location.href = "initCostsTable.do?fromDate=" + fromDate
            + "&projectsId=" + projectsId
            + "&toDate=" + toDate
            + "&first=1"
            + "&t=" + new Date().getTime();
    }

    function exportCostTable() {
        var projectsId = document.getElementById("projectsId").value;
        var fromDate = document.getElementById("fromDate").value;
        var toDate = document.getElementById("toDate").value;

        if (fromDate == null || fromDate == '') {
            alert("请选择开始日期");
            return;
        }
        if (toDate == null || toDate == '') {
            alert("请选择结束日期");
            return;
        }

        window.location.href = "exportCostTable.do?fromDate=" + fromDate
            + "&projectsId=" + projectsId
            + "&toDate=" + toDate
            + "&first=1"
            + "&t=" + new Date().getTime();
    }

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            searchFunc();
        }
    });


    Ext.onReady(function () {
        new com.custom.DateField({
            renderTo: 'fromDateDiv',
            format: 'Y-m-d',
            name: 'fromDate',
            value: document.getElementById("hideFromDateDiv").value,
            id: 'fromDate'
        });
        new com.custom.DateField({
            renderTo: 'toDateDiv',
            format: 'Y-m-d',
            name: 'toDate',
            value: document.getElementById("hideToDateDiv").value,
            id: 'toDate'
        });
    });

</script>
</html>