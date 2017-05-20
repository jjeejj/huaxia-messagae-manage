<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策法规数据库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
//			$('#fileDownload').on('click',function () {
//				var href = $(this).data("href");
//				top.$.jBox.confirm("确认要下载该文件吗？","系统提示",function(v,h,f){
//					if(v=="ok"){
//                        $.ajax({
//                            url:href,
//                            type:'GET',
//                            success:function () {
//                                $.jBox.info('下载成功');
//                            },
//                            error:function (err) {
//                                console.log(err,err);
//                                $.jBox.error('下载失败');
//                            }
//                        })
//					}
//				},{buttonsFocus:1});
//				top.$('.jbox-body .jbox-icon').css('top','55px');
//            })
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
			<li><label>文件来源：</label>
				<form:select path="fileSource" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('file_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>文件分类：</label>
				<form:select path="fileType" class="input-medium">
					<form:option value="" label="请选择"/>
					<form:options items="${fns:getDictList('file_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</li>
			<li><label>文件时效性：</label>
				<form:select path="fileTimeliness" class="input-medium">
					<form:option value="" label="请选择"/>
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
				<th>文件分类</th>
				<th>文件时效性</th>
				<th>文件来源</th>
				<th>备注信息</th>
				<shiro:hasPermission name="mms:policiesRegulations:edit"><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="policiesRegulations">
			<tr>
				<td>
					<%--<a href="${ctx}/mms/policiesRegulations/form?id=${policiesRegulations.id}">--%>
					${policiesRegulations.sequence}
					<%--</a>--%>
				</td>
				<td>
					<a id="fileDownload" href="${policiesRegulations.uploadAddress}"
					   download="${policiesRegulations.fileName}.${fn:substringAfter(policiesRegulations.uploadAddress,'.')}">
						${policiesRegulations.fileName}
					</a>
					</a>
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
						${fns:getDictLabel(policiesRegulations.fileSource, 'file_source', '')}
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