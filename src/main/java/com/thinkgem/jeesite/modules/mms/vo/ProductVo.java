package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 产品 VO
 * Created by jjeejj on 2017/3/15.
 */
public class ProductVo {

    private String id;

    private String productNumber;		// 产品编号
    private String englishName;		// 英文名称
    private String chineseName;		// 中文名称
    private String productType;		// 类别
    private String workMatters;		// 工作事项
    private String productLeader;		// 产品负责人
    private String projectLeader;		// 项目负责人
    private String enterpriseApplication;		// 申请企业
    private String actualProductionEnterprise;		// 实际生产企业
    private String responsibleUnitInChina;		// 在华责任单位
    private Date projectTime;		// 立项时间
    private Date contractSigningTime;		// 合同签订时间

    private Date sampleTime;		// 来样时间
    private String sampleQuantity;		// 样品数量
    private String totalNumberOfSamples;		// 送检总数
    private Date submissionTime;		// 送检时间

    private Date inspectionReportTime;		// 取送检报告时间
    private Date sendBodyTime;		// 送人体时间
    private Date bodyReportTime;		// 取送人体报告时间
    private Date reportTime;		// 上报时间
    private Date acceptanceTime;		// 受理时间
    private Date documentTime;		// 批件时间
    private Date nextOpinionTime;		// 下意见时间
    private Date replyOpinion;		// 回复意见时间
    private String remarks;	// 备注

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

    @ExcelField(title = "实际生产企业",sort = 90)
    public String getActualProductionEnterprise() {
        return actualProductionEnterprise;
    }

    public void setActualProductionEnterprise(String actualProductionEnterprise) {
        this.actualProductionEnterprise = actualProductionEnterprise;
    }

    @ExcelField(title = "在华责任单位",sort = 100)
    public String getResponsibleUnitInChina() {
        return responsibleUnitInChina;
    }

    public void setResponsibleUnitInChina(String responsibleUnitInChina) {
        this.responsibleUnitInChina = responsibleUnitInChina;
    }

    @ExcelField(title = "立项时间",sort = 110)
    public Date getProjectTime() {
        return projectTime;
    }

    public void setProjectTime(Date projectTime) {
        this.projectTime = projectTime;
    }

    @ExcelField(title = "合同签订时间",sort = 120)
    public Date getContractSigningTime() {
        return contractSigningTime;
    }

    public void setContractSigningTime(Date contractSigningTime) {
        this.contractSigningTime = contractSigningTime;
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

    @ExcelField(title = "送检时间",sort = 160)
    public Date getSubmissionTime() {
        return submissionTime;
    }

    public void setSubmissionTime(Date submissionTime) {
        this.submissionTime = submissionTime;
    }

    @ExcelField(title = "取送检报告时间",sort = 170)
    public Date getInspectionReportTime() {
        return inspectionReportTime;
    }

    public void setInspectionReportTime(Date inspectionReportTime) {
        this.inspectionReportTime = inspectionReportTime;
    }

    @ExcelField(title = "送人体时间",sort = 180)
    public Date getSendBodyTime() {
        return sendBodyTime;
    }

    public void setSendBodyTime(Date sendBodyTime) {
        this.sendBodyTime = sendBodyTime;
    }
    @ExcelField(title = "取送人体报告时间",sort = 190)
    public Date getBodyReportTime() {
        return bodyReportTime;
    }

    public void setBodyReportTime(Date bodyReportTime) {
        this.bodyReportTime = bodyReportTime;
    }

    @ExcelField(title = "上报时间",sort = 200)
    public Date getReportTime() {
        return reportTime;
    }

    public void setReportTime(Date reportTime) {
        this.reportTime = reportTime;
    }

    @ExcelField(title = "受理时间",sort = 210)
    public Date getAcceptanceTime() {
        return acceptanceTime;
    }

    public void setAcceptanceTime(Date acceptanceTime) {
        this.acceptanceTime = acceptanceTime;
    }

    @ExcelField(title = "批件时间",sort = 220)
    public Date getDocumentTime() {
        return documentTime;
    }

    public void setDocumentTime(Date documentTime) {
        this.documentTime = documentTime;
    }

    @ExcelField(title = "下意见时间",sort = 230)
    public Date getNextOpinionTime() {
        return nextOpinionTime;
    }

    public void setNextOpinionTime(Date nextOpinionTime) {
        this.nextOpinionTime = nextOpinionTime;
    }

    @ExcelField(title = "回复意见时间",sort = 240)
    public Date getReplyOpinion() {
        return replyOpinion;
    }

    public void setReplyOpinion(Date replyOpinion) {
        this.replyOpinion = replyOpinion;
    }

    @ExcelField(title = "备注",sort = 250)
    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
