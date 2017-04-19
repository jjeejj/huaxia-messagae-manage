<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>市场产品管理</title>
	<meta name="decorator" content="default"/>
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
		<li class="active"><a href="${ctx}/mms/marketProduct/">市场产品列表</a></li>
		<shiro:hasPermission name="mms:marketProduct:edit"><li><a href="${ctx}/mms/marketProduct/form">市场产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/marketProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>类别：</label>
				<form:select path="marketProduct.productType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
				<th>申请企业地址</th>
				<th>申请企业电话</th>
				<th>申请企业联系人</th>
				<th>实际生产企业</th>
				<th>实际生产企业地址</th>
				<th>在华责任单位</th>
				<th>在华责任单位地址</th>
				<th>在华责任单位电话</th>
				<th>在华责任单位传真</th>
				<th>在华责任单位邮编</th>
				<th>立项时间</th>
				<th>合同编号</th>
				<th>合同签订时间</th>
				<th>来款单位</th>
				<th>来款时间</th>
				<th>来样时间</th>
				<th>样品数量</th>
				<th>行政许可送检时间</th>
				<th>行政许可检验机构</th>
				<th>行政许可检验项目</th>
				<th>行政许可报告到达时间</th>
				<th>人体检验送检时间</th>
				<th>人体检验机构</th>
				<th>人体检验项目</th>
				<th>人体报告到达时间</th>
				<th>风险检验送检时间</th>
				<th>风险检验机构</th>
				<th>风险检验项目</th>
				<th>风险报告到达时间</th>
				<th>申报时间</th>
				<th>取得批件时间</th>
				<th>状态备注</th>
				<th>下意见时间</th>
				<th>回复时间</th>
				<th>其他说明</th>
				<shiro:hasPermission name="mms:marketProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td><a href="${ctx}/mms/marketProduct/form?id=${product.marketProductId}">
					${product.marketProduct.productNumber}
				</a></td>
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
						${product.marketProduct.enterpriseApplicationAddress}
				</td>
				<td>
						${product.marketProduct.enterpriseApplicationPhone}
				</td>
				<td>
						${product.marketProduct.enterpriseApplicationContacts}
				</td>
				<td>
						${product.marketProduct.actualProductionEnterprise}
				</td>
				<td>
						${product.marketProduct.actualProductionEnterpriseAddress}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChina}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaAddress}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaPhone}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaFax}
				</td>
				<td>
						${product.marketProduct.responsibleUnitInChinaZipCode}
				</td>
				<td>
					<fmt:formatDate value="${product.marketProduct.projectTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.marketProduct.contractNumber}
				</td>
				<td>
					<fmt:formatDate value="${product.marketProduct.contractSigningTime}" pattern="yyyy-MM-dd"/>
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
					<fmt:formatDate value="${product.declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${product.declareProduct.administrativeLicenseInspectionOrganization}
				</td>
				<td>
						${product.declareProduct.administrativeLicenseInspectionProject}
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
						${product.declareProduct.sendBodyProject}
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
						${product.declareProduct.sendRiskTestProject}
				</td>
				<td>
					<fmt:formatDate value="${product.comprehensiveProduct.riskTestAcceptanceReportTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.reportTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.documentTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${fns:getDictLabel(product.declareProduct.productStatusRemark, 'product_status_remark', '')}
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					<fmt:formatDate value="${product.declareProduct.replyOpinion}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
					${product.declareProduct.otherDescription}
				</td>
				<shiro:hasPermission name="mms:marketProduct:edit"><td>
    				<a href="${ctx}/mms/marketProduct/form?id=${product.marketProductId}">修改</a>
					<a href="${ctx}/mms/marketProduct/delete?id=${product.marketProductId}" onclick="return confirmx('确认要删除该产品吗(包括其他部门该产品数据)？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>