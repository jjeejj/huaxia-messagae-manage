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
			<tr>
				<td>
					${formulaDetails.sequence}
				</td>
				<td>
					${formulaDetails.standardChineseName}
				</td>
				<td>
					${formulaDetails.inicName}
				</td>
				<td>
					${formulaDetails.rawMaterialContent}
				</td>
				<td>
					${formulaDetails.compoundPercentage}
				</td>
				<td>
					${formulaDetails.actualComponentContent}
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
</body>
</html>