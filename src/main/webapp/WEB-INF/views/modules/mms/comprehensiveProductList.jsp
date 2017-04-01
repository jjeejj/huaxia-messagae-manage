<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综合产品管理</title>
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
		<li class="active"><a href="${ctx}/mms/comprehensiveProduct/">综合产品列表</a></li>
		<%--<c:if test="${fns:isAdmin()}">--%>
			<%--<shiro:hasPermission name="mms:comprehensiveProduct:edit"><li><a href="${ctx}/mms/comprehensiveProduct/form">综合产品添加</a></li></shiro:hasPermission>--%>
		<%--</c:if>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="comprehensiveProduct" action="${ctx}/mms/comprehensiveProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>行政许可检验受理编号：</label>
				<form:input path="administrativeLicenseInspectionNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>人体检验受理编号：</label>
				<form:input path="humanTestAcceptanceNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>风险检验受理编号：</label>
				<form:input path="riskTestAcceptanceNumber" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>来款时间</th>
				<th>来样时间</th>
				<th>样品数量</th>
				<th>行政许可检验取报告时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comprehensiveProduct">
			<tr>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.arrivalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comprehensiveProduct.sampleQuantity}
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.administrativeLicenseInspectionReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comprehensiveProduct.remarks}
				</td>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><td>
    				<a href="${ctx}/mms/comprehensiveProduct/form?id=${comprehensiveProduct.id}">修改</a>
					<%--<c:if test="${fns:isAdmin()}">--%>
						<%--<a href="${ctx}/mms/comprehensiveProduct/delete?id=${comprehensiveProduct.id}" onclick="return confirmx('确认要删除该综合产品吗？', this.href)">删除</a>--%>
					<%--</c:if>--%>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>