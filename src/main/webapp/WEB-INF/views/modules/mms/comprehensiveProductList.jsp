<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综合产品管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:150px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			
		});
		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mms/comprehensiveProduct/">综合产品列表</a></li>
		<%--<c:if test="${fns:isAdmin()}">--%>
			<%--<shiro:hasPermission name="mms:comprehensiveProduct:edit"><li><a href="${ctx}/mms/comprehensiveProduct/form">综合产品添加</a></li></shiro:hasPermission>--%>
		<%--</c:if>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/comprehensiveProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品编号：</label>
				<form:input path="marketProduct.productNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>行政许可检验受理编号：</label>
				<form:input path="comprehensiveProduct.administrativeLicenseInspectionNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>人体检验受理编号：</label>
				<form:input path="comprehensiveProduct.humanTestAcceptanceNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>风险检验受理编号：</label>
				<form:input path="comprehensiveProduct.riskTestAcceptanceNo" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品编号</th>
				<th>英文名称</th>
				<th>中文名称</th>
				<th>原产国</th>
				<th>类别</th>
				<th>工作事项</th>
				<th>产品负责人</th>
				<th>项目负责人</th>
				<th>申请企业</th>
				<th>立项时间</th>
				<th>合同编号</th>
				<th>来款单位</th>
				<th>来款时间</th>
				<th>来样时间</th>
				<th>样品数量</th>
				<th>生产日期或批号</th>
				<th>保质期或限期使用日期</th>
				<th>保质期</th>
				<th>送检总数</th>
				<th>行政许可送检时间</th>
				<th>行政许可检验机构</th>
				<th>行政许可检验受理编号</th>
				<th>行政许可检验项目</th>
				<th>行政许可送检数量</th>
				<th>行政许可报告到达时间</th>
				<th>人体检验送检时间</th>
				<th>人体检验机构</th>
				<th>人体检验受理编号</th>
				<th>人体检验项目</th>
				<th>人体送检数量</th>
				<th>人体报告到达时间</th>
				<th>风险检验送检时间</th>
				<th>风险检验机构</th>
				<th>风险检验受理编号</th>
				<th>风险检验项目</th>
				<th>风险送检数量</th>
				<th>风险报告到达时间</th>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td>
					<a href="${ctx}/mms/comprehensiveProduct/form?id=${product.comprehensiveProductId}">
						${product.marketProduct.productNumber}
					</a>
				</td>
				<td>
						${product.marketProduct.englishName}
				</td>
				<td>
						${product.marketProduct.chineseName}
				</td>
				<td>
						${product.marketProduct.countryOfOrigin}
				</td>
				<td>
						${fns:getDictLabel(product.marketProduct.productType, 'product_type', '')}
				</td>
				<td>
						${product.marketProduct.workMatters}
				</td>
				<td>
						${product.marketProduct.productLeader}
				</td>
				<td>
						${product.marketProduct.projectLeader}
				</td>
				<td>
						${product.marketProduct.enterpriseApplication}
				</td>
				<td>
					<fmt:formatDate value="${product.marketProduct.projectTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.marketProduct.contractNumber}
				</td>
				<td>
						${product.marketProduct.arrivalCompany}
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.arrivalTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${product.comprehensiveProduct.sampleQuantity}
				</td>
				<td>
						${product.declareProduct.sampleMarking}
				</td>
				<td>
						${product.declareProduct.dateOfExpiry}
				</td>
				<td>
						${product.declareProduct.technologyDateOfExpiry}
				</td>
				<td>
						${product.declareProduct.totalSubmission}
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.declareProduct.administrativeLicenseInspectionOrganization}
				</td>
				<td>
						${product.comprehensiveProduct.administrativeLicenseInspectionNo}
				</td>
				<td>
						${product.declareProduct.administrativeLicenseInspectionProject}
				</td>
				<td>
						${product.declareProduct.administrativeLicenseInspectionNumber}
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.administrativeLicenseInspectionReportTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.sendBodyTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.declareProduct.sendBodyOrganization}
				</td>
				<td>
						${product.comprehensiveProduct.humanTestAcceptanceNo}
				</td>
				<td>
						${product.declareProduct.sendBodyProject}
				</td>
				<td>
						${product.declareProduct.sendBodyNumber}
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.humanTestAcceptanceReportTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.sendRiskTestTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.declareProduct.sendRiskTestOrganization}
				</td>
				<td>
						${product.comprehensiveProduct.riskTestAcceptanceNo}
				</td>
				<td>
						${product.declareProduct.sendRiskTestProject}
				</td>
				<td>
						${product.declareProduct.sendRiskTestNumber}
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.riskTestAcceptanceReportTime}" pattern="yyyy-MM-dd"/>
				</td>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><td>
    				<a href="${ctx}/mms/comprehensiveProduct/form?id=${product.comprehensiveProductId}">修改</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>