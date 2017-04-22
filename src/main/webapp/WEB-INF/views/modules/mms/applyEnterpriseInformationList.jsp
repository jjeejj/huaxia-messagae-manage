<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息管理</title>
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
		<li class="active"><a href="${ctx}/mms/enterpriseInformation/">申请企业信息列表</a></li>
		<%--<shiro:hasPermission name="mms:enterpriseInformation:edit"><li><a href="${ctx}/mms/enterpriseInformation/form">企业信息添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="enterpriseInformation" action="${ctx}/mms/enterpriseInformation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="enterpriseType" name="enterpriseType" type="hidden" value="${enterpriseType}"/>
		<ul class="ul-form">
			<li><label>企业名称：</label>
				<form:input path="enterpriseName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>企业地址：</label>
				<form:input path="enterpriseAddress" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>申请企业名称</th>
				<th>申请企业地址</th>
				<th>申请企业电话</th>
				<th>申请企业联系人</th>
				<%--<shiro:hasPermission name="mms:enterpriseInformation:edit"><th>操作</th></shiro:hasPermission>--%>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="enterpriseInformation">
			<tr>
				<td>
					${enterpriseInformation.enterpriseName}
				</td>
				<td>
					${enterpriseInformation.enterpriseAddress}
				</td>
				<td>
					${enterpriseInformation.enterprisePhone}
				</td>
				<td>
					${enterpriseInformation.enterpriseContacts}
				</td>
				<%--<shiro:hasPermission name="mms:enterpriseInformation:edit"><td>--%>
    				<%--<a href="${ctx}/mms/enterpriseInformation/form?id=${enterpriseInformation.id}">修改</a>--%>
					<%--<a href="${ctx}/mms/enterpriseInformation/delete?id=${enterpriseInformation.id}" onclick="return confirmx('确认要删除该企业信息吗？', this.href)">删除</a>--%>
				<%--</td></shiro:hasPermission>--%>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>