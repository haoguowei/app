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
    <link href="<%=syspath%>/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
    <link href="<%=syspath%>/css/style.css" rel="stylesheet" type="text/css"/>
    <link href="<%=syspath%>/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>
    <script type="text/javascript" src="<%=syspath%>/js/kanban.js"></script>

    <style type="text/css">
        hr {
            border-top: 1px dashed gray;
            height: 1px;
            border-bottom: 0px;
        }

        table {
            width: 90%;
            margin-left: 5px;
        }

        table td {
            padding: 3px;
            margin: 3px;
        }
    </style>
</head>
<body style="background-color:white;">

<input type="hidden" id="hideStartDate" name="hideStartDate" value="${fromDate}">
<input type="hidden" id="hideEndDate" name="hideEndDate" value="${endDate}">

<div id="div_panel_id">
    <div style="float:left;margin: 10px; ">开始时间：</div>
    <div id="fromDIV" style="float:left;margin: 10px; "></div>

    <div style="float:left;margin: 10px; ">结束时间：</div>
    <div id="endDIV" style="float:left;margin: 10px; "></div>

    <input style="margin-left: 50px;" type="button" value="搜索" class="Mybotton" onclick="searchFunc()">


    <hr>

    <div class="row" style="margin-left: 10px;">

        <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3">
            <div class="card-box noradius noborder bg-default">
                <i class="fa fa-file-text-o float-right text-white"></i>
                <h6 class="text-white text-uppercase m-b-20">总收入：</h6>
                <h2 class="m-b-20 text-white counter">${totalIn}</h2>
                <span class="text-white">单位（元）</span>
            </div>
        </div>

        <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3">
            <div class="card-box noradius noborder bg-warning">
                <i class="fa fa-file-text-o float-right text-white"></i>
                <h6 class="text-white text-uppercase m-b-20">总支出：</h6>
                <h2 class="m-b-20 text-white counter">${totalOut}</h2>
                <span class="text-white">单位（元）</span>
            </div>
        </div>

        <div class="col-xs-12 col-md-6 col-lg-6 col-xl-3">
            <div class="card-box noradius noborder bg-danger">
                <i class="fa fa-bar-chart float-right text-white"></i>
                <h6 class="text-white text-uppercase m-b-20">总净流量：</h6>
                <h2 class="m-b-20 text-white counter">${total}</h2>
                <span class="text-white">单位（元）</span>
            </div>
        </div>

    </div>
</div>

</body>
</html>