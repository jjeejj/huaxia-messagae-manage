package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 产品 VO，导出全部数据使用的
 * Created by jjeejj on 2017/3/15.
 */
public class ProductVo {

    private String id;

    private String productNumber;		// 产品编号
    private String englishName;		// 英文名称
    private String chineseName;		// 中文名称
    private String countryOfOrigin;		// 原产国
    private String productType;		// 类别
    private String workMatters;		// 工作事项
    private String productLeader;		// 产品负责人
    private String projectLeader;		// 项目负责人
    private String enterpriseApplication;		// 申请企业
    private String enterpriseApplicationAddress;		// 申请企业地址
    private String enterpriseApplicationPhone;		// 申请企业电话
    private String enterpriseApplicationContacts;		// 申请企业联系人
    private String actualProductionEnterprise;		// 实际生产企业
    private String actualProductionEnterpriseAddress;		// 实际生产企业地址
    private String responsibleUnitInChina;		// 在华责任单位
    private String responsibleUnitInChinaAddress;		// 在华责任单位地址
    private String responsibleUnitInChinaPhone;		// 在华责任单位电话
    private String responsibleUnitInChinaFax;		// 在华责任单位传真
    private String responsibleUnitInChinaZipCode;		// 在华责任单位邮编
    private Date projectTime;		// 立项时间
    private String contractNumber;		// 合同编号
    private Date contractSigningTime;		// 合同签订时间
    private String arrivalCompany;		// 来款单位

    private Date arrivalTime;		// 来款时间
    private Date sampleTime;		// 来样时间
    private String sampleQuantity;		// 样品数量
    private String totalNumberOfSamples;		// 送检总数

    private String colorCharacter;		// 颜色性状
    private String sampleMarking;		// 样品标记（生产日期或批号）
    private String dateOfExpiry;		// 保质期或限期使用日期（包装标注的保质期或限期使用日期）
    private String technologyDateOfExpiry;		// 保质期（技术要求中显示的保质期）
    private String smell;		// 气味
    private String specifications;		// 规格

    private Date administrativeLicenseInspectionTime;		// 行政许可送检时间
    private String administrativeLicenseInspectionOrganization;		// 行政许可检验机构
    private String administrativeLicenseInspectionProject;		// 行政许可检验项目
    private String administrativeLicenseInspectionNumber;		// 行政许可送检数量
    private String administrativeLicenseInspectionNo;		// 行政许可检验受理编号
    private Date administrativeLicenseInspectionReportTime;		// 行政许可检验取报告时间

    private Date sendBodyTime;		// 人体检验送检时间
    private String sendBodyOrganization;		// 人体检验送检机构
    private String sendBodyProject;		// 人体检验项目
    private String sendBodyNumber;		// 人体检验数量
    private String humanTestAcceptanceNo;		// 人体检验受理编号
    private Date humanTestAcceptanceReportTime;		// 人体检验取报告时间

    private Date sendRiskTestTime;		// 风险检验时间
    private String sendRiskTestOrganization;		// 风险检验机构
    private String sendRiskTestProject;		// 风险检验项目
    private String sendRiskTestNumber;		// 风险检验数量
    private String riskTestAcceptanceNo;		// 风险检验受理编号
    private Date riskTestAcceptanceReportTime;		// 风险检验取报告时间

    private Date reportTime;		// 上报时间／申报时间
    private Date acceptanceTime;		// 受理时间
    private String acceptanceNumber;		// 受理编号
    private Date documentTime;		// 取得批件时间
    private String documentNumber;		// 批件编号
    private String productStatusRemark;		// 产品状态备注（1：通过审批，2：不予批准3：终止申报;）
    private Date nextOpinionTime;		// 下意见时间
    private String opinionContent;		// 意见内容
    private Date replyOpinion;		// 回复意见时间
    private String otherDescription;		// 其他说明

