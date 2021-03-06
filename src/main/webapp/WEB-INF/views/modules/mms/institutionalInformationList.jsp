<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>机构信息管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:100px;
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
		<li class="active"><a href="${ctx}/mms/institutionalInformation/">机构信息列表</a></li>
		<shiro:hasPermission name="mms:institutionalInformation:edit"><li><a href="${ctx}/mms/institutionalInformation/form">机构信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="institutionalInformation" action="${ctx}/mms/institutionalInformation/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>机构名称：</label>
				<form:input path="institutionalName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>机构名称分类：</label>
				<form:select path="institutionalType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('institutional_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>机构名称</th>
				<th>机构名称分类</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:institutionalInformation:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="institutionalInformation">
			<tr>
				<td><a href="${ctx}/mms/institutionalInformation/form?id=${institutionalInformation.id}">
					${institutionalInformation.sequence}
				</a></td>
				<td>
					${institutionalInformation.institutionalName}
				</td>
				<td>
					${fns:getDictLabel(institutionalInformation.institutionalType, 'institutional_type', '')}
				</td>
				<td>
					<fmt:formatDate value="${institutionalInformation.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${institutionalInformation.remarks}
				</td>
				<shiro:hasPermission name="mms:institutionalInformation:edit"><td>
    				<a href="${ctx}/mms/institutionalInformation/form?id=${institutionalInformation.id}">修改</a>
					<a href="${ctx}/mms/institutionalInformation/delete?id=${institutionalInformation.id}" onclick="return confirmx('确认要删除该机构信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>