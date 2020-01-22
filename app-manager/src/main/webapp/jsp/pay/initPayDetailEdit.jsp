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
    <title></title>
    <script type="text/javascript" src="<%=syspath%>/js/payDetailEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form1" action="saveYYCost.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${payDetail.id }">
        <input type="hidden" id="payId" name="payId" value="${payItem.id }">
        <input type="hidden" id="flag" name="flag" value="${flag }">

        <table class="Mytable">
            <tr>
                <td>工资单:</td>
                <td>
                    <span>${payItem.projectsName} - <fmt:formatDate value="${payItem.payMonth }"
                                                                    pattern="yyyy年MM月"/></span>
                </td>
            </tr>

            <tr>
                <td>员工:<span style="color: red">*</span></td>
                <td>
                    <select id="employeeId" name="employeeId">
                        <option value="0">请选择...
                            <c:forEach items="${employeeList }" var="itm">
                        <option
                                <c:if test="${itm.id == payDetail.empId }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="150px">固定工资:</td>
                <td>
                    <input type="text" class="Mytext" name="fixAmount" id="fixAmount"
                           value="${payDetail.fixAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">加班费:</td>
                <td>
                    <input type="text" class="Mytext" name="jiabanAmount" id="jiabanAmount"
                           value="${payDetail.jiabanAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">绩效费:</td>
                <td>
                    <input type="text" class="Mytext" name="jixiaoAmount" id="jixiaoAmount"
                           value="${payDetail.jixiaoAmount }">
                </td>
            </tr>

            <tr>
                <td width="150px">奖金:</td>
                <td>
                    <input type="text" class="Mytext" name="jiangjinAmount" id="jiangjinAmount"
                           value="${payDetail.jiangjinAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">罚款:</td>
                <td>
                    <input type="text" class="Mytext" name="fakuanAmount" id="fakuanAmount"
                           value="${payDetail.fakuanAmount }">
                </td>
            </tr>

            <tr>
                <td>总金额:</td>
                <td>
                    <span id="totalAmount">${payDetail.totalAmount }</span>
                </td>
            </tr>

            <tr>
                <td>已发金额:</td>
                <td>
                    <input type="text" class="Mytext" name="payedAmount" id="payedAmount"
                           value="${payDetail.payedAmount }">
                </td>
            </tr>

            <tr>
                <td>发放状态:</td>
                <td>
                    <span id="payStatus">${payDetail.payStatus == 1?'是':'否' }</span>
                </td>
            </tr>

            <tr>
                <td>备注:</td>
                <td>
                    <input style="width: 600px" type="text" class="Mytext" name="remark" id="remark"
                           value="${payDetail.remark }">
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