    @ExcelField(title = "产品编号",sort = 10)
    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    @ExcelField(title = "英文名称",sort = 30)
    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }

    @ExcelField(title = "中文名称",sort = 20)
    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    @ExcelField(title = "原产国",sort = 35)
    public String getCountryOfOrigin() {
        return countryOfOrigin;
    }

    public void setCountryOfOrigin(String countryOfOrigin) {
        this.countryOfOrigin = countryOfOrigin;
    }

    @ExcelField(title = "类别",sort = 40)
    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    @ExcelField(title = "工作事项",sort = 50)
    public String getWorkMatters() {
        return workMatters;
    }

    public void setWorkMatters(String workMatters) {
        this.workMatters = workMatters;
    }

    @ExcelField(title = "产品负责人",sort = 60)
    public String getProductLeader() {
        return productLeader;
    }

    public void setProductLeader(String productLeader) {
        this.productLeader = productLeader;
    }

    @ExcelField(title = "项目负责人",sort = 70)
    public String getProjectLeader() {
        return projectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        this.projectLeader = projectLeader;
    }

    @ExcelField(title = "申请企业",sort = 80)
    public String getEnterpriseApplication() {
        return enterpriseApplication;
    }

    public void setEnterpriseApplication(String enterpriseApplication) {
        this.enterpriseApplication = enterpriseApplication;
    }
    @ExcelField(title = "申请企业地址",sort = 82)
    public String getEnterpriseApplicationAddress() {
        return enterpriseApplicationAddress;
    }

    public void setEnterpriseApplicationAddress(String enterpriseApplicationAddress) {
        this.enterpriseApplicationAddress = enterpriseApplicationAddress;
    }
    @ExcelField(title = "申请企业电话",sort = 84)
    public String getEnterpriseApplicationPhone() {
        return enterpriseApplicationPhone;
    }

    public void setEnterpriseApplicationPhone(String enterpriseApplicationPhone) {
        this.enterpriseApplicationPhone = enterpriseApplicationPhone;
    }
    @ExcelField(title = "申请企业联系人",sort = 86)
    public String getEnterpriseApplicationContacts() {
        return enterpriseApplicationContacts;
    }

    public void setEnterpriseApplicationContacts(String enterpriseApplicationContacts) {
        this.enterpriseApplicationContacts = enterpriseApplicationContacts;
    }

    @ExcelField(title = "实际生产企业",sort = 90)
    public String getActualProductionEnterprise() {
        return actualProductionEnterprise;
    }

    public void setActualProductionEnterprise(String actualProductionEnterprise) {
        this.actualProductionEnterprise = actualProductionEnterprise;
    }

    @ExcelField(title = "实际生产企业地址",sort = 92)
    public String getActualProductionEnterpriseAddress() {
        return actualProductionEnterpriseAddress;
    }

    public void setActualProductionEnterpriseAddress(String actualProductionEnterpriseAddress) {
        this.actualProductionEnterpriseAddress = actualProductionEnterpriseAddress;
    }

    @ExcelField(title = "在华责任单位",sort = 100)
    public String getResponsibleUnitInChina() {
        return responsibleUnitInChina;
    }

    public void setResponsibleUnitInChina(String responsibleUnitInChina) {
        this.responsibleUnitInChina = responsibleUnitInChina;
    }
    @ExcelField(title = "在华责任单位地址",sort = 102)
    public String getResponsibleUnitInChinaAddress() {
        return responsibleUnitInChinaAddress;
    }

    public void setResponsibleUnitInChinaAddress(String responsibleUnitInChinaAddress) {
        this.responsibleUnitInChinaAddress = responsibleUnitInChinaAddress;
    }
    @ExcelField(title = "在华责任单位电话",sort = 104)
    public String getResponsibleUnitInChinaPhone() {
        return responsibleUnitInChinaPhone;
    }

    public void setResponsibleUnitInChinaPhone(String responsibleUnitInChinaPhone) {
        this.responsibleUnitInChinaPhone = responsibleUnitInChinaPhone;
    }
    @ExcelField(title = "在华责任单位传真",sort = 106)
    public String getResponsibleUnitInChinaFax() {
        return responsibleUnitInChinaFax;
    }

    public void setResponsibleUnitInChinaFax(String responsibleUnitInChinaFax) {
        this.responsibleUnitInChinaFax = responsibleUnitInChinaFax;
    }
    @ExcelField(title = "在华责任单位邮编",sort = 108)
    public String getResponsibleUnitInChinaZipCode() {
        return responsibleUnitInChinaZipCode;
    }

    public void setResponsibleUnitInChinaZipCode(String responsibleUnitInChinaZipCode) {
        this.responsibleUnitInChinaZipCode = responsibleUnitInChinaZipCode;
    }

    @ExcelField(title = "立项时间",sort = 110)
    public Date getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    @ExcelField(title = "合同编号",sort = 112)
    public String getContractNumber() {
        return contractNumber;
    }

    public void setContractNumber(String contractNumber) {
        this.contractNumber = contractNumber;
    }

    @ExcelField(title = "合同签订时间",sort = 120)
    public Date getContractSigningTime() {
        return contractSigningTime;
    }

    public void setContractSigningTime(Date contractSigningTime) {
        this.contractSigningTime = contractSigningTime;
    }

    @ExcelField(title = "来款单位",sort = 122)
    public String getArrivalCompany() {
        return arrivalCompany;
    }

    public void setArrivalCompany(String arrivalCompany) {
        this.arrivalCompany = arrivalCompany;
    }
    @ExcelField(title = "来款时间",sort = 124)
    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    @ExcelField(title = "来样时间",sort = 130)
    public Date getSampleTime() {
        return sampleTime;
    }

    public void setSampleTime(Date sampleTime) {
        this.sampleTime = sampleTime;
    }

    @ExcelField(title = "样品数量",sort = 140)
    public String getSampleQuantity() {
        return sampleQuantity;
    }

    public void setSampleQuantity(String sampleQuantity) {
        this.sampleQuantity = sampleQuantity;
    }

    @ExcelField(title = "送检总数",sort = 150)
    public String getTotalNumberOfSamples() {
        return totalNumberOfSamples;
    }

    public void setTotalNumberOfSamples(String totalNumberOfSamples) {
        this.totalNumberOfSamples = totalNumberOfSamples;
    }
    @ExcelField(title = "颜色性状",sort = 151)
    public String getColorCharacter() {
        return colorCharacter;
    }

    public void setColorCharacter(String colorCharacter) {
        this.colorCharacter = colorCharacter;
    }
    @ExcelField(title = "样品标记（生产日期或批号）",sort = 152)
    public String getSampleMarking() {
        return sampleMarking;
    }

    public void setSampleMarking(String sampleMarking) {
        this.sampleMarking = sampleMarking;
    }
    @ExcelField(title = "保质期或限期使用日期",sort = 153)
    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
    @ExcelField(title = "保质期",sort = 154)
    public String getTechnologyDateOfExpiry() {
        return technologyDateOfExpiry;
    }

    public void setTechnologyDateOfExpiry(String technologyDateOfExpiry) {
        this.technologyDateOfExpiry = technologyDateOfExpiry;
    }
    @ExcelField(title = "气味",sort = 155)
    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }
    @ExcelField(title = "规格",sort = 156)
    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ExcelField(title = "行政许可送检时间",sort = 160)
    public Date getAdministrativeLicenseInspectionTime() {
        return administrativeLicenseInspectionTime;
    }

    public void setAdministrativeLicenseInspectionTime(Date administrativeLicenseInspectionTime) {
        this.administrativeLicenseInspectionTime = administrativeLicenseInspectionTime;
    }
    @ExcelField(title = "行政许可检验机构",sort = 162)
    public String getAdministrativeLicenseInspectionOrganization() {
        return administrativeLicenseInspectionOrganization;
    }

    public void setAdministrativeLicenseInspectionOrganization(String administrativeLicenseInspectionOrganization) {
        this.administrativeLicenseInspectionOrganization = administrativeLicenseInspectionOrganization;
    }
    @ExcelField(title = "行政许可检验项目",sort = 164)
    public String getAdministrativeLicenseInspectionProject() {
        return administrativeLicenseInspectionProject;
    }

    public void setAdministrativeLicenseInspectionProject(String administrativeLicenseInspectionProject) {
        this.administrativeLicenseInspectionProject = administrativeLicenseInspectionProject;
    }
    @ExcelField(title = "行政许可送检数量",sort = 166)
    public String getAdministrativeLicenseInspectionNumber() {
        return administrativeLicenseInspectionNumber;
    }

    public void setAdministrativeLicenseInspectionNumber(String administrativeLicenseInspectionNumber) {
        this.administrativeLicenseInspectionNumber = administrativeLicenseInspectionNumber;
    }
    @ExcelField(title = "行政许可检验受理编号",sort = 168)
    public String getAdministrativeLicenseInspectionNo() {
        return administrativeLicenseInspectionNo;
    }

    public void setAdministrativeLicenseInspectionNo(String administrativeLicenseInspectionNo) {
        this.administrativeLicenseInspectionNo = administrativeLicenseInspectionNo;
    }
    @ExcelField(title = "行政许可检验取报告时间",sort = 170)
    public Date getAdministrativeLicenseInspectionReportTime() {
        return administrativeLicenseInspectionReportTime;
    }

    public void setAdministrativeLicenseInspectionReportTime(Date administrativeLicenseInspectionReportTime) {
        this.administrativeLicenseInspectionReportTime = administrativeLicenseInspectionReportTime;
    }
    @ExcelField(title = "人体检验送检时间",sort = 172)
    public Date getSendBodyTime() {
        return sendBodyTime;
    }

    public void setSendBodyTime(Date sendBodyTime) {
        this.sendBodyTime = sendBodyTime;
    }
    @ExcelField(title = "人体检验送检机构",sort = 174)
    public String getSendBodyOrganization() {
        return sendBodyOrganization;
    }

    public void setSendBodyOrganization(String sendBodyOrganization) {
        this.sendBodyOrganization = sendBodyOrganization;
    }
    @ExcelField(title = "人体检验项目",sort = 176)
    public String getSendBodyProject() {
        return sendBodyProject;
    }

    public void setSendBodyProject(String sendBodyProject) {
        this.sendBodyProject = sendBodyProject;
    }
    @ExcelField(title = "人体检验数量",sort = 178)
    public String getSendBodyNumber() {
        return sendBodyNumber;
    }

    public void setSendBodyNumber(String sendBodyNumber) {
        this.sendBodyNumber = sendBodyNumber;
    }
    @ExcelField(title = "人体检验受理编号",sort = 180)
    public String getHumanTestAcceptanceNo() {
        return humanTestAcceptanceNo;
    }

    public void setHumanTestAcceptanceNo(String humanTestAcceptanceNo) {
        this.humanTestAcceptanceNo = humanTestAcceptanceNo;
    }
    @ExcelField(title = "人体检验取报告时间",sort = 182)
    public Date getHumanTestAcceptanceReportTime() {
        return humanTestAcceptanceReportTime;
    }

    public void setHumanTestAcceptanceReportTime(Date humanTestAcceptanceReportTime) {
        this.humanTestAcceptanceReportTime = humanTestAcceptanceReportTime;
    }
    @ExcelField(title = "风险检验时间",sort = 184)
    public Date getSendRiskTestTime() {
        return sendRiskTestTime;
    }

    public void setSendRiskTestTime(Date sendRiskTestTime) {
        this.sendRiskTestTime = sendRiskTestTime;
    }
    @ExcelField(title = "风险检验机构",sort = 186)
    public String getSendRiskTestOrganization() {
        return sendRiskTestOrganization;
    }

    public void setSendRiskTestOrganization(String sendRiskTestOrganization) {
        this.sendRiskTestOrganization = sendRiskTestOrganization;
    }
    @ExcelField(title = "风险检验项目",sort = 188)
    public String getSendRiskTestProject() {
        return sendRiskTestProject;
    }

    public void setSendRiskTestProject(String sendRiskTestProject) {
        this.sendRiskTestProject = sendRiskTestProject;
    }
    @ExcelField(title = "风险检验数量",sort = 190)
    public String getSendRiskTestNumber() {
        return sendRiskTestNumber;
    }

    public void setSendRiskTestNumber(String sendRiskTestNumber) {
        this.sendRiskTestNumber = sendRiskTestNumber;
    }
    @ExcelField(title = "风险检验受理编号",sort = 192)
    public String getRiskTestAcceptanceNo() {
        return riskTestAcceptanceNo;
    }

    public void setRiskTestAcceptanceNo(String riskTestAcceptanceNo) {
        this.riskTestAcceptanceNo = riskTestAcceptanceNo;
    }
    @ExcelField(title = "风险检验取报告时间",sort = 194)
    public Date getRiskTestAcceptanceReportTime() {
        return riskTestAcceptanceReportTime;
    }

    public void setRiskTestAcceptanceReportTime(Date riskTestAcceptanceReportTime) {
        this.riskTestAcceptanceReportTime = riskTestAcceptanceReportTime;
    }
    @ExcelField(title = "上报时间／申报时间",sort = 196)
    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }
    @ExcelField(title = "受理时间",sort = 198)
    public Date getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Date acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }
    @ExcelField(title = "受理编号",sort = 200)
    public String getAcceptanceNumber() {
        return acceptanceNumber;
    }

    public void setAcceptanceNumber(String acceptanceNumber) {
        this.acceptanceNumber = acceptanceNumber;
    }
    @ExcelField(title = "取得批件时间",sort = 202)
    public Date getDocumentTime() {
        return documentTime;
    }

    public void setDocumentTime(Date documentTime) {
        this.documentTime = documentTime;
    }
    @ExcelField(title = "批件编号",sort = 204)
    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }
    @ExcelField(title = "产品状态备注",sort = 206,dictType = "product_status_remark")
    public String getProductStatusRemark() {
        return productStatusRemark;
    }

    public void setProductStatusRemark(String productStatusRemark) {
        this.productStatusRemark = productStatusRemark;
    }
    @ExcelField(title = "下意见时间",sort = 208)
    public Date getNextOpinionTime() {
        return nextOpinionTime;
    }

    public void setNextOpinionTime(Date nextOpinionTime) {
        this.nextOpinionTime = nextOpinionTime;
    }
    @ExcelField(title = "意见内容",sort = 210)
    public String getOpinionContent() {
        return opinionContent;
    }

    public void setOpinionContent(String opinionContent) {
        this.opinionContent = opinionContent;
    }
    @ExcelField(title = "回复意见时间",sort = 212)
    public Date getReplyOpinion() {
        return replyOpinion;
    }

    public void setReplyOpinion(Date replyOpinion) {
        this.replyOpinion = replyOpinion;
    }
    @ExcelField(title = "其他说明",sort = 214)
    public String getOtherDescription() {
        return otherDescription;
    }

    public void setOtherDescription(String otherDescription) {
        this.otherDescription = otherDescription;
    }
}
