<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>inci名与标准中文名相互转换管理</title>
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
		<li class="active"><a href="${ctx}/mms/incinameConvertChinesename/">inci名与标准中文名相互转换列表</a></li>
		<shiro:hasPermission name="mms:incinameConvertChinesename:edit"><li><a href="${ctx}/mms/incinameConvertChinesename/form">inci名与标准中文名相互转换添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="incinameConvertChinesename" action="${ctx}/mms/incinameConvertChinesename/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标准中文名称：</label>
				<form:input path="standardChineseName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>INCI名：</label>
				<form:input path="inciName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>标准中文名称</th>
				<th>INCI名</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:incinameConvertChinesename:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="incinameConvertChinesename">
			<tr>
				<td><a href="${ctx}/mms/incinameConvertChinesename/form?id=${incinameConvertChinesename.id}">
					${incinameConvertChinesename.standardChineseName}
				</a></td>
				<td>
					${incinameConvertChinesename.inciName}
				</td>
				<td>
					<fmt:formatDate value="${incinameConvertChinesename.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${incinameConvertChinesename.remarks}
				</td>
				<shiro:hasPermission name="mms:incinameConvertChinesename:edit"><td>
    				<a href="${ctx}/mms/incinameConvertChinesename/form?id=${incinameConvertChinesename.id}">修改</a>
					<a href="${ctx}/mms/incinameConvertChinesename/delete?id=${incinameConvertChinesename.id}" onclick="return confirmx('确认要删除该inci名与标准中文名相互转换吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>