<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>原料使用数据库管理</title>
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
		<li class="active"><a href="${ctx}/mms/materialUse/">原料使用数据库列表</a></li>
		<shiro:hasPermission name="mms:materialUse:edit"><li><a href="${ctx}/mms/materialUse/form">原料使用数据库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="materialUse" action="${ctx}/mms/materialUse/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>原料序号（导入配方详情的每一项的序号）：</label>
				<form:input path="sequence" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
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
				<th>原料序号（导入配方详情的每一项的序号）</th>
				<th>标准中文名称</th>
				<th>最小实际成份含量（%）</th>
				<th>最大实际成份含量（%）</th>
				<th>使用目的</th>
				<th>风险物质</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:materialUse:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="materialUse">
			<tr>
				<td><a href="${ctx}/mms/materialUse/form?id=${materialUse.id}">
					${materialUse.sequence}
				</a></td>
				<td>
					${materialUse.standardChineseName}
				</td>
				<td>
					${materialUse.minActualComponentContent}
				</td>
				<td>
					${materialUse.maxActualComponentContent}
				</td>
				<td>
					${materialUse.purposeOfUse}
				</td>
				<td>
					${materialUse.riskMaterial}
				</td>
				<td>
					<fmt:formatDate value="${materialUse.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${materialUse.remarks}
				</td>
				<shiro:hasPermission name="mms:materialUse:edit"><td>
    				<a href="${ctx}/mms/materialUse/form?id=${materialUse.id}">修改</a>
					<a href="${ctx}/mms/materialUse/delete?id=${materialUse.id}" onclick="return confirmx('确认要删除该原料使用数据库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>