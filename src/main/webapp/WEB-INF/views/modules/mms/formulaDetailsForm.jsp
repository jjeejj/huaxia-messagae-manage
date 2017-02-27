<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>配方详情信息管理</title>
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
		<li><a href="${ctx}/mms/formulaDetails/">配方详情信息列表</a></li>
		<li class="active"><a href="${ctx}/mms/formulaDetails/form?id=${formulaDetails.id}">配方详情信息<shiro:hasPermission name="mms:formulaDetails:edit">${not empty formulaDetails.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:formulaDetails:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="formulaDetails" action="${ctx}/mms/formulaDetails/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">配方id：</label>
			<div class="controls">
				<form:input path="formulaId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">序号：</label>
			<div class="controls">
				<form:input path="sequence" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准中文名称：</label>
			<div class="controls">
				<form:input path="standardChineseName" htmlEscape="false" maxlength="100" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">INCI名：</label>
			<div class="controls">
				<form:input path="inicName" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">原料含量（%）：</label>
			<div class="controls">
				<form:input path="rawMaterialContent" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">复配百分比（%）：</label>
			<div class="controls">
				<form:input path="compoundPercentage" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际成份含量（%）：</label>
			<div class="controls">
				<form:input path="actualComponentContent" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">使用目的：</label>
			<div class="controls">
				<form:input path="purposeOfUse" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险物质：</label>
			<div class="controls">
				<form:input path="riskMaterial" htmlEscape="false" maxlength="100" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">成分类型：</label>
			<div class="controls">
				<form:select path="componentType" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('component_type')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">对于限用成分的实际成份含量状态：</label>
			<div class="controls">
				<form:select path="actualComponentContentStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('actual_component_content_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">标准中文名称或INCI名的状态：</label>
			<div class="controls">
				<form:select path="nameOrInicStatus" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('name_or_inic_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">是否是植物成分：</label>
			<div class="controls">
				<form:select path="plantComponent" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('plant_component')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mms:formulaDetails:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>