<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>产品管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
            $("#btnExport").click(function () {
                top.$.jBox.confirm("确认要导出产品数据吗？", "系统提示", function (v, h, f) {
                    if (v == "ok") {
                        $("#searchForm").attr("action", "${ctx}/mms/product/export");
                        $("#searchForm").submit();
                        //跳转链接改回查询
                        $("#searchForm").attr("action", "${ctx}/mms/product/productInformation");
                    }
                }, {buttonsFocus: 1});
                top.$('.jbox-body .jbox-icon').css('top', '55px');
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
	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/mms/product/">产品列表</a></li>
		<%--<shiro:hasPermission name="mms:product:edit"><li><a href="${ctx}/mms/product/form">产品添加</a></li></shiro:hasPermission>--%>
	</ul>
	<form:form id="searchForm" modelAttribute="product" action="${ctx}/mms/product/productInformation" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<ul class="ul-form">
			<li><label>产品编号：</label>
				<form:input path="marketProduct.productNumber" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<li><label>中文名称：</label>
				<form:input path="marketProduct.englishName" htmlEscape="false" maxlength="100" class="input-medium"/>
			</li>
			<%--<li><label>英文名称：</label>--%>
				<%--<form:input path="marketProduct.chineseName" htmlEscape="false" maxlength="100" class="input-medium"/>--%>
			<%--</li>--%>
			<%--<li><label>产品状态：</label>--%>
				<%--<form:select path="productStatus" class="input-medium">--%>
					<%--<form:option value="" label=""/>--%>
					<%--<form:options items="${fns:getDictList('product_status')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="clearfix"></li>
			<hr/>
			<b>选择要显示列项：</b>
			<form:checkbox value="1" path="isShowProductNumber" label="产品编号"/>	     <form:checkbox path="isShowEnglishName" label="英文名称" value="1"/>
			<form:checkbox path="isShowChineseName" value="1" label="中文名称"/>  <form:checkbox path="isShowCountryOfOrigin" label="原产国" value="1"/>
			<form:checkbox value="1" path="isShowProductType" label="类别"/>  <form:checkbox path="isShowWorkMatters" value="1" label="工作事项"/>
			<form:checkbox value="1" path="isShowProductLeader" label="产品负责人"/>     <form:checkbox value="1" path="isShowProjectLeader" label="项目负责人"/>
			<form:checkbox path="isShowEnterpriseApplication" label="申请企业" value="1" />  <form:checkbox path="isShowEnterpriseApplicationAddress" value="1" label="申请企业地址" />
			<form:checkbox path="isShowEnterpriseApplicationPhone" label="申请企业电话" value="1"/> <form:checkbox path="isShowEnterpriseApplicationContacts" label="申请企业联系人" value="1"/>
			<form:checkbox path="isShowActualProductionEnterprise" label="实际生产企业" value="1" /> <form:checkbox path="isShowActualProductionEnterpriseAddress" value="1" label="实际生产企业地址"/>
			<form:checkbox path="isShowResponsibleUnitInChina" label="在华责任单位"  value="1"/> <form:checkbox path="isShowResponsibleUnitInChinaAddress" label="在华责任单位地址" value="1"/>
			<form:checkbox path="isShowResponsibleUnitInChinaPhone" label="在华责任单位电话" value="1"/> <form:checkbox path="isShowResponsibleUnitInChinaFax" label="在华责任单位传真" value="1"/>
			<form:checkbox path="isShowResponsibleUnitInChinaZipCode" label="在华责任单位邮编" value="1"/> <form:checkbox path="isShowProjectTime" label="立项时间" value="1"/>
			<form:checkbox path="isShowContractNumber" label="合同编号" value="1"/> <form:checkbox path="isShowContractSigningTime" label="合同签订时间" value="1"/>
			<form:checkbox path="isShowArrivalCompany" value="1" label="来款单位" /> <form:checkbox path="isShowArrivalTime" label="来款时间" value="1"/>
			<form:checkbox path="isShowSampleTime" label="来样时间" value="1"/> <form:checkbox path="isShowSampleQuantity" label="样品数量" value="1"/>
			<form:checkbox path="isShowTotalNumberOfSamples" label="送检总数" value="1"/> <form:checkbox path="isShowColorCharacter" label="颜色性状" value="1"/>
			<form:checkbox path="isShowSampleMarking" label="样品标记（生产日期或批号)" value="1"/> <form:checkbox path="isShowDateOfExpiry" label="保质期或限期使用日期" value="1"/>
			<form:checkbox path="isShowTechnologyDateOfExpiry" label="保质期" value="1"/> <form:checkbox path="isShowSmell" label="气味" value="1"/>
			<form:checkbox path="isShowSpecifications" label="规格" value="1"/> <form:checkbox path="isShowAdministrativeLicenseInspectionTime" label="行政许可送检时间" value="1" />
			<form:checkbox path="isShowAdministrativeLicenseInspectionOrganization" label="行政许可检验机构" value="1"/> <form:checkbox path="isShowAdministrativeLicenseInspectionProject" label="行政许可检验项目" value="1"/>
			<form:checkbox path="isShowAdministrativeLicenseInspectionNumber" label="行政许可送检数量" value="1"/> <form:checkbox path="isShowAdministrativeLicenseInspectionNo" label="行政许可检验受理编号" value="1"/>
			<form:checkbox path="isShowAdministrativeLicenseInspectionReportTime" label="行政许可检验取报告时间" value="1"/> <form:checkbox path="isShowSendBodyTime" label="人体检验送检时间" value="1"/>
			<form:checkbox path="isShowSendBodyOrganization" label="人体检验送检机构" value="1"/> <form:checkbox path="isShowSendBodyProject" label="人体检验项目" value="1"/>
			<form:checkbox path="isShowSendBodyNumber" label="人体检验数量" value="1"/>
			<form:checkbox path="isShowHumanTestAcceptanceNo" label="人体检验受理编号" value="1"/>
			<form:checkbox path="isShowHumanTestAcceptanceReportTime" label="人体检验取报告时间" value="1"/>
			<form:checkbox path="isShowSendRiskTestTime" label="风险检验时间" value="1"/>
			<form:checkbox path="isShowSendRiskTestOrganization" label="风险检验机构" value="1"/>
			<form:checkbox path="isShowSendRiskTestProjectr" label="风险检验项目" value="1"/>
			<form:checkbox path="isShowSendRiskTestNumber" label="风险检验数量" value="1"/>
			<form:checkbox path="isShowRiskTestAcceptanceNo" label="风险检验受理编号" value="1"/>
			<form:checkbox path="isShowRiskTestAcceptanceReportTime" label="风险检验取报告时间" value="1"/>
			<form:checkbox path="isShowReportTime" label="上报时间" value="1"/>
			<form:checkbox path="isShowAcceptanceTime" label="受理时间" value="1"/>
			<form:checkbox path="isShowAcceptanceNumber" label="受理编号" value="1"/>
			<form:checkbox path="isShowDocumentTime" label="取得批件时间" value="1"/>
			<form:checkbox path="isShowDocumentNumber" label="批件编号" value="1"/>
			<form:checkbox path="isShowProductStatusRemark" label="产品状态备注" value="1"/>
			<form:checkbox path="isShowNextOpinionTime" label="下意见时间" value="1"/>
			<form:checkbox path="isShowOpinionContent" label="意见内容" value="1"/>
			<form:checkbox path="isShowReplyOpinion" label="回复意见时间" value="1"/>
			<form:checkbox path="isShowOtherDescription" label="其他说明" value="1"/>


		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<c:if test="${product.isShowProductNumber eq '1'}">
					<th>产品编号</th>
				</c:if>
				<c:if test="${product.isShowChineseName eq '1'}">
					<th>中文名称</th>
				</c:if>
				<c:if test="${product.isShowEnglishName eq '1'}">
					<th>英文名称</th>
				</c:if>
				<c:if test="${product.isShowCountryOfOrigin eq '1'}">
					<th>原产国</th>
				</c:if>
				<c:if test="${product.isShowProductType eq '1'}">
					<th>类别</th>
				</c:if>
				<c:if test="${product.isShowWorkMatters eq '1'}">
					<th>工作事项</th>
				</c:if>
				<c:if test="${product.isShowProductLeader eq '1'}">
					<th>产品负责人</th>
				</c:if>
				<c:if test="${product.isShowProjectLeader eq '1'}">
					<th>项目负责人</th>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplication eq '1'}">
					<th>申请企业</th>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationAddress eq '1'}">
					<th>申请企业地址</th>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationPhone eq '1'}">
					<th>申请企业电话</th>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationContacts eq '1'}">
					<th>申请企业联系人</th>
				</c:if>
				<c:if test="${product.isShowActualProductionEnterprise eq '1'}">
					<th>实际生产企业</th>
				</c:if>
				<c:if test="${product.isShowActualProductionEnterpriseAddress eq '1'}">
					<th>实际生产企业地址</th>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChina eq '1'}">
					<th>在华责任单位</th>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaAddress eq '1'}">
					<th>在华责任单位地址</th>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaPhone eq '1'}">
					<th>在华责任单位电话</th>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaFax eq '1'}">
					<th>在华责任单位传真</th>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaZipCode eq '1'}">
					<th>在华责任单位邮编</th>
				</c:if>
				<c:if test="${product.isShowProjectTime eq '1'}">
					<th>立项时间</th>
				</c:if>
				<c:if test="${product.isShowContractNumber eq '1'}">
					<th>合同编号</th>
				</c:if>
				<c:if test="${product.isShowContractSigningTime eq '1'}">
					<th>合同签订时间</th>
				</c:if>
				<c:if test="${product.isShowArrivalCompany eq '1'}">
					<th>来款单位</th>
				</c:if>
				<c:if test="${product.isShowArrivalTime eq '1'}">
					<th>来款时间</th>
				</c:if>
				<c:if test="${product.isShowSampleTime eq '1'}">
					<th>来样时间</th>
				</c:if>
				<c:if test="${product.isShowSampleQuantity eq '1'}">
					<th>样品数量</th>
				</c:if>
				<c:if test="${product.isShowTotalNumberOfSamples eq '1'}">
					<th>送检总数</th>
				</c:if>
				<c:if test="${product.isShowColorCharacter eq '1'}">
					<th>颜色性状</th>
				</c:if>
				<c:if test="${product.isShowSampleMarking eq '1'}">
					<th>样品标记</th>
				</c:if>
				<c:if test="${product.isShowDateOfExpiry eq '1'}">
					<th>保质期或限期使用日期</th>
				</c:if>
				<c:if test="${product.isShowTechnologyDateOfExpiry eq '1'}">
					<th>保质期</th>
				</c:if>
				<c:if test="${product.isShowSmell eq '1'}">
					<th>气味</th>
				</c:if>
				<c:if test="${product.isShowSpecifications eq '1'}">
					<th>规格</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionTime eq '1'}">
					<th>行政许可送检时间</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionOrganization eq '1'}">
					<th>行政许可检验机构</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionProject eq '1'}">
					<th>行政许可检验项目</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionNumber eq '1'}">
					<th>行政许可送检数量</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionNo eq '1'}">
					<th>行政许可检验受理编号</th>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionReportTime eq '1'}">
					<th>行政许可检验取报告时间</th>
				</c:if>
				<c:if test="${product.isShowSendBodyTime eq '1'}">
					<th>人体检验送检时间</th>
				</c:if>
				<c:if test="${product.isShowSendBodyOrganization eq '1'}">
					<th>人体检验送检机构</th>
				</c:if>
				<c:if test="${product.isShowSendBodyProject eq '1'}">
					<th>人体检验项目</th>
				</c:if>
				<c:if test="${product.isShowSendBodyNumber eq '1'}">
					<th>人体检验数量</th>
				</c:if>
				<c:if test="${product.isShowHumanTestAcceptanceNo eq '1'}">
					<th>人体检验受理编号</th>
				</c:if>
				<c:if test="${product.isShowHumanTestAcceptanceReportTime eq '1'}">
					<th>人体检验取报告时间</th>
				</c:if>
				<c:if test="${product.isShowSendRiskTestTime eq '1'}">
					<th>风险检验时间</th>
				</c:if>
				<c:if test="${product.isShowSendRiskTestOrganization eq '1'}">
					<th>风险检验机构</th>
				</c:if>
				<c:if test="${product.isShowSendRiskTestProjectr eq '1'}">
					<th>风险检验项目</th>
				</c:if>
				<c:if test="${product.isShowSendRiskTestNumber eq '1'}">
					<th>风险检验数量</th>
				</c:if>
				<c:if test="${product.isShowRiskTestAcceptanceNo eq '1'}">
					<th>风险检验受理编号</th>
				</c:if>
				<c:if test="${product.isShowRiskTestAcceptanceReportTime eq '1'}">
					<th>风险检验取报告时间</th>
				</c:if>
				<c:if test="${product.isShowReportTime eq '1'}">
					<th>上报时间</th>
				</c:if>
				<c:if test="${product.isShowAcceptanceTime eq '1'}">
					<th>受理时间</th>
				</c:if>
				<c:if test="${product.isShowAcceptanceNumber eq '1'}">
					<th>受理编号</th>
				</c:if>
				<c:if test="${product.isShowDocumentTime eq '1'}">
					<th>取得批件时间</th>
				</c:if>
				<c:if test="${product.isShowDocumentNumber eq '1'}">
					<th>批件编号</th>
				</c:if>
				<c:if test="${product.isShowProductStatusRemark eq '1'}">
					<th>产品状态备注</th>
				</c:if>
				<c:if test="${product.isShowNextOpinionTime eq '1'}">
					<th>下意见时间</th>
				</c:if>
				<c:if test="${product.isShowOpinionContent eq '1'}">
					<th>意见内容</th>
				</c:if>
				<c:if test="${product.isShowReplyOpinion eq '1'}">
					<th>回复意见时间</th>
				</c:if>
				<c:if test="${product.isShowOtherDescription eq '1'}">
				<th>其他说明</th>
				</c:if>
				<th>操作</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="productData">
			<tr>
				<c:if test="${product.isShowProductNumber eq '1'}">
					<td>
						${productData.marketProduct.productNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowChineseName eq '1'}">
					<td>
							${productData.marketProduct.chineseName}
					</td>
				</c:if>
				<c:if test="${product.isShowEnglishName eq '1'}">
					<td>
							${productData.marketProduct.englishName}
					</td>
				</c:if>
				<c:if test="${product.isShowCountryOfOrigin eq '1'}">
					<td>
							${productData.marketProduct.countryOfOrigin}
					</td>
				</c:if>
				<c:if test="${product.isShowProductType eq '1'}">
					<td>
							${productData.marketProduct.productType}
					</td>
				</c:if>
				<c:if test="${product.isShowWorkMatters eq '1'}">
					<td>
							${productData.marketProduct.workMatters}
					</td>
				</c:if>
				<c:if test="${product.isShowProductLeader eq '1'}">
					<td>
							${productData.marketProduct.productLeader}
					</td>
				</c:if>
				<c:if test="${product.isShowProjectLeader eq '1'}">
					<td>
							${productData.marketProduct.projectLeader}
					</td>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplication eq '1'}">
					<td>
							${productData.marketProduct.enterpriseApplication}
					</td>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationAddress eq '1'}">
					<td>
							${productData.marketProduct.enterpriseApplicationAddress}
					</td>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationPhone eq '1'}">
					<td>
							${productData.marketProduct.enterpriseApplicationPhone}
					</td>
				</c:if>
				<c:if test="${product.isShowEnterpriseApplicationContacts eq '1'}">
					<td>
							${productData.marketProduct.enterpriseApplicationContacts}
					</td>
				</c:if>
				<c:if test="${product.isShowActualProductionEnterprise eq '1'}">
					<td>
							${productData.marketProduct.actualProductionEnterprise}
					</td>
				</c:if>
				<c:if test="${product.isShowActualProductionEnterpriseAddress eq '1'}">
					<td>
							${productData.marketProduct.actualProductionEnterpriseAddress}
					</td>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChina eq '1'}">
					<td>
						${productData.marketProduct.responsibleUnitInChina}
					</td>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaAddress eq '1'}">
					<td>
						${productData.marketProduct.responsibleUnitInChinaAddress}
					</td>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaPhone eq '1'}">
					<td>
							${productData.marketProduct.responsibleUnitInChinaPhone}
					</td>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaFax eq '1'}">
					<td>
							${productData.marketProduct.responsibleUnitInChinaFax}
					</td>
				</c:if>
				<c:if test="${product.isShowResponsibleUnitInChinaZipCode eq '1'}">
					<td>
							${productData.marketProduct.responsibleUnitInChinaZipCode}
					</td>
				</c:if>
				<c:if test="${product.isShowProjectTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.marketProduct.projectTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowContractNumber eq '1'}">
					<td>
							${productData.marketProduct.contractNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowContractSigningTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.marketProduct.contractSigningTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowArrivalCompany eq '1'}">
					<td>
							${productData.marketProduct.arrivalCompany}
					</td>
				</c:if>
				<c:if test="${product.isShowArrivalTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.comprehensiveProduct.arrivalTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSampleTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.comprehensiveProduct.sampleTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSampleQuantity eq '1'}">
					<td>
							${productData.comprehensiveProduct.sampleQuantity}
					</td>
				</c:if>
				<c:if test="${product.isShowTotalNumberOfSamples eq '1'}">
					<td>
							${productData.declareProduct.totalNumberOfSamples}
					</td>
				</c:if>
				<c:if test="${product.isShowColorCharacter eq '1'}">
					<td>
						${productData.declareProduct.colorCharacter}
					</td>
				</c:if>
				<c:if test="${product.isShowSampleMarking eq '1'}">
					<td>
							${productData.declareProduct.sampleMarking}
					</td>
				</c:if>
				<c:if test="${product.isShowDateOfExpiry eq '1'}">
					<td>
							${productData.declareProduct.dateOfExpiry}
					</td>
				</c:if>
				<c:if test="${product.isShowTechnologyDateOfExpiry eq '1'}">
					<td>
							${productData.declareProduct.technologyDateOfExpiry}
					</td>
				</c:if>
				<c:if test="${product.isShowSmell eq '1'}">
					<td>
							${productData.declareProduct.smell}
					</td>
				</c:if>
				<c:if test="${product.isShowSpecifications eq '1'}">
					<td>
							${productData.declareProduct.specifications}
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.administrativeLicenseInspectionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionOrganization eq '1'}">
					<td>
							${productData.declareProduct.administrativeLicenseInspectionOrganization}
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionProject eq '1'}">
					<td>
							${productData.declareProduct.administrativeLicenseInspectionProject}
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionNumber eq '1'}">
					<td>
							${productData.declareProduct.administrativeLicenseInspectionNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionNo eq '1'}">
					<td>
							${productData.comprehensiveProduct.administrativeLicenseInspectionNo}
					</td>
				</c:if>
				<c:if test="${product.isShowAdministrativeLicenseInspectionReportTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.comprehensiveProduct.administrativeLicenseInspectionReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSendBodyTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.sendBodyTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSendBodyOrganization eq '1'}">
					<td>
							${productData.declareProduct.sendBodyOrganization}
					</td>
				</c:if>
				<c:if test="${product.isShowSendBodyProject eq '1'}">
					<td>
							${productData.declareProduct.sendBodyProject}
					</td>
				</c:if>
				<c:if test="${product.isShowSendBodyNumber eq '1'}">
					<td>
							${productData.declareProduct.sendBodyNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowHumanTestAcceptanceNo eq '1'}">
					<td>
							${productData.comprehensiveProduct.humanTestAcceptanceNo}
					</td>
				</c:if>
				<c:if test="${product.isShowHumanTestAcceptanceReportTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.comprehensiveProduct.humanTestAcceptanceReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSendRiskTestTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.sendRiskTestTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowSendRiskTestOrganization eq '1'}">
					<td>
							${productData.declareProduct.sendRiskTestOrganization}
					</td>
				</c:if>
				<c:if test="${product.isShowSendRiskTestProjectr eq '1'}">
					<td>
							${productData.declareProduct.sendRiskTestProject}
					</td>
				</c:if>
				<c:if test="${product.isShowSendRiskTestNumber eq '1'}">
					<td>
							${productData.declareProduct.sendRiskTestNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowRiskTestAcceptanceNo eq '1'}">
					<td>
							${productData.comprehensiveProduct.riskTestAcceptanceNo}
					</td>
				</c:if>
				<c:if test="${product.isShowRiskTestAcceptanceReportTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.comprehensiveProduct.riskTestAcceptanceReportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowReportTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.reportTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowAcceptanceTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.acceptanceTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowAcceptanceNumber eq '1'}">
					<td>
							${productData.declareProduct.acceptanceNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowDocumentTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.documentTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowDocumentNumber eq '1'}">
					<td>
							${productData.declareProduct.documentNumber}
					</td>
				</c:if>
				<c:if test="${product.isShowProductStatusRemark eq '1'}">
					<td>
							${fns:getDictLabel(productData.declareProduct.productStatusRemark, 'product_status_remark', '')}
					</td>
				</c:if>
				<c:if test="${product.isShowNextOpinionTime eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.nextOpinionTime}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowOpinionContent eq '1'}">
					<td>
							${productData.declareProduct.opinionContent}
					</td>
				</c:if>
				<c:if test="${product.isShowReplyOpinion eq '1'}">
					<td>
						<fmt:formatDate value="${productData.declareProduct.replyOpinion}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
				</c:if>
				<c:if test="${product.isShowOtherDescription eq '1'}">
					<td>
							${productData.declareProduct.otherDescription}
					</td>
				</c:if>
				<td>
					<a href="${ctx}/mms/formula/formulaDetailById/${productData.marketProduct.productNumber}">
							查看配方
					</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination">${page}</div>
</body>
</html>