<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>化妆品安全技术规范的限用成分管理</title>
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
		<li class="active"><a href="${ctx}/mms/limitedComponent/">化妆品安全技术规范的限用成分列表</a></li>
		<shiro:hasPermission name="mms:limitedComponent:edit"><li><a href="${ctx}/mms/limitedComponent/form">化妆品安全技术规范的限用成分添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="limitedComponent" action="${ctx}/mms/limitedComponent/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>序号：</label>
				<form:input path="sequence" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>标准中文名称：</label>
				<form:input path="standardChineseName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>INIC名：</label>
				<form:input path="inicName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>标准中文名称</th>
				<th>INIC名</th>
				<th>英文名称</th>
				<th>适用及(或)使用范围</th>
				<th>最大允许浓度</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:limitedComponent:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="limitedComponent">
			<tr>
				<td><a href="${ctx}/mms/limitedComponent/form?id=${limitedComponent.id}">
					${limitedComponent.sequence}
				</a></td>
				<td>
					${limitedComponent.standardChineseName}
				</td>
				<td>
					${limitedComponent.inicName}
				</td>
				<td>
					${limitedComponent.englishName}
				</td>
				<td>
					${limitedComponent.useRange}
				</td>
				<td>
					${limitedComponent.maxAllowConcentretion}
				</td>
				<td>
					<fmt:formatDate value="${limitedComponent.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${limitedComponent.remarks}
				</td>
				<shiro:hasPermission name="mms:limitedComponent:edit"><td>
    				<a href="${ctx}/mms/limitedComponent/form?id=${limitedComponent.id}">修改</a>
					<a href="${ctx}/mms/limitedComponent/delete?id=${limitedComponent.id}" onclick="return confirmx('确认要删除该化妆品安全技术规范的限用成分吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>