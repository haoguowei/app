
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
    <script type="text/javascript" src="<%=syspath%>/js/assetsEdit.js"></script>
</head>
<body>
<div id="div_panel_id">
    <form name="form" action="saveAssets.do" method="post" onsubmit="return save()">
        <input type="hidden" id="hideId" name="hideId" value="${itemObj.id }">
        <input type="hidden" id="hideBuyTimeDiv" name="hideBuyTimeDiv"
               value="<fmt:formatDate value="${itemObj.buyTime }" pattern="yyyy-MM-dd" />">

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
                <td>资产类型:<span style="color: red">*</span></td>
                <td>
                    <select id="type" name="type">
                        <option value="0">请选择...
                            <c:forEach items="${assetsTypeMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.type }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="120px">资产名称:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="name" id="name" value="${itemObj.name }">
                </td>
            </tr>
            <tr>
                <td width="120px">资产编号:<span style="color: red">*</span></td>
                <td>
                    <input type="text" class="Mytext" name="number" id="number" value="${itemObj.number }">
                </td>
            </tr>
            <tr>
                <td width="120px">牌照号:</td>
                <td>
                    <input type="text" class="Mytext" name="license" id="license" value="${itemObj.license }">
                </td>
            </tr>

            <tr>
                <td>责任人:<span style="color: red">*</span></td>
                <td>
                    <select id="owner" name="owner">
                        <option value="0">请选择...
                            <c:forEach items="${ownerList }" var="ite">
                        <option <c:if test="${ite.id == itemObj.owner }">selected="selected"</c:if>  value="${ite.id}">${ite.name}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td class="Myfont">
                    采购时间:<span style="color: red">*</span>
                </td>
                <td class="Myfont">
                    <div id="buyTimeDiv"></div>
                </td>
            </tr>

            <tr>
                <td width="120px">品牌:</td>
                <td>
                    <input type="text" class="Mytext" name="brand" id="brand" value="${itemObj.brand }">
                </td>
            </tr>

            <tr>
                <td width="120px">规格型号:</td>
                <td>
                    <input type="text" class="Mytext" name="carType" id="carType" value="${itemObj.carType }">
                </td>
            </tr>


            <tr>
                <td width="120px">转入/借用:</td>
                <td>
                    <input type="text" style="width: 600px" class="Mytext" name="inOut" id="inOut"
                           value="${itemObj.inOut }">
                </td>
            </tr>

            <tr>
                <td width="120px">数量:</td>
                <td>
                    <input type="text" class="Mytext" name="quantity" id="quantity" value="${itemObj.quantity }">
                </td>
            </tr>
            <tr>
                <td width="120px">现况数量:</td>
                <td>
                    <input type="text" class="Mytext" name="quoQuantity" id="quoQuantity"
                           value="${itemObj.quoQuantity }">
                </td>
            </tr>

            <tr>
                <td width="120px">存放地点:</td>
                <td>
                    <input type="text" class="Mytext" name="storageLocation" id="storageLocation"
                           value="${itemObj.storageLocation }">
                </td>
            </tr>
            <tr>
                <td width="120px">发动机号:</td>
                <td>
                    <input type="text" class="Mytext" name="engineNumber" id="engineNumber"
                           value="${itemObj.engineNumber }">
                </td>
            </tr>
            <tr>
                <td width="120px">发动机号牌种类:</td>
                <td>
                    <select id="engineNumberType" name="engineNumberType">
                        <option value="0">请选择...
                            <c:forEach items="${engineNumberTypeMap }" var="itm">
                        <option
                                <c:if test="${itm.key == itemObj.engineNumberType }">selected="selected"</c:if>
                                value="${itm.key}">${itm.value}
                            </c:forEach>
                    </select>
                </td>
            </tr>

            <tr>
                <td width="120px">单价（元）:</td>
                <td>
                    <input type="text" class="Mytext" name="price" id="price" value="${itemObj.price }">
                </td>
            </tr>

            <tr>
                <td width="120px">分期月数:</td>
                <td>
                    <input type="text" class="Mytext" name="staging" id="staging" value="${itemObj.staging }">
                </td>
            </tr>
            <tr>
                <td width="120px">购置税（元）:</td>
                <td>
                    <input type="text" class="Mytext" name="purTax" id="purTax" value="${itemObj.purTax }">
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
