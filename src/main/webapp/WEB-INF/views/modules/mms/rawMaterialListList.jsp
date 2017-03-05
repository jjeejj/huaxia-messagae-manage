<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>已使用原料目录管理</title>
	<meta name="decorator" content="default"/>
	<style>
		.form-search .ul-form li label{
			width:120px;
		}
	</style>
	<script type="text/javascript">
        $(document).ready(function() {
            $("#btnImport").click(function(){
                $.jBox($("#importBox").html(), {title:"导入数据", buttons:{"关闭":true},
                    bottomText:"导入文件不能超过5M，仅允许导入“xls”或“xlsx”格式文件！"});
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
		<form id="importForm" action="${ctx}/mms/rawMaterialList/import" method="post" enctype="multipart/form-data"
			  class="form-search" style="padding-left:20px;text-align:center;" onsubmit="loading('正在导入，请稍等...');"><br/>
			<input id="uploadFile" name="file" type="file" style="width:330px"/><br/><br/>　　
			<input id="btnImportSubmit" class="btn btn-primary" type="submit" value="   导    入   "/>
			<a href="${ctx}/mms/rawMaterialList/import/template">下载模板</a>
		</form>
	</div>
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mms/rawMaterialList/">已使用原料目录列表</a></li>
		<shiro:hasPermission name="mms:rawMaterialList:edit"><li><a href="${ctx}/mms/rawMaterialList/form">已使用原料目录添加</a></li></shiro:hasPermission>
	</ul>
	<form:form id="searchForm" modelAttribute="rawMaterialList" action="${ctx}/mms/rawMaterialList/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>标准中文名称：</label>
				<form:input path="standardChineseName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>INCI名/英文名称：</label>
				<form:input path="inicName" htmlEscape="false" maxlength="100" class="input-medium"/>
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
				<th>标准中文名称</th>
				<th>INCI名/英文名称</th>
				<th>更新时间</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:rawMaterialList:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="rawMaterialList">
			<tr>
				<td><a href="${ctx}/mms/rawMaterialList/form?id=${rawMaterialList.id}">
					${rawMaterialList.sequence}
				</a></td>
				<td>
					${rawMaterialList.standardChineseName}
				</td>
				<td>
					${rawMaterialList.inicName}
				</td>
				<td>
					<fmt:formatDate value="${rawMaterialList.updateDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${rawMaterialList.remarks}
				</td>
				<shiro:hasPermission name="mms:rawMaterialList:edit"><td>
    				<a href="${ctx}/mms/rawMaterialList/form?id=${rawMaterialList.id}">修改</a>
					<a href="${ctx}/mms/rawMaterialList/delete?id=${rawMaterialList.id}" onclick="return confirmx('确认要删除该已使用原料目录吗？', this.href)">删除</a>
				</td></shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>