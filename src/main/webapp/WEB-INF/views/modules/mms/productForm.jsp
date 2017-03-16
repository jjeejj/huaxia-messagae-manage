<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
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
		<li><a href="${ctx}/mms/product/">产品列表</a></li>
		<li class="active"><a href="${ctx}/mms/product/form?id=${product.id}">产品<shiro:hasPermission name="mms:product:edit">${not empty product.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:product:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="productVo" action="${ctx}/mms/product/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">产品编号：</label>
			<div class="controls">
				<form:input path="productNumber" htmlEscape="false" maxlength="64" class="input-xlarge required" disabled="true"/>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">中文名称：</label>
			<div class="controls">
				<form:input path="chineseName" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">英文名称：</label>
			<div class="controls">
				<form:input path="englishName" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">类别：</label>
			<div class="controls">
				<form:input path="productType" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
				<%--<form:textarea path="productType" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">工作事项：</label>
			<div class="controls">
				<form:input path="workMatters" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品负责人：</label>
			<div class="controls">
				<form:input path="productLeader" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">项目负责人：</label>
			<div class="controls">
				<form:input path="projectLeader" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
				<%--<form:textarea path="projectLeader" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">申请企业：</label>
			<div class="controls">
				<form:input path="enterpriseApplication" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">实际生产企业：</label>
			<div class="controls">
				<form:input path="actualProductionEnterprise" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">在华责任单位：</label>
			<div class="controls">
				<form:input path="responsibleUnitInChina" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
				<%--<form:textarea path="responsibleUnitInChina" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">立项时间:</label>
			<div class="controls">
				<input id="projectTime" name="projectTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.projectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">合同签订时间:</label>
			<div class="controls">
				<input id="contractSigningTime" name="contractSigningTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.contractSigningTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>


		<div class="control-group">
			<label class="control-label">来样时间:</label>
			<div class="controls">
				<input id="sampleTime" name="sampleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品数量：</label>
			<div class="controls">
				<form:input path="sampleQuantity" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
				<%--<form:textarea path="sampleQuantity" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送检总数：</label>
			<div class="controls">
				<form:input path="totalNumberOfSamples" htmlEscape="false" maxlength="64" class="input-xlarge " disabled="true"/>
				<%--<form:textarea path="totalNumberOfSamples" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge " disabled="true"/>--%>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送检时间:</label>
			<div class="controls">
				<input id="submissionTime" name="submissionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.submissionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">取送检报告时间:</label>
			<div class="controls">
				<input id="inspectionReportTime" name="inspectionReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.inspectionReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">送人体时间:</label>
			<div class="controls">
				<input id="sendBodyTime" name="sendBodyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取送人体报告时间:</label>
			<div class="controls">
				<input id="bodyReportTime" name="bodyReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.bodyReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上报时间:</label>
			<div class="controls">
				<input id="reportTime" name="reportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">受理时间:</label>
			<div class="controls">
				<input id="acceptanceTime" name="acceptanceTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批件时间:</label>
			<div class="controls">
				<input id="documentTime" name="documentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">下意见时间:</label>
			<div class="controls">
				<input id="nextOpinionTime" name="nextOpinionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">回复意见时间:</label>
			<div class="controls">
				<input id="replyOpinion" name="replyOpinion" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${productVo.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注:</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="3" maxlength="200" class="input-xlarge"/>
			</div>
		</div>
		<div class="form-actions">
			<%--<shiro:hasPermission name="mms:product:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>--%>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>