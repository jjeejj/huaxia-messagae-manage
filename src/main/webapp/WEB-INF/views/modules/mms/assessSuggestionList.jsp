<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>审评意见管理</title>
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
		<li class="active"><a href="${ctx}/mms/assessSuggestion/">审评意见列表</a></li>
		<shiro:hasPermission name="mms:assessSuggestion:edit"><li><a href="${ctx}/mms/assessSuggestion/form">审评意见添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="assessSuggestion" action="${ctx}/mms/assessSuggestion/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>意见类别：</label>
				<form:select path="suggestionType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('suggestion_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>序号</th>
				<th>意见类别</th>
				<th>主要内容</th>
				<th>出具日期</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="mms:assessSuggestion:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="assessSuggestion">
			<tr>
				<td><a href="${ctx}/mms/assessSuggestion/form?id=${assessSuggestion.id}">
					${assessSuggestion.sequence}
				</a></td>
				<td>
					${fns:getDictLabel(assessSuggestion.suggestionType, 'suggestion_type', '')}
				</td>
				<td>
					${assessSuggestion.mainContent}
				</td>
				<td>
					<fmt:formatDate value="${assessSuggestion.issuanceDate}" pattern="yyyy-MM-dd"/>
				</td>
				<td>
						${assessSuggestion.remarks}
				</td>
				<td>
					<fmt:formatDate value="${assessSuggestion.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="mms:assessSuggestion:edit"><td>
    				<a href="${ctx}/mms/assessSuggestion/form?id=${assessSuggestion.id}">修改</a>
					<a href="${ctx}/mms/assessSuggestion/delete?id=${assessSuggestion.id}" onclick="return confirmx('确认要删除该审评意见吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>