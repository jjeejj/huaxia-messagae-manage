/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.mms.entity;

import org.hibernate.validator.constraints.Length;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 产品Entity
 * @author jiang
 * @version 2017-03-12
 */
public class Product extends DataEntity<Product> {
	
	private static final long serialVersionUID = 1L;
	private String marketProductId;		// 市场产品id
	private String comprehensiveProductId;		// 综合产品id
	private String declareProductId;		// 申报产品id
	private String productStatus;		// 产品状态
	private String productProcess;		// 产品进度
	private String productLeader;		// 产品负责人

	private MarketProduct marketProduct; //市场产品
	private ComprehensiveProduct comprehensiveProduct; //综合产品
	private DeclareProduct declareProduct; //申报产品


	private String startDate;//开始日期
	private String endDate; //结束日期

	private String dateLimitContent;//办理时限说明

	//受否首次进入该页面
	private boolean isFirst = true;

//	产品界面是否显示该字段 1:显示，0：为不显示
	private String isShowProductNumber;
	private String isShowChineseName;
	private String isShowEnglishName;
	private String isShowCountryOfOrigin;
	private String isShowProductType;
	private String isShowWorkMatters;
	private String isShowProductLeader;
	private String isShowProjectLeader;
	private String isShowEnterpriseApplication;
	private String isShowEnterpriseApplicationAddress;
	private String isShowEnterpriseApplicationPhone;
	private String isShowEnterpriseApplicationContacts;
	private String isShowActualProductionEnterprise;
	private String isShowActualProductionEnterpriseAddress;
	private String isShowResponsibleUnitInChina;
	private String isShowResponsibleUnitInChinaAddress;
	private String isShowResponsibleUnitInChinaPhone;
	private String isShowResponsibleUnitInChinaFax;
	private String isShowResponsibleUnitInChinaZipCode;
	private String isShowProjectTime;
	private String isShowContractNumber;
	private String isShowContractSigningTime;
	private String isShowArrivalCompany;
	private String isShowArrivalTime;
	private String isShowSampleTime;
	private String isShowSampleQuantity;
	private String isShowTotalNumberOfSamples; //送检总数
	private String isShowColorCharacter;
	private String isShowSampleMarking;
	private String isShowDateOfExpiry;
	private String isShowTechnologyDateOfExpiry;
	private String isShowSmell;
	private String isShowSpecifications;
	private String isShowAdministrativeLicenseInspectionTime;
	private String isShowAdministrativeLicenseInspectionOrganization;
	private String isShowAdministrativeLicenseInspectionProject;
	private String isShowAdministrativeLicenseInspectionNumber;
	private String isShowAdministrativeLicenseInspectionNo;
	private String isShowAdministrativeLicenseInspectionReportTime;
	private String isShowSendBodyTime;
	private String isShowSendBodyOrganization;
	private String isShowSendBodyProject;
	private String isShowSendBodyNumber;
	private String isShowHumanTestAcceptanceNo;
	private String isShowHumanTestAcceptanceReportTime;
	private String isShowSendRiskTestTime;
	private String isShowSendRiskTestOrganization;
	private String isShowSendRiskTestProjectr;
	private String isShowSendRiskTestNumber;
	private String isShowRiskTestAcceptanceNo;
	private String isShowRiskTestAcceptanceReportTime;
	private String isShowReportTime;
	private String isShowAcceptanceTime;
	private String isShowAcceptanceNumber;
	private String isShowDocumentTime;
	private String isShowDocumentNumber;
	private String isShowProductStatusRemark;
	private String isShowNextOpinionTime;
	private String isShowOpinionContent;
	private String isShowReplyOpinion;
	private String isShowOtherDescription;


	public Product() {
		super();
	}

	public Product(String id){
		super(id);
	}

	@Length(min=1, max=64, message="市场产品id长度必须介于 1 和 64 之间")
	public String getMarketProductId() {
		return marketProductId;
	}

	public void setMarketProductId(String marketProductId) {
		this.marketProductId = marketProductId;
	}
	
	@Length(min=0, max=64, message="综合产品id长度必须介于 0 和 64 之间")
	public String getComprehensiveProductId() {
		return comprehensiveProductId;
	}

	public void setComprehensiveProductId(String comprehensiveProductId) {
		this.comprehensiveProductId = comprehensiveProductId;
	}
	
	@Length(min=0, max=64, message="申报产品id长度必须介于 0 和 64 之间")
	public String getDeclareProductId() {
		return declareProductId;
	}

	public void setDeclareProductId(String declareProductId) {
		this.declareProductId = declareProductId;
	}

