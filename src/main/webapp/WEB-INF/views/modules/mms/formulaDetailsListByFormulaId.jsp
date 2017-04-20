<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配方详情信息管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:100px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
	</script>
</head>
<body>
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
				<th>备注信息</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${formulaDetailsList}" var="formulaDetails">
			<c:if test="${formulaDetails.nameOrInicStatus eq '1'}">
				<tr style="background-color: red">
			</c:if>
			<c:if test="${formulaDetails.nameOrInicStatus ne '1'}">
				<tr>
			</c:if>
				<td>
					${formulaDetails.sequence}
				</td>
				<td>
					${formulaDetails.standardChineseName}
					<c:if test="${formulaDetails.nameOrInicStatus eq '2' || formulaDetails.nameOrInicStatus eq '4'}">
						<font color="red">?</font>
					</c:if>
					<c:if test="${formulaDetails.componentType eq '2'}">
						<font color="red">!</font>
					</c:if>
					<c:if test="${formulaDetails.componentType eq '3'}">
						<font color="yellow">!</font>
					</c:if>
					<c:if test="${formulaDetails.plantComponent eq '1'}">
						<font color="yellow">?</font>
					</c:if>
				</td>
				<td>
					${formulaDetails.inicName}
					<c:if test="${formulaDetails.nameOrInicStatus eq '3' || formulaDetails.nameOrInicStatus eq '4'}">
						<font color="red">?</font>
					</c:if>
				</td>
				<td>
					${formulaDetails.rawMaterialContent}
				</td>
				<td>
					${formulaDetails.compoundPercentage}
				</td>
				<td>
					${formulaDetails.actualComponentContent}
					<c:if test="${formulaDetails.componentType eq '3' && formulaDetails.actualComponentContentStatus eq '1'}">
						<font color="green">√</font>
					</c:if>
					<c:if test="${formulaDetails.componentType eq '3' && formulaDetails.actualComponentContentStatus eq '2'}">
						<font color="red">!</font>
					</c:if>
				</td>
				<td>
					${formulaDetails.purposeOfUse}
				</td>
				<td>
					${formulaDetails.riskMaterial}
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