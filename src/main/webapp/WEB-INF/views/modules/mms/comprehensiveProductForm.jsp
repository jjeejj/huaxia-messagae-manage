<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>综合产品管理</title>
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
		<li><a href="${ctx}/mms/comprehensiveProduct/">综合产品列表</a></li>
		<li class="active"><a href="${ctx}/mms/comprehensiveProduct/form?id=${comprehensiveProduct.id}">综合产品<shiro:hasPermission name="mms:comprehensiveProduct:edit">${not empty comprehensiveProduct.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="mms:comprehensiveProduct:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="comprehensiveProduct" action="${ctx}/mms/comprehensiveProduct/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>
		<div class="control-group">
			<label class="control-label">产品中文名称：</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="64"
						class="input-xlarge "  value="${marketProduct.chineseName}" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品英文名称：</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="64"
						class="input-xlarge " readonly value="${marketProduct.englishName}" />
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">产品编号：</label>
			<div class="controls">
				<input  htmlEscape="false" maxlength="64"
						class="input-xlarge "  value="${marketProduct.productNumber}" readonly="readonly"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来款时间：</label>
			<div class="controls">
				<input name="arrivalTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comprehensiveProduct.arrivalTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">来样时间：</label>
			<div class="controls">
				<input name="sampleTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">样品数量：</label>
			<div class="controls">
				<form:input path="sampleQuantity" htmlEscape="false" maxlength="10" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验受理编号：</label>
			<div class="controls">
				<form:input path="administrativeLicenseInspectionNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">行政许可检验取报告时间：</label>
			<div class="controls">
				<input name="administrativeLicenseInspectionReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comprehensiveProduct.administrativeLicenseInspectionReportTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验受理编号：</label>
			<div class="controls">
				<form:input path="humanTestAcceptanceNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">人体检验取报告时间：</label>
			<div class="controls">
				<input name="humanTestAcceptanceReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comprehensiveProduct.humanTestAcceptanceReportTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验受理编号：</label>
			<div class="controls">
				<form:input path="riskTestAcceptanceNo" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">风险检验取报告时间：</label>
			<div class="controls">
				<input name="riskTestAcceptanceReportTime" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${comprehensiveProduct.riskTestAcceptanceReportTime}" pattern="yyyy-MM-dd"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
			</div>
		</div>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">产品处理人id：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:input path="productHandlePersonId" htmlEscape="false" maxlength="64" class="input-xlarge required"/>--%>
				<%--<span class="help-inline"><font color="red">*</font> </span>--%>
			<%--</div>--%>
		<%--</div>--%>
		<%--<div class="control-group">--%>
			<%--<label class="control-label">备注信息：</label>--%>
			<%--<div class="controls">--%>
				<%--<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="255" class="input-xxlarge "/>--%>
			<%--</div>--%>
		<%--</div>--%>
		<div class="form-actions">
			<shiro:hasPermission name="mms:comprehensiveProduct:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>