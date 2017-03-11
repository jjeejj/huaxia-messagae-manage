<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>禁用语词汇管理</title>
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
		<li class="active"><a href="${ctx}/mms/forbiddenWords/">禁用语词汇列表</a></li>
		<shiro:hasPermission name="mms:forbiddenWords:edit"><li><a href="${ctx}/mms/forbiddenWords/form">禁用语词汇添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="forbiddenWords" action="${ctx}/mms/forbiddenWords/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>禁用名称：</label>
				<form:input path="forbiddenName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>禁用名称</th>
				<th>禁用说明</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:forbiddenWords:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="forbiddenWords">
			<tr>
				<td><a href="${ctx}/mms/forbiddenWords/form?id=${forbiddenWords.id}">
					${forbiddenWords.sequence}
				</a></td>
				<td>
					${forbiddenWords.forbiddenName}
				</td>
				<td>
					${forbiddenWords.forbiddenExplain}
				</td>
				<td>
					<fmt:formatDate value="${forbiddenWords.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${forbiddenWords.remarks}
				</td>
				<shiro:hasPermission name="mms:forbiddenWords:edit"><td>
    				<a href="${ctx}/mms/forbiddenWords/form?id=${forbiddenWords.id}">修改</a>
					<a href="${ctx}/mms/forbiddenWords/delete?id=${forbiddenWords.id}" onclick="return confirmx('确认要删除该禁用语词汇吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>