	public String getProductStatus() {
		return productStatus;
	}

	public void setProductStatus(String productStatus) {
		this.productStatus = productStatus;
	}

	public MarketProduct getMarketProduct() {
		return marketProduct;
	}

	public void setMarketProduct(MarketProduct marketProduct) {
		this.marketProduct = marketProduct;
	}

	public ComprehensiveProduct getComprehensiveProduct() {
		return comprehensiveProduct;
	}

	public void setComprehensiveProduct(ComprehensiveProduct comprehensiveProduct) {
		this.comprehensiveProduct = comprehensiveProduct;
	}

	public DeclareProduct getDeclareProduct() {
		return declareProduct;
	}

	public void setDeclareProduct(DeclareProduct declareProduct) {
		this.declareProduct = declareProduct;
	}

	public String getProductProcess() {
		return productProcess;
	}

	public void setProductProcess(String productProcess) {
		this.productProcess = productProcess;
	}

	public String getProductLeader() {
		return productLeader;
	}

	public void setProductLeader(String productLeader) {
		this.productLeader = productLeader;
	}


	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDateLimitContent() {
		return dateLimitContent;
	}

	public void setDateLimitContent(String dateLimitContent) {
		this.dateLimitContent = dateLimitContent;
	}


	public String getIsShowProductNumber() {
		return isShowProductNumber;
	}

	public void setIsShowProductNumber(String isShowProductNumber) {
		this.isShowProductNumber = isShowProductNumber;
	}



	public String getIsShowChineseName() {
		return isShowChineseName;
	}

	public void setIsShowChineseName(String isShowChineseName) {
		this.isShowChineseName = isShowChineseName;
	}

	public String getIsShowEnglishName() {
		return isShowEnglishName;
	}

	public void setIsShowEnglishName(String isShowEnglishName) {
		this.isShowEnglishName = isShowEnglishName;
	}

	public String getIsShowCountryOfOrigin() {
		return isShowCountryOfOrigin;
	}

	public void setIsShowCountryOfOrigin(String isShowCountryOfOrigin) {
		this.isShowCountryOfOrigin = isShowCountryOfOrigin;
	}

	public String getIsShowProductType() {
		return isShowProductType;
	}

	public void setIsShowProductType(String isShowProductType) {
		this.isShowProductType = isShowProductType;
	}

	public String getIsShowWorkMatters() {
		return isShowWorkMatters;
	}

	public void setIsShowWorkMatters(String isShowWorkMatters) {
		this.isShowWorkMatters = isShowWorkMatters;
	}

	public String getIsShowProductLeader() {
		return isShowProductLeader;
	}

	public void setIsShowProductLeader(String isShowProductLeader) {
		this.isShowProductLeader = isShowProductLeader;
	}

	public String getIsShowEnterpriseApplication() {
		return isShowEnterpriseApplication;
	}

	public void setIsShowEnterpriseApplication(String isShowEnterpriseApplication) {
		this.isShowEnterpriseApplication = isShowEnterpriseApplication;
	}

	public String getIsShowEnterpriseApplicationAddress() {
		return isShowEnterpriseApplicationAddress;
	}

	public void setIsShowEnterpriseApplicationAddress(String isShowEnterpriseApplicationAddress) {
		this.isShowEnterpriseApplicationAddress = isShowEnterpriseApplicationAddress;
	}

	public String getIsShowEnterpriseApplicationPhone() {
		return isShowEnterpriseApplicationPhone;
	}

	public void setIsShowEnterpriseApplicationPhone(String isShowEnterpriseApplicationPhone) {
		this.isShowEnterpriseApplicationPhone = isShowEnterpriseApplicationPhone;
	}

	public String getIsShowEnterpriseApplicationContacts() {
		return isShowEnterpriseApplicationContacts;
	}

	public void setIsShowEnterpriseApplicationContacts(String isShowEnterpriseApplicationContacts) {
		this.isShowEnterpriseApplicationContacts = isShowEnterpriseApplicationContacts;
	}

	public String getIsShowActualProductionEnterprise() {
		return isShowActualProductionEnterprise;
	}

	public void setIsShowActualProductionEnterprise(String isShowActualProductionEnterprise) {
		this.isShowActualProductionEnterprise = isShowActualProductionEnterprise;
	}

	public String getIsShowActualProductionEnterpriseAddress() {
		return isShowActualProductionEnterpriseAddress;
	}

