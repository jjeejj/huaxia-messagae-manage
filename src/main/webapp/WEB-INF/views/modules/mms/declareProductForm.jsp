<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>申报产品管理</title>
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
		<li><a href="${ctx}/mms/declareProduct/">申报产品列表</a></li>
		<li class="active"><a href="${ctx}/mms/declareProduct/form?id=${declareProduct.id}">申报产品<shiro:hasPermission name="mms:declareProduct:edit">${not empty declareProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:declareProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="declareProduct" action="${ctx}/mms/declareProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">行政许可检送检时间：</label>
			<div class="controls">
				<input name="administrativeLicenseInspectionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验机构：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionOrganization" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验项目：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionProject" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可送检数量：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionNumber" htmlEscape="false" maxlength="10" class="input-xlarge" type="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送人体检验时间：</label>
			<div class="controls">
				<input name="sendBodyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${declareProduct.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送人体检验机构：</label>
			<div class="controls">
				<form:input path="sendBodyOrganization" htmlEscape="false" maxlength="10" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送人体检验项目：</label>
			<div class="controls">
				<form:input path="sendBodyProject" htmlEscape="false" maxlength="10" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送人体报告数量：</label>
			<div class="controls">
				<form:input path="sendBodyNumber" htmlEscape="false" maxlength="10" class="input-xlarge" type="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送风险检验时间：</label>
			<div class="controls">
				<input name="sendRistTestTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${declareProduct.sendRistTestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送风险检验机构：</label>
			<div class="controls">
				<form:input path="sendRistTestOrganization" htmlEscape="false" maxlength="10" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送风险检验项目：</label>
			<div class="controls">
				<form:input path="sendRistTestProject" htmlEscape="false" maxlength="10" class="input-xlarge"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送风险报告数量：</label>
			<div class="controls">
				<form:input path="sendRistTestNumber" htmlEscape="false" maxlength="10" class="input-xlarge" type="number"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上报时间：</label>
			<div class="controls">
				<input name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">受理时间：</label>
			<div class="controls">
				<input name="acceptanceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">受理编号：</label>
			<div class="controls">
				<form:input path="acceptanceNumber" htmlEscape="false" maxlength="10" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批件时间：</label>
			<div class="controls">
				<input name="documentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批件编号：</label>
			<div class="controls">
				<form:input path="documentNumber" htmlEscape="false" maxlength="10" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下意见时间：</label>
			<div class="controls">
				<input name="nextOpinionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下意见内容：</label>
			<div class="controls">
				<form:textarea path="opinionContent" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回复意见时间：</label>
			<div class="controls">
				<input name="replyOpinion" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色性状：</label>
			<div class="controls">
				<form:input path="colorCharacter" htmlEscape="false" maxlength="10" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品标记（生产日期或批号）：</label>
			<div class="controls">
				<form:input path="sampleMarking" htmlEscape="false" maxlength="10" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期或限期使用日期：</label>
			<div class="controls">
				<input name="dateOfExpiry" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					   value="<fmt:formatDate value="${declareProduct.dateOfExpiry}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:input path="specifications" htmlEscape="false" maxlength="10" class="input-xlarge" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mms:declareProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>