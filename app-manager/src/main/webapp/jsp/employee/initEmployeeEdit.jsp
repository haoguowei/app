

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
<script type="text/javascript" src="<%=syspath%>/js/projectsEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
<form name="form1" action="saveProjects.do" method="post" onsubmit="return save()">
<input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">

<table class="Mytable">
    <tr>
        <td width="100px">姓名:<span style="color: red">*</span></td>
        <td>
            <input type="text"  class="Mytext" name="name" id="name" value="${itemObj.name }">
        </td>
    </tr>
    
    <tr>
        <td class="Myfont">
            性别：<span style="color: red">*</span>
        </td>
        <td class="Myfont">
            <input type="radio" name="gender" value="0" id="gender0" <c:if test="${itemObj.gender == 0 }">checked="checked"</c:if>>男
            &nbsp;&nbsp;
            <input type="radio" name="gender" value="1" id="gender1" <c:if test="${itemObj.gender == 1 }">checked="checked"</c:if>>女
        </td>
    </tr>
    <tr>
        <td width="100px">手机号:<span style="color: red">*</span></td>
        <td>
            <input type="text"  class="Mytext" name="phone" id="phone" value="${itemObj.phone }">
        </td>
    </tr>

    <tr>
        <td width="100px">身份证号:<span style="color: red">*</span></td>
        <td>
            <input type="text"  class="Mytext" name="idCard" id="idCard" value="${itemObj.idCard }">
        </td>
    </tr>
    <tr>
        <td>所属项目:</td>
        <td>
            <select id="projects" name="projects">
                <option value="0">请选择...
                <c:forEach items="${areaList }" var="area">
                    <option <c:if test="${area.id == itemObj.projects }">selected="selected"</c:if>  value="${area.id}">${area.name}
                </c:forEach>
            </select>
        </td>
    </tr>
    <tr>
        <td>职位:<span style="color: red">*</span></td>
        <td>
            <select id="jobType" name="jobType">
                <option value="0">请选择...
                <option <c:if test="${1 == itemObj.jobType }">selected="selected"</c:if>  value="1">经理
                <option <c:if test="${2 == itemObj.jobType }">selected="selected"</c:if>  value="2">主管
                <option <c:if test="${3 == itemObj.jobType }">selected="selected"</c:if>  value="3">文员
                <option <c:if test="${4 == itemObj.jobType }">selected="selected"</c:if>  value="4">助理
                <option <c:if test="${5 == itemObj.jobType }">selected="selected"</c:if>  value="5">队长
                <option <c:if test="${6 == itemObj.jobType }">selected="selected"</c:if>  value="6">司机
                <option <c:if test="${7 == itemObj.jobType }">selected="selected"</c:if>  value="7">装卸工
                <option <c:if test="${8 == itemObj.jobType }">selected="selected"</c:if>  value="8">保洁员
            </select>
        </td>
    </tr>

    <tr>
        <td width="100px">民族:<span style="color: red">*</span></td>
        <td>
            <input type="text"  class="Mytext" name="ethnic" id="ethnic" value="${itemObj.ethnic }">
        </td>
    </tr>
    
    <tr>
        <td class="Myfont">
            出生日期：<span style="color: red">*</span>
        </td>
        <td class="Myfont">
            <div id="birthDateDiv"></div>
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
        <td class="Myfont">
            离职日期：<span style="color: red">*</span>
        </td>
        <td class="Myfont">
            <div id="leaveDateDiv"></div>
        </td>
    </tr>

    <tr>
        <td>学历</td>
        <td>
            <select id="eduType" name="eduType">
                <option value="0">请选择...
                <option <c:if test="${1 == itemObj.eduType }">selected="selected"</c:if>  value="1">小学
                <option <c:if test="${2 == itemObj.eduType }">selected="selected"</c:if>  value="2">初中
                <option <c:if test="${3 == itemObj.eduType }">selected="selected"</c:if>  value="3">高中
                <option <c:if test="${4 == itemObj.eduType }">selected="selected"</c:if>  value="4">大学
               
            </select>
        </td>
    </tr>


    <tr>
        <td>户口类型</td>
        <td>
            <select id="hukouType" name="hukouType">
                <option value="0">请选择...
                <option <c:if test="${1 == itemObj.hukouType }">selected="selected"</c:if>  value="1">农村
                <option <c:if test="${2 == itemObj.hukouType }">selected="selected"</c:if>  value="2">城镇
               
            </select>
        </td>
    </tr>

    <tr>
        <td>户籍地址</td>
        <td>
            <input type="text"  class="Mytext" name="hujiAddress" id="hujiAddress" value="${itemObj.hujiAddress }">
        </td>
    </tr>
    <tr>
        <td>现住地址</td>
        <td>
            <input type="text"  class="Mytext" name="address" id="address" value="${itemObj.address }">
        </td>
    </tr>


    <tr>
        <td>紧急联系人</td>
        <td>
            <input type="text"  class="Mytext" name="emergencyContact" id="emergencyContact" value="${itemObj.emergencyContact }">
        </td>
    </tr>
    <tr>
        <td>紧急联系人电话</td>
        <td>
            <input type="text"  class="Mytext" name="emergencyContactPhone" id="emergencyContactPhone" value="${itemObj.emergencyContactPhone }">
        </td>
    </tr>

    <tr>
        <td>保险类型</td>
        <td>
            <input type="text"  class="Mytext" name="safeType" id="safeType" value="${itemObj.safeType }">
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