	public void setIsShowActualProductionEnterpriseAddress(String isShowActualProductionEnterpriseAddress) {
		this.isShowActualProductionEnterpriseAddress = isShowActualProductionEnterpriseAddress;
	}

	public String getIsShowResponsibleUnitInChina() {
		return isShowResponsibleUnitInChina;
	}

	public void setIsShowResponsibleUnitInChina(String isShowResponsibleUnitInChina) {
		this.isShowResponsibleUnitInChina = isShowResponsibleUnitInChina;
	}

	public String getIsShowResponsibleUnitInChinaAddress() {
		return isShowResponsibleUnitInChinaAddress;
	}

	public void setIsShowResponsibleUnitInChinaAddress(String isShowResponsibleUnitInChinaAddress) {
		this.isShowResponsibleUnitInChinaAddress = isShowResponsibleUnitInChinaAddress;
	}

	public String getIsShowResponsibleUnitInChinaPhone() {
		return isShowResponsibleUnitInChinaPhone;
	}

	public void setIsShowResponsibleUnitInChinaPhone(String isShowResponsibleUnitInChinaPhone) {
		this.isShowResponsibleUnitInChinaPhone = isShowResponsibleUnitInChinaPhone;
	}

	public String getIsShowResponsibleUnitInChinaFax() {
		return isShowResponsibleUnitInChinaFax;
	}

	public void setIsShowResponsibleUnitInChinaFax(String isShowResponsibleUnitInChinaFax) {
		this.isShowResponsibleUnitInChinaFax = isShowResponsibleUnitInChinaFax;
	}

	public String getIsShowResponsibleUnitInChinaZipCode() {
		return isShowResponsibleUnitInChinaZipCode;
	}

	public void setIsShowResponsibleUnitInChinaZipCode(String isShowResponsibleUnitInChinaZipCode) {
		this.isShowResponsibleUnitInChinaZipCode = isShowResponsibleUnitInChinaZipCode;
	}

	public String getIsShowProjectTime() {
		return isShowProjectTime;
	}

	public void setIsShowProjectTime(String isShowProjectTime) {
		this.isShowProjectTime = isShowProjectTime;
	}

	public String getIsShowContractNumber() {
		return isShowContractNumber;
	}

	public void setIsShowContractNumber(String isShowContractNumber) {
		this.isShowContractNumber = isShowContractNumber;
	}

	public String getIsShowContractSigningTime() {
		return isShowContractSigningTime;
	}

	public void setIsShowContractSigningTime(String isShowContractSigningTime) {
		this.isShowContractSigningTime = isShowContractSigningTime;
	}

	public String getIsShowArrivalCompany() {
		return isShowArrivalCompany;
	}

	public void setIsShowArrivalCompany(String isShowArrivalCompany) {
		this.isShowArrivalCompany = isShowArrivalCompany;
	}

	public String getIsShowArrivalTime() {
		return isShowArrivalTime;
	}

	public void setIsShowArrivalTime(String isShowArrivalTime) {
		this.isShowArrivalTime = isShowArrivalTime;
	}

	public String getIsShowSampleTime() {
		return isShowSampleTime;
	}

	public void setIsShowSampleTime(String isShowSampleTime) {
		this.isShowSampleTime = isShowSampleTime;
	}

	public String getIsShowSampleQuantity() {
		return isShowSampleQuantity;
	}

	public void setIsShowSampleQuantity(String isShowSampleQuantity) {
		this.isShowSampleQuantity = isShowSampleQuantity;
	}

	public String getIsShowTotalNumberOfSamples() {
		return isShowTotalNumberOfSamples;
	}

	public void setIsShowTotalNumberOfSamples(String isShowTotalNumberOfSamples) {
		this.isShowTotalNumberOfSamples = isShowTotalNumberOfSamples;
	}

	public String getIsShowColorCharacter() {
		return isShowColorCharacter;
	}

	public void setIsShowColorCharacter(String isShowColorCharacter) {
		this.isShowColorCharacter = isShowColorCharacter;
	}

	public String getIsShowSampleMarking() {
		return isShowSampleMarking;
	}

	public void setIsShowSampleMarking(String isShowSampleMarking) {
		this.isShowSampleMarking = isShowSampleMarking;
	}

	public String getIsShowDateOfExpiry() {
		return isShowDateOfExpiry;
	}

	public void setIsShowDateOfExpiry(String isShowDateOfExpiry) {
		this.isShowDateOfExpiry = isShowDateOfExpiry;
	}

	public String getIsShowTechnologyDateOfExpiry() {
		return isShowTechnologyDateOfExpiry;
	}

