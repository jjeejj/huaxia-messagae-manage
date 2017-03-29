<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策法规数据库管理</title>
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
		<li class="active"><a href="${ctx}/mms/policiesRegulations/">政策法规数据库列表</a></li>
		<shiro:hasPermission name="mms:policiesRegulations:edit"><li><a href="${ctx}/mms/policiesRegulations/form">政策法规数据库添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="policiesRegulations" action="${ctx}/mms/policiesRegulations/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>文件名称：</label>
				<form:input path="fileName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>文号：</label>
				<form:input path="documentNumber" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<li><label>文件分类（1：法规、2：标准、3：技术资料）：</label>
				<form:select path="fileType" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('file_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>文件时效性（1：现行、2：征求意见、3：历史文件）：</label>
				<form:select path="fileTimeliness" class="input-medium">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('file_timeliness')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
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
				<th>文件名称</th>
				<th>文件原始链接</th>
				<th>文号</th>
				<th>文件分类（1：法规、2：标准、3：技术资料）</th>
				<th>文件时效性（1：现行、2：征求意见、3：历史文件）</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:policiesRegulations:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="policiesRegulations">
			<tr>
				<td><a href="${ctx}/mms/policiesRegulations/form?id=${policiesRegulations.id}">
					${policiesRegulations.sequence}
				</a></td>
				<td>
					${policiesRegulations.fileName}
				</td>
				<td>
					${policiesRegulations.sourceHref}
				</td>
				<td>
					${policiesRegulations.documentNumber}
				</td>
				<td>
					${fns:getDictLabel(policiesRegulations.fileType, 'file_type', '')}
				</td>
				<td>
					${fns:getDictLabel(policiesRegulations.fileTimeliness, 'file_timeliness', '')}
				</td>
				<td>
					<fmt:formatDate value="${policiesRegulations.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${policiesRegulations.remarks}
				</td>
				<shiro:hasPermission name="mms:policiesRegulations:edit"><td>
    				<a href="${ctx}/mms/policiesRegulations/form?id=${policiesRegulations.id}">修改</a>
					<a href="${ctx}/mms/policiesRegulations/delete?id=${policiesRegulations.id}" onclick="return confirmx('确认要删除该政策法规数据库吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>