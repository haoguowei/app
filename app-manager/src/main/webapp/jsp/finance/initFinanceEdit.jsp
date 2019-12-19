
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@include file="../../import.jsp"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title></title>
    <script type="text/javascript" src="<%=syspath%>/js/financeEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form1" action="saveProjects.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">

        <table class="Mytable">
           <tr>
                <td>所属项目:<span style="color: red">*</span></td>
                <td>
                    <select id="projects" name="projects">
                        <option value="0">请选择...
                            <c:forEach items="${projectsList }" var="itm">
                        <option <c:if test="${itm.id == itemObj.projects }">selected="selected"</c:if>  value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="100px">收入金额:<span style="color: red">*</span></td>
                <td>
                    <input type="text"  class="Mytext" name="incomeAmount" id="incomeAmount" value="${itemObj.incomeAmount }">
                </td>
            </tr>
            <tr>
                <td width="100px">支出金额:<span style="color: red">*</span></td>
                <td>
                    <input type="text"  class="Mytext" name="payoutAmount" id="payoutAmount" value="${itemObj.payoutAmount }">
                </td>
            </tr>

            <tr>
                <td>年份:<span style="color: red">*</span></td>
                <td>
                    <select id="year" name="year">
                        <option value="0">请选择...
                            <c:forEach items="${yearList }" var="year">
                        <option <c:if test="${year == itemObj.year }">selected="selected"</c:if>  value="${year}">${year}
                            </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>月份:<span style="color: red">*</span></td>
                <td>
                    <select id="month" name="month">
                        <option value="0">请选择...
                            <c:forEach items="${monthList }" var="month">
                        <option <c:if test="${month == itemObj.month }">selected="selected"</c:if>  value="${month}">${month}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>备注</td>
                <td>
                    <input type="text"  class="Mytext" name="remark" id="remark" value="${itemObj.remark }">
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td colspan="2">
                    <input type="submit" value="确定" class="Mybotton">
                    <input type="button" value="取消" class="Mybotton" onclick="_back()">
                </td>
            </tr>
        </table>
    </form>
</div>
</html>