	public void setIsShowTechnologyDateOfExpiry(String isShowTechnologyDateOfExpiry) {
		this.isShowTechnologyDateOfExpiry = isShowTechnologyDateOfExpiry;
	}

	public String getIsShowSmell() {
		return isShowSmell;
	}

	public void setIsShowSmell(String isShowSmell) {
		this.isShowSmell = isShowSmell;
	}

	public String getIsShowSpecifications() {
		return isShowSpecifications;
	}

	public void setIsShowSpecifications(String isShowSpecifications) {
		this.isShowSpecifications = isShowSpecifications;
	}

	public String getIsShowAdministrativeLicenseInspectionTime() {
		return isShowAdministrativeLicenseInspectionTime;
	}

	public void setIsShowAdministrativeLicenseInspectionTime(String isShowAdministrativeLicenseInspectionTime) {
		this.isShowAdministrativeLicenseInspectionTime = isShowAdministrativeLicenseInspectionTime;
	}

	public String getIsShowAdministrativeLicenseInspectionOrganization() {
		return isShowAdministrativeLicenseInspectionOrganization;
	}

	public void setIsShowAdministrativeLicenseInspectionOrganization(String isShowAdministrativeLicenseInspectionOrganization) {
		this.isShowAdministrativeLicenseInspectionOrganization = isShowAdministrativeLicenseInspectionOrganization;
	}

	public String getIsShowAdministrativeLicenseInspectionProject() {
		return isShowAdministrativeLicenseInspectionProject;
	}

	public void setIsShowAdministrativeLicenseInspectionProject(String isShowAdministrativeLicenseInspectionProject) {
		this.isShowAdministrativeLicenseInspectionProject = isShowAdministrativeLicenseInspectionProject;
	}

	public String getIsShowAdministrativeLicenseInspectionNumber() {
		return isShowAdministrativeLicenseInspectionNumber;
	}

	public void setIsShowAdministrativeLicenseInspectionNumber(String isShowAdministrativeLicenseInspectionNumber) {
		this.isShowAdministrativeLicenseInspectionNumber = isShowAdministrativeLicenseInspectionNumber;
	}

	public String getIsShowAdministrativeLicenseInspectionNo() {
		return isShowAdministrativeLicenseInspectionNo;
	}

	public void setIsShowAdministrativeLicenseInspectionNo(String isShowAdministrativeLicenseInspectionNo) {
		this.isShowAdministrativeLicenseInspectionNo = isShowAdministrativeLicenseInspectionNo;
	}

	public String getIsShowAdministrativeLicenseInspectionReportTime() {
		return isShowAdministrativeLicenseInspectionReportTime;
	}

	public void setIsShowAdministrativeLicenseInspectionReportTime(String isShowAdministrativeLicenseInspectionReportTime) {
		this.isShowAdministrativeLicenseInspectionReportTime = isShowAdministrativeLicenseInspectionReportTime;
	}

	public String getIsShowSendBodyTime() {
		return isShowSendBodyTime;
	}

	public void setIsShowSendBodyTime(String isShowSendBodyTime) {
		this.isShowSendBodyTime = isShowSendBodyTime;
	}

	public String getIsShowSendBodyOrganization() {
		return isShowSendBodyOrganization;
	}

	public void setIsShowSendBodyOrganization(String isShowSendBodyOrganization) {
		this.isShowSendBodyOrganization = isShowSendBodyOrganization;
	}

	public String getIsShowSendBodyProject() {
		return isShowSendBodyProject;
	}

	public void setIsShowSendBodyProject(String isShowSendBodyProject) {
		this.isShowSendBodyProject = isShowSendBodyProject;
	}

	public String getIsShowSendBodyNumber() {
		return isShowSendBodyNumber;
	}

	public void setIsShowSendBodyNumber(String isShowSendBodyNumber) {
		this.isShowSendBodyNumber = isShowSendBodyNumber;
	}

	public String getIsShowHumanTestAcceptanceNo() {
		return isShowHumanTestAcceptanceNo;
	}

	public void setIsShowHumanTestAcceptanceNo(String isShowHumanTestAcceptanceNo) {
		this.isShowHumanTestAcceptanceNo = isShowHumanTestAcceptanceNo;
	}

	public String getIsShowHumanTestAcceptanceReportTime() {
		return isShowHumanTestAcceptanceReportTime;
	}

	public void setIsShowHumanTestAcceptanceReportTime(String isShowHumanTestAcceptanceReportTime) {
		this.isShowHumanTestAcceptanceReportTime = isShowHumanTestAcceptanceReportTime;
	}

