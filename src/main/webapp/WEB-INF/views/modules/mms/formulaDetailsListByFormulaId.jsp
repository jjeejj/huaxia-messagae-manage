<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>配方详情信息管理</title>
    <meta name="decorator" content="default"/>
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
    <style>
        .form-search .ul-form li label {
            width: 100px;
        }
    </style>
    <script type="text/javascript">
        $(document).ready(function () {

        });
    </script>
</head>
<body>
<div>
    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true" style="background-color: red"></span>禁用成分；
    <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="background-color: yellow"></span>限用成分；
    <span class="glyphicon glyphicon-ok" aria-hidden="true" style="background-color: blue;color: white"></span>不大于限用成分的实际成分含量；
    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: blue;color: red"></span>大于限用成分的实际成分含量；
    <br/>
    <span class="glyphicon glyphicon-tint" aria-hidden="true" style="background-color: orange"></span>可能需要备注具体使用部位的植物成分；
    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: orange;color: red"></span>已使用原料目录信息不一致；
    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: orange"></span>不再已使用原料目录中；
</div>
<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>序号</th>
        <th>标准中文名称</th>
        <th>INCI名</th>
        <th>原料含量（%）</th>
        <th>复配百分比（%）</th>
        <th>实际成份含量（%）</th>
        <th>使用目的</th>
        <th>风险物质</th>
        <th>限用成分备注信息</th>
        <th>备注信息</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${formulaDetailsList}" var="formulaDetails">
        <tr>
            <td>
                <c:if test="${formulaDetails.nameOrInicStatus eq '1'}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: blue;color: red"></span>
                </c:if>
                ${formulaDetails.sequence}
            </td>
            <td>
                <c:if test="${formulaDetails.nameOrInicStatus eq '2' || formulaDetails.nameOrInicStatus eq '4'}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: orange"></span>
                </c:if>
                <c:if test="${formulaDetails.componentType eq '2'}">
                    <span class="glyphicon glyphicon-remove-sign" aria-hidden="true" style="background-color: red"></span>
                </c:if>
                <c:if test="${formulaDetails.componentType eq '3'}">
                    <span class="glyphicon glyphicon-info-sign" aria-hidden="true" style="background-color: yellow"></span>
                </c:if>
                <c:if test="${formulaDetails.plantComponent eq '1'}">
                    <span class="glyphicon glyphicon-tint" aria-hidden="true" style="background-color: orange"></span>
                </c:if>
                    ${formulaDetails.standardChineseName}
            </td>
            <td>
                <c:if test="${formulaDetails.nameOrInicStatus eq '3' || formulaDetails.nameOrInicStatus eq '4'}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: orange"></span>
                </c:if>
                ${formulaDetails.inicName}
            </td>
            <td>
                    ${formulaDetails.rawMaterialContent}
            </td>
            <td>
                    ${formulaDetails.compoundPercentage}
            </td>
            <td>
                <c:if test="${formulaDetails.componentType eq '3' && formulaDetails.actualComponentContentStatus eq '1'}">
                    <span class="glyphicon glyphicon-ok" aria-hidden="true" style="background-color: blue;color: white"></span>
                </c:if>
                <c:if test="${formulaDetails.componentType eq '3' && formulaDetails.actualComponentContentStatus eq '2'}">
                    <span class="glyphicon glyphicon-remove" aria-hidden="true" style="background-color: blue;color: red"></span>
                </c:if>
                    ${formulaDetails.actualComponentContent}
            </td>
            <td>
                    ${formulaDetails.purposeOfUse}
            </td>
            <td>
                    ${formulaDetails.riskMaterial}
            </td>
            <td>
                    ${formulaDetails.limitedRemarks}
            </td>
            <td>
                    ${formulaDetails.remarks}
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

	<div style="margin-top: 8px; margin-right: 20px; float: right">
		总原料含量（%）: ${formula.rawMaterialContentTotal}
		<c:if test="${fn:substring(formula.rawMaterialContentTotal, 0, 3) eq '100'}"><font color="green">√</font></c:if>
		<c:if test="${fn:substring(formula.rawMaterialContentTotal, 0, 3) ne '100'}"><font color="yellow">?</font></c:if>
		;&nbsp;&nbsp;
		总实际成份含量（%）: ${formula.actualComponentContentTotal}
		<c:if test="${fn:substring(formula.actualComponentContentTotal, 0, 3) eq '100'}"><font color="green">√</font></c:if>
		<c:if test="${fn:substring(formula.actualComponentContentTotal, 0, 3) ne '100'}"><font color="yellow">?</font></c:if>
	</div>
</body>
</html>