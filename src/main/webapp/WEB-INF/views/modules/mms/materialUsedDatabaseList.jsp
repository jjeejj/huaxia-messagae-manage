<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>原料使用数据库管理</title>
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
		<li class="active"><a href="${ctx}/mms/materialUsedDatabase/">原料使用数据库列表</a></li>
		<%--<shiro:hasPermission name="mms:materialUsedDatabase:edit"><li><a href="${ctx}/mms/materialUsedDatabase/form">原料使用数据库添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="materialUsedDatabase" action="${ctx}/mms/materialUsedDatabase/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标准中文名称：</label>
				<form:input path="standardChineseName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标准中文名称(即原料名称)</th>
				<th>实际成份含量（%）</th>
				<th>使用目的</th>
				<th>风险物质</th>
				<%--<shiro:hasPermission name="mms:materialUsedDatabase:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="materialUsedDatabase">
			<tr>
				<td>
					${materialUsedDatabase.standardChineseName}
				</td>
				<td>
					${materialUsedDatabase.minActualComponentContent}%-${materialUsedDatabase.maxActualComponentContent}%
				</td>
				<td>
					${materialUsedDatabase.purposesOfUse}
				</td>
				<td>
					${materialUsedDatabase.riskMaterials}
				</td>
				<%--<shiro:hasPermission name="mms:materialUsedDatabase:edit"><td>--%>
    				<%--<a href="${ctx}/mms/materialUsedDatabase/form?id=${materialUsedDatabase.id}">修改</a>--%>
					<%--<a href="${ctx}/mms/materialUsedDatabase/delete?id=${materialUsedDatabase.id}" onclick="return confirmx('确认要删除该原料使用数据库吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>