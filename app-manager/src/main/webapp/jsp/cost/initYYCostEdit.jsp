

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
    <script type="text/javascript" src="<%=syspath%>/js/costEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form1" action="saveYYCost.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">
        <input type="hidden" id="hideEnterDateDiv" name="hideEnterDateDiv"
               value="<fmt:formatDate value="${itemObj.enterDate }" pattern="yyyy-MM-dd" />">

        <table class="Mytable">
            <tr>
                <td>所属项目:<span style="color: red">*</span></td>
                <td>
                    <select id="projects" name="projects">
                        <option value="0">请选择...
                            <c:forEach items="${projectsList }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.projects }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td class="Myfont">
                    消费日期:<span style="color: red">*</span>
                </td>
                <td class="Myfont">
                    <div id="enterDateDiv"></div>
                </td>
            </tr>
            <tr>
                <td>消费司机:<span style="color: red">*</span></td>
                <td>
                    <select id="employeeId" name="employeeId">
                        <option value="0">请选择...
                            <c:forEach items="${employeeList }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.employeeId }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>
            <tr>
                <td>消费资产:<span style="color: red">*</span></td>
                <td>
                    <select id="assetId" name="assetId">
                        <option value="0">请选择...
                            <c:forEach items="${assetsList }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.assetId }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name} （资产编号：${itm.number} ，牌照号：${itm.license}）
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="150px">日初里程数:</td>
                <td>
                    <input type="text" class="Mytext" name="startMileage" id="startMileage"
                           value="${itemObj.startMileage }">
                </td>
            </tr>
            <tr>
                <td width="150px">日末里程数:</td>
                <td>
                    <input type="text" class="Mytext" name="endMileage" id="endMileage" value="${itemObj.endMileage }">
                </td>
            </tr>
            <tr>
                <td width="150px">作业量:</td>
                <td>
                    <input type="text" class="Mytext" name="workload" id="workload" value="${itemObj.workload }">
                </td>
            </tr>

            <tr>
                <td width="150px">加油量:</td>
                <td>
                    <input type="text" class="Mytext" name="fuel" id="fuel" value="${itemObj.fuel }">
                </td>
            </tr>
            <tr>
                <td width="150px">加油金额(元):</td>
                <td>
                    <input type="text" class="Mytext" name="fuelAmount" id="fuelAmount" value="${itemObj.fuelAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">保养费用(元):</td>
                <td>
                    <input type="text" class="Mytext" name="baoyangAmount" id="baoyangAmount"
                           value="${itemObj.baoyangAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">维修费用(元):</td>
                <td>
                    <input type="text" class="Mytext" name="fixAmount" id="fixAmount" value="${itemObj.fixAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">维修厂:</td>
                <td>
                    <input type="text" class="Mytext" name="fixFactory" id="fixFactory" value="${itemObj.fixFactory }">
                </td>
            </tr>

            <tr>
                <td width="150px">事故次数:</td>
                <td>
                    <input type="text" class="Mytext" name="shiguTimes" id="shiguTimes" value="${itemObj.shiguTimes }">
                </td>
            </tr>
            <tr>
                <td width="150px">事故金额(元):</td>
                <td>
                    <input type="text" class="Mytext" name="shiguAmount" id="shiguAmount"
                           value="${itemObj.shiguAmount }">
                </td>
            </tr>
            <tr>
                <td width="150px">事故保险外赔偿金额(元):</td>
                <td>
                    <input type="text" class="Mytext" name="shiguOutAmount" id="shiguOutAmount"
                           value="${itemObj.shiguOutAmount }">
                </td>
            </tr>

            <tr>
                <td width="150px">保险费用(元):</td>
                <td>
                    <input type="text" class="Mytext" name="baoxianAmount" id="baoxianAmount"
                           value="${itemObj.baoxianAmount }">
                </td>
            </tr>

            <tr>
                <td width="150px">年检费用(元):</td>
                <td>
                    <input type="text" class="Mytext" name="yearCheckAmount" id="yearCheckAmount" value="${itemObj.yearCheckAmount }">
                </td>
            </tr>

            <tr>
                <td>备注:</td>
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
