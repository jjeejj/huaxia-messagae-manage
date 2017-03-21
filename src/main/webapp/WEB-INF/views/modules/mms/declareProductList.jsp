<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申报产品管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:120px;
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
		<li class="active"><a href="${ctx}/mms/declareProduct/">申报产品列表</a></li>
		<%--<shiro:hasPermission name="mms:declareProduct:edit"><li><a href="${ctx}/mms/declareProduct/form">申报产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="declareProduct" action="${ctx}/mms/declareProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>送人体时间：</label>
				<form:input path="sendBodyTime" htmlEscape="false" maxlength="10" class="input-medium"/>
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
				<th>中文名称</th>
				<th>英文名称</th>
				<th>送检总数</th>
				<th>行政许可检送检时间</th>
				<th>行政许可检验机构</th>
				<th>行政许可检验项目</th>
				<th>行政许可送检数量</th>
				<th>送人体检验时间</th>
				<th>送人体检验机构</th>
				<th>送人体检验项目</th>
				<th>送人体报告数量</th>
				<th>送风险检验时间</th>
				<th>送风险检验机构</th>
				<th>送风险检验项目</th>
				<th>送风险报告数量</th>
				<th>上报时间</th>
				<th>受理时间</th>
				<th>受理编号</th>
				<th>批件时间</th>
				<th>批件编号</th>
				<th>下意见时间</th>
				<th>下意见内容</th>
				<th>回复意见时间</th>
				<th>颜色性状</th>
				<th>样品标记（生产日期或批号）</th>
				<th>保质期或限期使用日期</th>
				<th>规格</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:declareProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="declareProduct">
			<tr>
				<td><a  href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">
						${declareProduct.marketProduct.productNumber}
				</a></td>
				<td>
						${declareProduct.marketProduct.chineseName}
				</td>
				<td>
						${declareProduct.marketProduct.englishName}
				</td>
				<td>
						${declareProduct.totalSubmission}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.administrativeLicenseInspectionOrganization}
				</td>
				<td>
						${declareProduct.administrativeLicenseInspectionProject}
				</td>
				<td>
						${declareProduct.administrativeLicenseInspectionNumber}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.sendBodyOrganization}
				</td>
				<td>
						${declareProduct.sendBodyProject}
				</td>
				<td>
						${declareProduct.sendBodyNumber}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.sendRistTestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.sendRistTestOrganization}
				</td>
				<td>
						${declareProduct.sendRistTestProject}
				</td>
				<td>
						${declareProduct.sendRistTestNumber}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.acceptanceNumber}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.documentNumber}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.opinionContent}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.colorCharacter}
				</td>
				<td>
						${declareProduct.sampleMarking}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.dateOfExpiry}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
						${declareProduct.specifications}
				</td>
				<td>
					${declareProduct.remarks}
				</td>
				<shiro:hasPermission name="mms:declareProduct:edit"><td>
    				<a href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">修改</a>
					<a href="${ctx}/mms/declareProduct/delete?id=${declareProduct.id}" onclick="return confirmx('确认要删除该申报产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>