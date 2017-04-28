<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>着色剂管理</title>
	<meta name="decorator" content="default"/>
	<%--<style>--%>
		<%--.form-search .ul-form li label{--%>
			<%--width:100px;--%>
		<%--}--%>
	<%--</style>--%>
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
		<li class="active"><a href="${ctx}/mms/colorantComponent/">着色剂列表</a></li>
		<shiro:hasPermission name="mms:colorantComponent:edit"><li><a href="${ctx}/mms/colorantComponent/form">着色剂添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="colorantComponent" action="${ctx}/mms/colorantComponent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>索引号：</label>
				<form:input path="colorIndex" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>索引通用名：</label>
				<form:input path="colorGenericName" htmlEscape="false" maxlength="64" class="input-medium"/>
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
				<th>着色剂索引号</th>
				<th>着色剂索引通用名</th>
				<th>限用成分备注信息</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:colorantComponent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="colorantComponent">
			<tr>
				<td><a href="${ctx}/mms/colorantComponent/form?id=${colorantComponent.id}">
					${colorantComponent.sequence}
				</a></td>
				<td>
					${colorantComponent.colorIndex}
				</td>
				<td>
					${colorantComponent.colorGenericName}
				</td>
				<td>
					${colorantComponent.limitedRemarks}
				</td>
				<td>
					<fmt:formatDate value="${colorantComponent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${colorantComponent.remarks}
				</td>
				<shiro:hasPermission name="mms:colorantComponent:edit"><td>
    				<a href="${ctx}/mms/colorantComponent/form?id=${colorantComponent.id}">修改</a>
					<a href="${ctx}/mms/colorantComponent/delete?id=${colorantComponent.id}" onclick="return confirmx('确认要删除该着色剂吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>