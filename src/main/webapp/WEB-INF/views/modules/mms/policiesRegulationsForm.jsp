<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>政策法规数据库管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
                rules: {
                    documentNumber: {remote: "${ctx}/mms/policiesRegulations/checkDocumentNumber?oldDocumentNumber=" + encodeURIComponent("${policiesRegulations.documentNumber}")}
                },
                messages: {
                    documentNumber: {remote: "该文号已存在"}
                },
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/mms/policiesRegulations/">政策法规数据库列表</a></li>
		<li class="active"><a href="${ctx}/mms/policiesRegulations/form?id=${policiesRegulations.id}">政策法规数据库<shiro:hasPermission name="mms:policiesRegulations:edit">${not empty policiesRegulations.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:policiesRegulations:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="policiesRegulations" action="${ctx}/mms/policiesRegulations/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<%--<div class="control-group">--%>
			<%--<label class="control-label">序号：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="sequence" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="control-group">
			<label class="control-label">文件名称：</label>
			<div class="controls">
				<form:input path="fileName" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上传文件：</label>
			<div class="controls">
				<form:hidden id="uploadFileAddress" path="uploadAddress" htmlEscape="false" maxlength="255" class="input-xlarge required"/>
				<sys:ckfinder input="uploadFileAddress" type="files" uploadPath="/mms"/>
				<%--<form:input path="uploadAddress" htmlEscape="false" maxlength="64" class="input-xlarge" type="file"/>--%>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件原始链接：</label>
			<div class="controls">
				<form:input path="sourceHref" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文号：</label>
			<div class="controls">
				<input id="oldDocumentNumber" name="oldDocumentNumber" type="hidden" value="${policiesRegulations.documentNumber}">
				<form:input path="documentNumber" htmlEscape="false" maxlength="32" class="input-xlarge"/>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件来源：</label>
			<div class="controls">
				<form:select path="fileSource" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('file_source')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件分类：</label>
			<div class="controls">
				<form:select path="fileType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('file_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">文件时效性：</label>
			<div class="controls">
				<form:select path="fileTimeliness" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('file_timeliness')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mms:policiesRegulations:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>