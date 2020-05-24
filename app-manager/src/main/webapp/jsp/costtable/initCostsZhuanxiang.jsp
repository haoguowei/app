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
<h1 style="font-size: 25px;">专项费用</h1>
<hr>


<input type="hidden" id="hideFromDateDiv" name="hideFromDateDiv" value="${fromDate }">
<input type="hidden" id="hideToDateDiv" name="hideToDateDiv" value="${toDate }">

<table>
    <tr>
        <td>
            <div style="float:left; "> 报表日期：</div>
            <div id="fromDateDiv" style="float:left; "></div>
            <div style="float:left; ">~</div>
            <div id="toDateDiv" style="float:left; "></div>


            <div style="float:left; "> 费用类型：</div>
            <div style="float:left; ">
                <select id="type1" name="type1" onchange="selectType2()">
                    <option value="0">请选择...</option>
                    <c:forEach items="${type1List }" var="itm">
                    <option
                            <c:if test="${itm.id == type1 }">selected="selected"</c:if>
                            value="${itm.id}">${itm.name}
                        </c:forEach>
                </select>
            </div>
            <div style="float:left; ">
                <select id="type2" name="type2" onchange="selectType3()">
                    <option value="0">请选择...</option>
                    <c:forEach items="${type2List }" var="itm">
                    <option
                            <c:if test="${itm.id == type2 }">selected="selected"</c:if>
                            value="${itm.id}">${itm.name}
                        </c:forEach>
                </select>
            </div>
            <div style="float:left; ">
                <select id="type3" name="type3">
                    <option value="0">请选择...</option>
                    <c:forEach items="${type3List }" var="itm">
                    <option
                            <c:if test="${itm.id == type3 }">selected="selected"</c:if>
                            value="${itm.id}">${itm.name}
                        </c:forEach>
                </select>
            </div>


            <div style="float:left; "><input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton"
                                             onclick="searchFunc()">
            </div>
            <div style="float:left; "><input style="margin-left: 20px;" type="button" value="导出Excel" class="Mybotton"
                                             onclick="exportCostTableMonth()"></div>

        </td>
    </tr>
</table>

<div style="overflow: auto;margin-bottom: 15px;" id="m_div_id">
    <table class="mytable">
        <tr>
            <th align="center" style="width: 100px;">
                项目名称
            </th>
            <c:forEach items="${allMonth }" var="month">
                <th align="center" style="width: 100px;">
                        ${WebUtils.getMonthName(month) }
                </th>
            </c:forEach>
        </tr>

        <c:forEach items="${projectsList }" var="project">
            <tr style="${project.id == 0 ? 'background-color: #D8BFD8;' : '' } ">
                <td align="left">
                        ${project.name }
                </td>

                <c:forEach items="${allMonth }" var="month">
                    <td align="right">
                            ${WebUtils.getZhuanxiangAmount(project.id, month, costTable)}
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
        var fromDate = document.getElementById("fromDate").value;
        var toDate = document.getElementById("toDate").value;

        var type1 = document.getElementById("type1").value;
        var type2 = document.getElementById("type2").value;
        var type3 = document.getElementById("type3").value;

        if (fromDate == null || fromDate == '') {
            alert("请选择开始日期");
            return;
        }
        if (toDate == null || toDate == '') {
            alert("请选择结束日期");
            return;
        }

        if (type3 == null || type3 == '' || type3 == 0 || type3 == '0') {
            alert("请选择费用类型");
            return;
        }

        window.location.href = "initCostsZhuanxiang.do?fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&type1=" + type1
            + "&type2=" + type2
            + "&type3=" + type3
            + "&first=1"
            + "&t=" + new Date().getTime();
    }

    function exportCostTableMonth() {
        var fromDate = document.getElementById("fromDate").value;
        var toDate = document.getElementById("toDate").value;

        var type1 = document.getElementById("type1").value;
        var type2 = document.getElementById("type2").value;
        var type3 = document.getElementById("type3").value;

        if (fromDate == null || fromDate == '') {
            alert("请选择开始日期");
            return;
        }
        if (toDate == null || toDate == '') {
            alert("请选择结束日期");
            return;
        }
        // if (type3 == null || type3 == '' || type3 == 0 || type3 == '0') {
        //     alert("请选择费用类型");
        //     return;
        // }

        window.location.href = "exportCostZhuanxiang.do?fromDate=" + fromDate
            + "&toDate=" + toDate
            + "&type1=" + type1
            + "&type2=" + type2
            + "&type3=" + type3
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


        this.selectType2 = function () {
            var type1 = document.getElementById("type1").value;
            $("#type2").empty();
            $("#type2").append("<option value='0'>请选择...</option>");

            $("#type3").empty();
            $("#type3").append("<option value='0'>请选择...</option>");

            if (type1 == 0) {
                return;
            }

            Ext.Ajax.request({
                url: 'getTypeListByParentId.do?parentId=' + type1,
                success: function (response) {
                    var resp = Ext.util.JSON.decode(response.responseText);
                    if (resp.success) {
                        resp.info.forEach(function (v) {
                            $("#type2").append("<option value='" + v.id + "'>" + v.name + "</option>");
                        })
                    } else {
                        alert("消费类型获取失败");
                    }
                }
            });
        };

        this.selectType3 = function () {
            var type2 = document.getElementById("type2").value;
            $("#type3").empty();
            $("#type3").append("<option value='0'>请选择...</option>");

            if (type2 == 0) {
                return;
            }

            Ext.Ajax.request({
                url: 'getTypeListByParentId.do?parentId=' + type2,
                success: function (response) {
                    var resp = Ext.util.JSON.decode(response.responseText);
                    if (resp.success) {
                        resp.info.forEach(function (v) {
                            $("#type3").append("<option value='" + v.id + "'>" + v.name + "</option>");
                        })
                    } else {
                        alert("消费类型获取失败");
                    }
                }
            });
        };

    });

</script>
</html>