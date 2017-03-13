<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综合产品管理</title>
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
		<li class="active"><a href="${ctx}/mms/comprehensiveProduct/">综合产品列表</a></li>
		<%--<shiro:hasPermission name="mms:comprehensiveProduct:edit"><li><a href="${ctx}/mms/comprehensiveProduct/form">综合产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="comprehensiveProduct" action="${ctx}/mms/comprehensiveProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>来样时间：</label>
				<input name="sampleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					value="<fmt:formatDate value="${comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
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
				<th>来样时间</th>
				<th>样品数量</th>
				<th>送检总数</th>
				<th>送检时间</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="comprehensiveProduct">
			<tr>
				<td><a href="${ctx}/mms/comprehensiveProduct/form?id=${comprehensiveProduct.id}">
						${comprehensiveProduct.marketProduct.productNumber}
				</a></td>
				<td>
						${comprehensiveProduct.marketProduct.chineseName}
				</td>
				<td>
						${comprehensiveProduct.marketProduct.englishName}
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comprehensiveProduct.sampleQuantity}
				</td>
				<td>
					${comprehensiveProduct.totalNumberOfSamples}
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.submissionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${comprehensiveProduct.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${comprehensiveProduct.remarks}
				</td>
				<shiro:hasPermission name="mms:comprehensiveProduct:edit"><td>
    				<a href="${ctx}/mms/comprehensiveProduct/form?id=${comprehensiveProduct.id}">修改</a>
					<a href="${ctx}/mms/comprehensiveProduct/delete?id=${comprehensiveProduct.id}" onclick="return confirmx('确认要删除该综合产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>