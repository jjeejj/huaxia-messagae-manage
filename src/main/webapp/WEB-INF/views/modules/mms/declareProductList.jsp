<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申报产品管理</title>
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
		<li class="active"><a href="${ctx}/mms/declareProduct/">申报产品列表</a></li>
		<shiro:hasPermission name="mms:declareProduct:edit"><li><a href="${ctx}/mms/declareProduct/form">申报产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="declareProduct" action="${ctx}/mms/declareProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>送检总数：</label>
				<form:input path="totalSubmission" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>受理编号：</label>
				<form:input path="acceptanceNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>送检总数</th>
				<th>行政许可送检时间</th>
				<th>行政许可检验机构</th>
				<th>人体检验项目</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:declareProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="declareProduct">
			<tr>
				<td><a href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">
					${declareProduct.totalSubmission}
				</a></td>
				<td>
					<fmt:formatDate value="${declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${declareProduct.administrativeLicenseInspectionOrganization}
				</td>
				<td>
					${declareProduct.sendBodyProject}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
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