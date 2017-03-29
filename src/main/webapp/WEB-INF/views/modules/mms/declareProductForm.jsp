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
			<label class="control-label">送检总数：</label>
			<div class="controls">
				<form:input path="totalSubmission" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可送检时间：</label>
			<div class="controls">
				<input name="administrativeLicenseInspectionTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验机构：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionOrganization" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验项目：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionProject" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可送检数量：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验送检时间：</label>
			<div class="controls">
				<input name="sendBodyTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验送检机构：</label>
			<div class="controls">
				<form:input path="sendBodyOrganization" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验项目：</label>
			<div class="controls">
				<form:input path="sendBodyProject" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验数量：</label>
			<div class="controls">
				<form:input path="sendBodyNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验时间：</label>
			<div class="controls">
				<input name="sendRiskTestTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.sendRiskTestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验机构：</label>
			<div class="controls">
				<form:input path="sendRiskTestOrganization" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验项目：</label>
			<div class="controls">
				<form:input path="sendRiskTestProject" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验数量：</label>
			<div class="controls">
				<form:input path="sendRiskTestNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">上报时间／申报时间：</label>
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
				<form:input path="acceptanceNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">取得批件时间：</label>
			<div class="controls">
				<input name="documentTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">批件编号：</label>
			<div class="controls">
				<form:input path="documentNumber" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品状态备注（1：通过审批，2：不予批准3：终止申报&rdquo;）：</label>
			<div class="controls">
				<form:select path="productStatusRemark" class="input-xlarge ">
					<form:option value="" label=""/>
					<form:options items="${fns:getDictList('product_status_remark')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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
			<label class="control-label">意见内容：</label>
			<div class="controls">
				<form:input path="opinionContent" htmlEscape="false" maxlength="255" class="input-xlarge "/>
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
			<label class="control-label">其他说明：</label>
			<div class="controls">
				<form:input path="otherDescription" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">颜色性状：</label>
			<div class="controls">
				<form:input path="colorCharacter" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品标记（生产日期或批号）：</label>
			<div class="controls">
				<form:input path="sampleMarking" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期或限期使用日期（包装标注的保质期或限期使用日期）：</label>
			<div class="controls">
				<form:input path="dateOfExpiry" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">保质期（技术要求中显示的保质期）：</label>
			<div class="controls">
				<form:input path="technologyDateOfExpiry" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">气味：</label>
			<div class="controls">
				<form:input path="smell" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">规格：</label>
			<div class="controls">
				<form:input path="specifications" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品处理人id：</label>
			<div class="controls">
				<form:input path="productHandlePersonId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>
				<span class="help-inline"><font color="red">*</font> </span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">状态备注：</label>
			<div class="controls">
				<form:input path="statusRemarks" htmlEscape="false" maxlength="255" class="input-xlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="mms:declareProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>