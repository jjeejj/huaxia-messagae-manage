<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配方详情信息管理</title>
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
		<li class="active"><a href="${ctx}/mms/formulaDetails/">配方详情信息列表</a></li>
		<shiro:hasPermission name="mms:formulaDetails:edit"><li><a href="${ctx}/mms/formulaDetails/form">配方详情信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="formulaDetails" action="${ctx}/mms/formulaDetails/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>序号：</label>
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
				<th>配方id</th>
				<th>序号</th>
				<th>标准中文名称</th>
				<th>INCI名</th>
				<th>原料含量（%）</th>
				<th>复配百分比（%）</th>
				<th>实际成份含量（%）</th>
				<th>使用目的</th>
				<th>风险物质</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:formulaDetails:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="formulaDetails">
			<tr>
				<td><a href="${ctx}/mms/formulaDetails/form?id=${formulaDetails.id}">
					${formulaDetails.formulaId}
				</a></td>
				<td>
					${formulaDetails.sequence}
				</td>
				<td>
					${formulaDetails.standardChineseName}
				</td>
				<td>
					${formulaDetails.inicName}
				</td>
				<td>
					${formulaDetails.rawMaterialContent}
				</td>
				<td>
					${formulaDetails.compoundPercentage}
				</td>
				<td>
					${formulaDetails.actualComponentContent}
				</td>
				<td>
					${formulaDetails.purposeOfUse}
				</td>
				<td>
					${formulaDetails.riskMaterial}
				</td>
				<td>
					<fmt:formatDate value="${formulaDetails.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${formulaDetails.remarks}
				</td>
				<shiro:hasPermission name="mms:formulaDetails:edit"><td>
    				<a href="${ctx}/mms/formulaDetails/form?id=${formulaDetails.id}">修改</a>
					<a href="${ctx}/mms/formulaDetails/delete?id=${formulaDetails.id}" onclick="return confirmx('确认要删除该配方详情信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>