<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
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
		<li class="active"><a href="${ctx}/mms/product/">产品列表</a></li>
		<%--<shiro:hasPermission name="mms:product:edit"><li><a href="${ctx}/mms/product/form">产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/product/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
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
				<th>类别</th>
				<th>工作事项</th>
				<th>产品负责人</th>
				<th>项目进度</th>
				<th>状态</th>
				<shiro:hasPermission name="mms:product:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="product">
			<tr>
				<td>
						${product.marketProduct.productNumber}
				</td>
				<td>
						${product.marketProduct.chineseName}
				</td>
				<td>
						${product.marketProduct.englishName}
				</td>
				<td>
						${product.marketProduct.productType}
				</td>
				<td>
						${product.marketProduct.workMatters}
				</td>
				<td>
						${product.marketProduct.productLeader}
				</td>
				<td>
						进度
				</td>
				<td>
						${fns:getDictLabels(product.productStatus, 'product_status', '')}
				</td>
				<shiro:hasPermission name="mms:product:edit"><td>
    				<a href="${ctx}/mms/product/form?id=${product.id}">查看</a>
					<%--<a href="${ctx}/mms/product/delete?id=${product.id}" onclick="return confirmx('确认要删除该产品吗？', this.href)">删除</a>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>