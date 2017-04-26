<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>企业信息管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
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
		<li><a href="${ctx}/mms/enterpriseInformation/?enterpriseType=${enterpriseType}">
			<c:if test="${enterpriseType eq '1'}">
				申请企业信息列表
			</c:if>
			<c:if test="${enterpriseType eq '2'}">
				实际生产企业列表
			</c:if>
			<c:if test="${enterpriseType eq '3'}">
				在华责任单位列表
			</c:if>
		</a></li>
		<li class="active"><a href="${ctx}/mms/enterpriseInformation/form?id=${enterpriseInformation.id}">企业信息
			${not empty enterpriseInformation.id?'修改':'添加'}</a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="enterpriseInformation" action="${ctx}/mms/enterpriseInformation/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">企业名称：</label>
			<div class="controls">
				<form:input path="enterpriseName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业地址：</label>
			<div class="controls">
				<form:input path="enterpriseAddress" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业类型：</label>
			<div class="controls">
				<form:select path="enterpriseType" class="input-xlarge required">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('enterprise_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业电话：</label>
			<div class="controls">
				<form:input path="enterprisePhone" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业联系人：</label>
			<div class="controls">
				<form:input path="enterpriseContacts" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业传真：</label>
			<div class="controls">
				<form:input path="nterpriseFax" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">企业邮编：</label>
			<div class="controls">
				<form:input path="rnterpriseZipCode" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>