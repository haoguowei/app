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
    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>
    <script type="text/javascript" src="<%=syspath%>/js/kanban.js"></script>

    <style type="text/css">
        hr {
            border-top: 1px dashed gray;
            height: 1px;
            border-bottom: 0px;
        }
    </style>
</head>
<body>

<input type="hidden" id="hideStartDate" name="hideStartDate" value="${fromDate}">
<input type="hidden" id="hideEndDate" name="hideEndDate" value="${endDate}">

<div id="div_panel_id">
    <table>
        <tr>
            <td style="width: 1000px;">

                <div style="float:left;margin: 10px; ">
                    选择项目：
                    <select id="projects" name="projects">
                        <c:forEach items="${projectsList }" var="itm">
                        <option
                                <c:if test="${itm.id == projectId }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </div>


                <div style="float:left;margin: 10px; ">开始时间：</div>
                <div id="fromDIV" style="float:left;margin: 10px; "></div>

                <div style="float:left;margin: 10px; ">结束时间：</div>
                <div id="endDIV" style="float:left;margin: 10px; "></div>


                <div style="float:left;margin: 10px; ">
                    <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">
                </div>
            </td>
        </tr>
        <tr>
            <td style="height: 30px">
                <hr>
            </td>
        </tr>
        <tr>
            <td>
                <div id="chartContainer" style="height: 400px; max-width: 800px; margin: 0px auto;"></div>
            </td>
        </tr>
        <tr>
            <td style="height: 30px">
                <hr>
            </td>
        </tr>
        <tr>
            <td>
                <div id="chartContainer2" style="height: 500px; max-width: 800px; margin: 0px auto;"></div>
            </td>
        </tr>
        <tr>
            <td style="height: 30px">

            </td>
        </tr>
    </table>

</div>

<script>
    window.onload = function () {

        var dataPoints = [];

        var chart = new CanvasJS.Chart("chartContainer", {
            theme: "light2", // "light1", "light2", "dark1", "dark2"
            exportEnabled: true,
            animationEnabled: true,
            title: {
                text: '${title}',
                titleFontSize: 20
            },
            data: [{
                type: "pie",
                startAngle: 25,
                toolTipContent: "<b>{label}</b>: {pay}元（{y}%）",
                showInLegend: "true",
                legendText: "{label}",
                indexLabelFontSize: 14,
                indexLabel: "{label} - {pay}元",
                dataPoints: ${datas}
            }]
        });

        // dataPoints.push({
        //     label: 'aaa',
        //     y: 100
        // });

        chart.render();

        ////////////////////////////////////////////////////////////////////////////////


        var chart2 = new CanvasJS.Chart("chartContainer2", {
            animationEnabled: true,
            exportEnabled: true,
            theme: "light2",
            title: {
                text: '${title2}'
            },
            axisY: {
                title: "开支（元）",
                titleFontSize: 24
            },
            axisX: {
                title: "",
                titleFontSize: 20
            },
            data: [{
                type: "column",
                yValueFormatString: "#,### 元",
                indexLabelFontSize: 14,
                indexLabel: "{y}",
                dataPoints: ${datas2}
            }]
        });

        chart2.render();
    }
</script>

<script src="<%=syspath%>/canvasjs/canvasjs.min.js"></script>
</body>
</html>