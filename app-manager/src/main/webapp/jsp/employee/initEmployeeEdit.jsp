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
    <script type="text/javascript" src="<%=syspath%>/js/employeeEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form" action="saveEmployee.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">

        <input type="hidden" id="hideEntryDateDiv" name="hideEntryDateDiv"
               value="<fmt:formatDate value="${itemObj.entryDate }" pattern="yyyy-MM-dd" />">

        <table class="Mytable">
            <tr>
                <td width="100px">姓名:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="name" id="name" value="${itemObj.name }">
                </td>
            </tr>

            <tr>
                <td width="100px">手机号:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="phone" id="phone" value="${itemObj.phone }">
                </td>
            </tr>

            <tr>
                <td width="100px">身份证号:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="idCard" id="idCard" value="${itemObj.idCard }">
                </td>
            </tr>
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
                <td>职位:<span style="color: red">*</span></td>
                <td>
                    <select id="jobType" name="jobType">
                        <option value="0">请选择...
                            <c:forEach items="${jobTypeMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.jobType }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>
                    </select>
                </td>
            </tr>


            <tr>
                <td width="100px">民族:</td>
                <td>
                    <select id="ethnic" name="ethnic">
                        <option value="0">请选择...
                            <c:forEach items="${minzuMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.ethnic }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td class="Myfont">
                    入职日期：<span style="color: red">*</span>
                </td>
                <td class="Myfont">
                    <div id="entryDateDiv"></div>
                </td>
            </tr>
            <tr>
                <td>学历</td>
                <td>
                    <select id="eduType" name="eduType">
                        <option value="0">请选择...
                            <c:forEach items="${xueliMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.eduType }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>
                    </select>
                </td>
            </tr>


            <tr>
                <td>户口类型</td>
                <td>
                    <select id="hukouType" name="hukouType">
                        <option value="0">请选择...
                            <c:forEach items="${hukouTypeMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.hukouType }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>

                    </select>
                </td>
            </tr>

            <tr>
                <td>户籍地址</td>
                <td>
                    <input type="text" class="Mytext" name="hujiAddress" id="hujiAddress"
                           value="${itemObj.hujiAddress }">
                </td>
            </tr>
            <tr>
                <td>现住地址</td>
                <td>
                    <input type="text" class="Mytext" name="address" id="address" value="${itemObj.address }">
                </td>
            </tr>


            <tr>
                <td>紧急联系人</td>
                <td>
                    <input type="text" class="Mytext" name="emergencyContact" id="emergencyContact"
                           value="${itemObj.emergencyContact }">
                </td>
            </tr>
            <tr>
                <td>紧急联系人电话</td>
                <td>
                    <input type="text" class="Mytext" name="emergencyContactPhone" id="emergencyContactPhone"
                           value="${itemObj.emergencyContactPhone }">
                </td>
            </tr>
            <tr>
                <td>入职合同:</td>
                <td>
                    <select id="hetong" name="hetong">
                        <option value="0">请选择...
                        <option
                                <c:if test="${1 == itemObj.hetong }">selected="selected"</c:if>
                                value="1">是
                        <option
                                <c:if test="${0 == itemObj.hetong }">selected="selected"</c:if>
                                value="0">否
                    </select>
                </td>
            </tr>
            <tr>
                <td>保险类型</td>
                <td>
                    <input type="text" class="Mytext" name="safeType" id="safeType" value="${itemObj.safeType }">
                </td>
            </tr>


            <tr>
                <td>备注</td>
                <td>
                    <input type="text" style="width: 600px" class="Mytext" name="remark" id="remark"
                           value="${itemObj.remark }">
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
</body>
</html>
