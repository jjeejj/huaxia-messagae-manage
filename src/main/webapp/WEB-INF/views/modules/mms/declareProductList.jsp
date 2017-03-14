<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申报产品管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:120px;
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
		<li class="active"><a href="${ctx}/mms/declareProduct/">申报产品列表</a></li>
		<%--<shiro:hasPermission name="mms:declareProduct:edit"><li><a href="${ctx}/mms/declareProduct/form">申报产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="declareProduct" action="${ctx}/mms/declareProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>取送检报告时间：</label>
				<input name="inspectionReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${declareProduct.inspectionReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li><label>送人体时间：</label>
				<form:input path="sendBodyTime" htmlEscape="false" maxlength="10" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>产品编号</th>
				<th>中文名称</th>
				<th>英文名称</th>
				<th>取送检报告时间</th>
				<th>送人体时间</th>
				<th>取送人体报告时间</th>
				<th>上报时间</th>
				<th>受理时间</th>
				<th>批件时间</th>
				<th>下意见时间</th>
				<th>回复意见</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:declareProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="declareProduct">
			<tr>
				<td><a  href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">
						${declareProduct.marketProduct.productNumber}
				</a></td>
				<td>
						${declareProduct.marketProduct.chineseName}
				</td>
				<td>
						${declareProduct.marketProduct.englishName}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.inspectionReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${declareProduct.sendBodyTime}
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.bodyReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${declareProduct.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${declareProduct.remarks}
				</td>
				<shiro:hasPermission name="mms:declareProduct:edit"><td>
    				<a href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">修改</a>
					<a href="${ctx}/mms/declareProduct/delete?id=${declareProduct.id}" onclick="return confirmx('确认要删除该申报产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>