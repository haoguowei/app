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
    <script type="text/javascript" src="<%=syspath%>/utils/jquery.js"></script>
    <script type="text/javascript" src="<%=syspath%>/js/costEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form1" action="saveCosts.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">
        <input type="hidden" id="hideEnterDateDiv" name="hideEnterDateDiv"
               value="<fmt:formatDate value="${itemObj.enterDate }" pattern="yyyy-MM-dd" />">

        <table class="Mytable">
            <tr>
                <td>所属项目:<span style="color: red">*</span></td>
                <td>
                    <select id="projects" name="projects">
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
                    费用日期:<span style="color: red">*</span>
                </td>
                <td class="Myfont">
                    <div id="enterDateDiv"></div>
                </td>
            </tr>

            <tr>
                <td class="Myfont">
                    费用类型:<span style="color: red">*</span>
                </td>
                <td class="Myfont">
                    <select id="type1" name="type1" onchange="selectType2()">
                        <option value="0">请选择...</option>
                        <c:forEach items="${type1List }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.type1 }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                    <select id="type2" name="type2" onchange="selectType3()">
                        <option value="0">请选择...</option>
                        <c:forEach items="${type2List }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.type2 }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                    <select id="type3" name="type3">
                        <option value="0">请选择...</option>
                        <c:forEach items="${type3List }" var="itm">
                        <option
                                <c:if test="${itm.id == itemObj.type3 }">selected="selected"</c:if>
                                value="${itm.id}">${itm.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td>费用金额:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="amount" id="amount"
                           value="${itemObj.amount }">
                </td>
            </tr>

            <tr>
                <td>费用单号:</td>
                <td>
                    <input type="text" class="Mytext" name="numb" id="numb"
                           value="${itemObj.numb }">
                </td>
            </tr>

            <tr>
                <td>费用用途:</td>
                <td>
                    <input type="text" class="Mytext" name="useful" id="useful"
                           value="${itemObj.useful }">
                </td>
            </tr>

            <tr>
                <td>备注:</td>
                <td>
                    <input style="width: 600px" type="text" class="Mytext" name="remark" id="remark"
                           value="${itemObj.remark }">
                </td>
            </tr>

            <tr>
                <td>&nbsp;</td>
                <td colspan="2">
                    <input type="submit" value="保存" class="Mybotton">
                    <input type="button" value="取消" class="Mybotton" onclick="_back()">
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
