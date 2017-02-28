<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配方信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			function startFilter(id) {
				$.ajax({
				    url:'${ctx}/mms/formula/filter',
					type:'get',
					success:function (data) {

                    },
					error:function (err) {

                    }
				})
            }
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
		<li class="active"><a href="${ctx}/mms/formula/">配方信息列表</a></li>
		<shiro:hasPermission name="mms:formula:edit"><li><a href="${ctx}/mms/formula/form">配方信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="formula" action="${ctx}/mms/formula/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>序号：</label>
				<form:input path="sequence" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>配方名称：</label>
				<form:input path="formulaName" htmlEscape="false" maxlength="255" class="input-medium"/>
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
				<th>配方名称</th>
				<th>总原料含量（%）</th>
				<th>总实际成份含量（%）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:formula:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="formula">
			<tr>
				<td><a href="${ctx}/mms/formula/form?id=${formula.id}">
					${formula.sequence}
				</a></td>
				<td>
					${formula.formulaName}
				</td>
				<td>
					${formula.rawMaterialContentTotal}
				</td>
				<td>
					${formula.actualComponentContentTotal}
				</td>
				<td>
					<fmt:formatDate value="${formula.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${formula.remarks}
				</td>
				<shiro:hasPermission name="mms:formula:edit"><td>
    				<a href="${ctx}/mms/formula/form?id=${formula.id}">修改</a>
					<a href="${ctx}/mms/formula/delete?id=${formula.id}" onclick="return confirmx('确认要删除该配方信息吗？', this.href)">删除</a>
					<%--<a href="${ctx}/mms/formula/filter?id=${formula.id}" onclick="startFilter(${formula.id})">筛选</a>--%>
					<a href="${ctx}/mms/formula/filter?id=${formula.id}" >筛选</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>