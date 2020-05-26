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
            width: 100%;
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
    <script src="<%=syspath%>/canvasjs/canvasjs.min.js"></script>
</head>
<body>
<h1 style="font-size: 25px;">费用汇总</h1>
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

        </td>
    </tr>
</table>

<div style="overflow: auto;margin-bottom: 15px;" id="m_div_id">
    <table class="mytable">
        <tr>
            <td style="width: 50%;padding-top: 30px;padding-bottom: 20px;">
                <c:if test="${data1 != '[]'}">
                    <div id="chartContainer1" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>
                </c:if>
                <c:if test="${data1 == '[]'}">
                    暂无数据
                    <div id="chartContainer1" hidden style="height: 370px; max-width: 920px; margin: 0px auto;">

                    </div>
                </c:if>
            </td>
            <td style="width: 50%;padding-top: 30px;padding-bottom: 20px;">
                <c:if test="${data2 != '[]'}">
                    <div id="chartContainer2" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>
                </c:if>
                <c:if test="${data2 == '[]'}">
                    暂无数据
                    <div id="chartContainer2" hidden style="height: 370px; max-width: 920px; margin: 0px auto;">

                    </div>
                </c:if>
            </td>
        </tr>
        <tr>
            <td style="width: 100%;padding-top: 30px;padding-bottom: 20px;" colspan="2">
                <c:if test="${data3 != '[]'}">
                    <div id="chartContainer3" style="height: 370px; max-width: 920px; margin: 0px auto;"></div>
                </c:if>
                <c:if test="${data3 == '[]'}">
                    暂无数据
                    <div id="chartContainer3" hidden style="height: 370px; max-width: 920px; margin: 0px auto;">

                    </div>
                </c:if>
            </td>

        </tr>
    </table>
</div>

</body>
<script type="text/javascript">
    var height = $(window).height() - 90;
    document.getElementById('m_div_id').style.height = height + "px";

    function searchFunc() {
        var fromDate = document.getElementById("fromDate").value;
        var toDate = document.getElementById("toDate").value;
        var projectsId = document.getElementById("projectsId").value;

        if (fromDate == null || fromDate == '') {
            alert("请选择开始日期");
            return;
        }
        if (toDate == null || toDate == '') {
            alert("请选择结束日期");
            return;
        }

        window.location.href = "initCostCharts.do?fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&projectsId=" + projectsId
            + "&default=1"
            + "&t=" + new Date().getTime();
    }

    $(document).keyup(function (event) {
        if (event.keyCode == 13) {
            searchFunc();
        }
    });


    window.onload = function () {

        var dataPoints = [];

        var chart = new CanvasJS.Chart("chartContainer1", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: false,
            animationEnabled: true,
            title: {
                text: '${title1}'
            },
            data: [{
                type: "pie",
                startAngle: 25,
                toolTipContent: "<b>{label}</b>: {pay}元（{y}%）",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 14,
                indexLabel: "{label} - {pay}元",
                dataPoints: ${data1}
            }]
        });

        // dataPoints.push({
        //     label: 'aaa',
        //     y: 100
        // });

        var chart2 = new CanvasJS.Chart("chartContainer2", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: false,
            animationEnabled: true,
            title: {
                text: '${title2}'
            },
            data: [{
                type: "pie",
                startAngle: 25,
                toolTipContent: "<b>{label}</b>: {pay}元（{y}%）",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 14,
                indexLabel: "{label} - {pay}元",
                dataPoints: ${data2}
            }]
        });

        var chart3 = new CanvasJS.Chart("chartContainer3", {
            exportEnabled: false,
            animationEnabled: true,
            dataPointMaxWidth: 45,
            title: {
                text: "${title3}"
            },
            subtitles: [{
                text: ""
            }],
            axisX: {
                title: ""
            },
            axisY: {
                title: "金额（元）",
                titleFontColor: "#4F81BC",
                lineColor: "#4F81BC",
                labelFontColor: "#4F81BC",
                tickColor: "#4F81BC"
            },
            axisY2: {
                title: "金额（元）",
                titleFontColor: "#C0504E",
                lineColor: "#C0504E",
                labelFontColor: "#C0504E",
                tickColor: "#C0504E"
            },
            toolTip: {
                shared: true
            },
            legend: {
                cursor: "pointer",
                itemclick: toggleDataSeries
            },
            data: [{
                type: "column",
                name: "收入",
                showInLegend: true,
                yValueFormatString: "#,##0.## 元",
                indexLabelFontSize: 14,
                indexLabel: "{y}",
                dataPoints: ${data3}
            },
                {
                    type: "column",
                    name: "费用",
                    // axisYType: "secondary",
                    showInLegend: true,
                    yValueFormatString: "#,##0.## 元",
                    indexLabelFontSize: 14,
                    indexLabel: "{y}",
                    dataPoints: ${data4}
                }]
        });

        chart.render();
        chart2.render();
        chart3.render();

        function toggleDataSeries(e) {
            if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
                e.dataSeries.visible = false;
            } else {
                e.dataSeries.visible = true;
            }
            e.chart.render();
        }

    }

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