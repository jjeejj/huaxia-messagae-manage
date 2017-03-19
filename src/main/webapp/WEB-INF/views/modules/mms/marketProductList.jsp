<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>市场产品管理</title>
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
		<li class="active"><a href="${ctx}/mms/marketProduct/">市场产品列表</a></li>
		<shiro:hasPermission name="mms:marketProduct:edit"><li><a href="${ctx}/mms/marketProduct/form">市场产品添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="marketProduct" action="${ctx}/mms/marketProduct/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品编号：</label>
				<form:input path="productNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="chineseName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>英文名称：</label>
				<form:input path="englishName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>类别</th>
				<th>工作事项</th>
				<th>产品负责人</th>
				<th>项目负责人</th>
				<th>申请企业</th>
				<th>申请企业地址</th>
				<th>申请企业电话</th>
				<th>申请企业联系人</th>
				<th>实际生产企业</th>
				<th>实际生产企业地址</th>
				<th>实际生产企业电话</th>
				<th>在华责任单位</th>
				<th>在华责任单位地址</th>
				<th>在华责任单位电话</th>
				<th>在华责任单位传真</th>
				<th>在华责任单位邮编</th>
				<th>合同编号</th>
				<th>立项时间</th>
				<th>合同签订时间</th>
				<th>来款时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:marketProduct:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="marketProduct">
			<tr>
				<td><a href="${ctx}/mms/marketProduct/form?id=${marketProduct.id}">
					${marketProduct.productNumber}
				</a></td>
				<td>
					${marketProduct.chineseName}
				</td>
				<td>
					${marketProduct.englishName}
				</td>
				<td>
				  	${marketProduct.productType}
				</td>
				<td>
						${marketProduct.workMatters}
				</td>
				<td>
					${marketProduct.productLeader}
				</td>
				<td>
					${marketProduct.projectLeader}
				</td>
				<td>
					${marketProduct.enterpriseApplication}
				</td>
				<td>
						${marketProduct.enterpriseApplicationAddress}
				</td>
				<td>
						${marketProduct.enterpriseApplicationPhone}
				</td>
				<td>
						${marketProduct.enterpriseApplicationContacts}
				</td>
				<td>
					${marketProduct.actualProductionEnterprise}
				</td>
				<td>
						${marketProduct.actualProductionEnterpriseAddress}
				</td>
				<td>
						${marketProduct.actualProductionEnterprisePhone}
				</td>
				<td>
					${marketProduct.responsibleUnitInChina}
				</td>
				<td>
						${marketProduct.responsibleUnitInChinaAddress}
				</td>
				<td>
						${marketProduct.responsibleUnitInChinaPhone}
				</td>
				<td>
						${marketProduct.responsibleUnitInChinaFax}
				</td>
				<td>
						${marketProduct.responsibleUnitInChinaZipCode}
				</td>
				<td>
						${marketProduct.contractNumber}
				</td>
				<td>
					<fmt:formatDate value="${marketProduct.projectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${marketProduct.contractSigningTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					<fmt:formatDate value="${marketProduct.arrivalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${marketProduct.remarks}
				</td>
				<shiro:hasPermission name="mms:marketProduct:edit"><td>
    				<a href="${ctx}/mms/marketProduct/form?id=${marketProduct.id}">修改</a>
					<a href="${ctx}/mms/marketProduct/delete?id=${marketProduct.id}" onclick="return confirmx('确认要删除该市场产品吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>