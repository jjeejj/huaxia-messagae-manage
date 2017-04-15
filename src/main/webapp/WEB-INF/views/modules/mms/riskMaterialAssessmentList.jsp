<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>风险物质评估信息管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:100px;
		}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
            $(document).ready(function() {
                $("#btnImport").click(function(){
                    $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                        bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
                });
            });
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
	<div id="importBox" class="hide">
		<form id="importForm" action="${ctx}/mms/riskMaterialAssessment/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/mms/riskMaterialAssessment/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mms/riskMaterialAssessment/">风险物质评估信息列表</a></li>
		<shiro:hasPermission name="mms:riskMaterialAssessment:edit"><li><a href="${ctx}/mms/riskMaterialAssessment/form">风险物质评估信息添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="riskMaterialAssessment" action="${ctx}/mms/riskMaterialAssessment/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>风险物质名称：</label>
				<form:input path="riskMaterialName" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li><label>主要来源：</label>
				<form:input path="source" htmlEscape="false" maxlength="255" class="input-medium"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnImport" class="btn btn-primary" type="button" value="导入"/></li>
			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th>序号</th>
				<th>风险物质名称</th>
				<th>主要来源</th>
				<th>安全阈值</th>
				<th>评估依据</th>
				<th>备注信息</th>
				<th>更新时间</th>
				<shiro:hasPermission name="mms:riskMaterialAssessment:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="riskMaterialAssessment">
			<tr>
				<td><a href="${ctx}/mms/riskMaterialAssessment/form?id=${riskMaterialAssessment.id}">
					${riskMaterialAssessment.sequence}
				</a></td>
				<td>
					${riskMaterialAssessment.riskMaterialName}
				</td>
				<td>
					${riskMaterialAssessment.source}
				</td>
				<td>
					${riskMaterialAssessment.safetyThreshold}
				</td>
				<td>
					${riskMaterialAssessment.evaluationBasis}
				</td>
				<td>
						${riskMaterialAssessment.remarks}
				</td>
				<td>
					<fmt:formatDate value="${riskMaterialAssessment.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<shiro:hasPermission name="mms:riskMaterialAssessment:edit"><td>
    				<a href="${ctx}/mms/riskMaterialAssessment/form?id=${riskMaterialAssessment.id}">修改</a>
					<a href="${ctx}/mms/riskMaterialAssessment/delete?id=${riskMaterialAssessment.id}" onclick="return confirmx('确认要删除该风险物质评估信息吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>