	public String getIsShowSendRiskTestTime() {
		return isShowSendRiskTestTime;
	}

	public void setIsShowSendRiskTestTime(String isShowSendRiskTestTime) {
		this.isShowSendRiskTestTime = isShowSendRiskTestTime;
	}

	public String getIsShowSendRiskTestOrganization() {
		return isShowSendRiskTestOrganization;
	}

	public void setIsShowSendRiskTestOrganization(String isShowSendRiskTestOrganization) {
		this.isShowSendRiskTestOrganization = isShowSendRiskTestOrganization;
	}

	public String getIsShowSendRiskTestProjectr() {
		return isShowSendRiskTestProjectr;
	}

	public void setIsShowSendRiskTestProjectr(String isShowSendRiskTestProjectr) {
		this.isShowSendRiskTestProjectr = isShowSendRiskTestProjectr;
	}

	public String getIsShowSendRiskTestNumber() {
		return isShowSendRiskTestNumber;
	}

	public void setIsShowSendRiskTestNumber(String isShowSendRiskTestNumber) {
		this.isShowSendRiskTestNumber = isShowSendRiskTestNumber;
	}

	public String getIsShowRiskTestAcceptanceNo() {
		return isShowRiskTestAcceptanceNo;
	}

	public void setIsShowRiskTestAcceptanceNo(String isShowRiskTestAcceptanceNo) {
		this.isShowRiskTestAcceptanceNo = isShowRiskTestAcceptanceNo;
	}

	public String getIsShowRiskTestAcceptanceReportTime() {
		return isShowRiskTestAcceptanceReportTime;
	}

	public void setIsShowRiskTestAcceptanceReportTime(String isShowRiskTestAcceptanceReportTime) {
		this.isShowRiskTestAcceptanceReportTime = isShowRiskTestAcceptanceReportTime;
	}

	public String getIsShowReportTime() {
		return isShowReportTime;
	}

	public void setIsShowReportTime(String isShowReportTime) {
		this.isShowReportTime = isShowReportTime;
	}

	public String getIsShowAcceptanceTime() {
		return isShowAcceptanceTime;
	}

	public void setIsShowAcceptanceTime(String isShowAcceptanceTime) {
		this.isShowAcceptanceTime = isShowAcceptanceTime;
	}

	public String getIsShowAcceptanceNumber() {
		return isShowAcceptanceNumber;
	}

	public void setIsShowAcceptanceNumber(String isShowAcceptanceNumber) {
		this.isShowAcceptanceNumber = isShowAcceptanceNumber;
	}

	public String getIsShowDocumentTime() {
		return isShowDocumentTime;
	}

	public void setIsShowDocumentTime(String isShowDocumentTime) {
		this.isShowDocumentTime = isShowDocumentTime;
	}

	public String getIsShowDocumentNumber() {
		return isShowDocumentNumber;
	}

	public void setIsShowDocumentNumber(String isShowDocumentNumber) {
		this.isShowDocumentNumber = isShowDocumentNumber;
	}

	public String getIsShowProductStatusRemark() {
		return isShowProductStatusRemark;
	}

	public void setIsShowProductStatusRemark(String isShowProductStatusRemark) {
		this.isShowProductStatusRemark = isShowProductStatusRemark;
	}

	public String getIsShowNextOpinionTime() {
		return isShowNextOpinionTime;
	}

	public void setIsShowNextOpinionTime(String isShowNextOpinionTime) {
		this.isShowNextOpinionTime = isShowNextOpinionTime;
	}

	public String getIsShowOpinionContent() {
		return isShowOpinionContent;
	}

	public void setIsShowOpinionContent(String isShowOpinionContent) {
		this.isShowOpinionContent = isShowOpinionContent;
	}

	public String getIsShowReplyOpinion() {
		return isShowReplyOpinion;
	}

	public void setIsShowReplyOpinion(String isShowReplyOpinion) {
		this.isShowReplyOpinion = isShowReplyOpinion;
	}

	public String getIsShowOtherDescription() {
		return isShowOtherDescription;
	}

	public void setIsShowOtherDescription(String isShowOtherDescription) {
		this.isShowOtherDescription = isShowOtherDescription;
	}

	public boolean isFirst() {
		return isFirst;
	}

	public void setFirst(boolean first) {
		isFirst = first;
	}

	public String getIsShowProjectLeader() {
		return isShowProjectLeader;
	}

	public void setIsShowProjectLeader(String isShowProjectLeader) {
		this.isShowProjectLeader = isShowProjectLeader;
	}
}