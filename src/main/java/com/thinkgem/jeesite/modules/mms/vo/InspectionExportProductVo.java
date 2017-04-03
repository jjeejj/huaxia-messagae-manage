package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

import java.util.Date;

/**
 * 送检导出清单字段
 * Created by jiang on 2017/4/3.
 */
public class InspectionExportProductVo {

    private String productNumber;		// 产品编号
    private String englishName;		// 英文名称
    private String chineseName;		// 中文名称
    private String productLeader;		// 产品负责人
    private String enterpriseApplicationAddress;		// 申请企业地址
    private String enterpriseApplicationPhone;		// 申请企业电话
    private String enterpriseApplicationContacts;		// 申请企业联系人
    private String responsibleUnitInChina;		// 在华责任单位
    private String responsibleUnitInChinaAddress;		// 在华责任单位地址
    private String responsibleUnitInChinaPhone;		// 在华责任单位电话
    private String responsibleUnitInChinaFax;		// 在华责任单位传真
    private String responsibleUnitInChinaZipCode;		// 在华责任单位邮编
    private String colorCharacter;		// 颜色性状
    private String sampleMarking;		// 样品标记（生产日期或批号）
    private String dateOfExpiry;		// 保质期或限期使用日期（包装标注的保质期或限期使用日期）
    private String technologyDateOfExpiry;		// 保质期（技术要求中显示的保质期）---（多的）
    private String smell;		// 气味 ----（多的）
    private String specifications;		// 规格
    private Date administrativeLicenseInspectionTime;		// 行政许可送检时间
    private String administrativeLicenseInspectionOrganization;		// 行政许可检验机构
    private String administrativeLicenseInspectionProject;		// 行政许可检验项目
    private String administrativeLicenseInspectionNumber;		// 行政许可送检数量

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

    @ExcelField(title = "产品负责人",sort = 40)
    public String getProductLeader() {
        return productLeader;
    }

    public void setProductLeader(String productLeader) {
        this.productLeader = productLeader;
    }

    @ExcelField(title = "申请企业地址",sort = 50)
    public String getEnterpriseApplicationAddress() {
        return enterpriseApplicationAddress;
    }

    public void setEnterpriseApplicationAddress(String enterpriseApplicationAddress) {
        this.enterpriseApplicationAddress = enterpriseApplicationAddress;
    }
    @ExcelField(title = "申请企业电话",sort = 60)
    public String getEnterpriseApplicationPhone() {
        return enterpriseApplicationPhone;
    }

    public void setEnterpriseApplicationPhone(String enterpriseApplicationPhone) {
        this.enterpriseApplicationPhone = enterpriseApplicationPhone;
    }
    @ExcelField(title = "申请企业联系人",sort = 70)
    public String getEnterpriseApplicationContacts() {
        return enterpriseApplicationContacts;
    }

    public void setEnterpriseApplicationContacts(String enterpriseApplicationContacts) {
        this.enterpriseApplicationContacts = enterpriseApplicationContacts;
    }
    @ExcelField(title = "在华责任单位",sort = 80)
    public String getResponsibleUnitInChina() {
        return responsibleUnitInChina;
    }

    public void setResponsibleUnitInChina(String responsibleUnitInChina) {
        this.responsibleUnitInChina = responsibleUnitInChina;
    }
    @ExcelField(title = "在华责任单位地址",sort = 90)
    public String getResponsibleUnitInChinaAddress() {
        return responsibleUnitInChinaAddress;
    }

    public void setResponsibleUnitInChinaAddress(String responsibleUnitInChinaAddress) {
        this.responsibleUnitInChinaAddress = responsibleUnitInChinaAddress;
    }
    @ExcelField(title = "在华责任单位电话",sort = 100)
    public String getResponsibleUnitInChinaPhone() {
        return responsibleUnitInChinaPhone;
    }

    public void setResponsibleUnitInChinaPhone(String responsibleUnitInChinaPhone) {
        this.responsibleUnitInChinaPhone = responsibleUnitInChinaPhone;
    }
    @ExcelField(title = "在华责任单位传真",sort = 110)
    public String getResponsibleUnitInChinaFax() {
        return responsibleUnitInChinaFax;
    }

    public void setResponsibleUnitInChinaFax(String responsibleUnitInChinaFax) {
        this.responsibleUnitInChinaFax = responsibleUnitInChinaFax;
    }
    @ExcelField(title = "在华责任单位邮编",sort = 120)
    public String getResponsibleUnitInChinaZipCode() {
        return responsibleUnitInChinaZipCode;
    }

    public void setResponsibleUnitInChinaZipCode(String responsibleUnitInChinaZipCode) {
        this.responsibleUnitInChinaZipCode = responsibleUnitInChinaZipCode;
    }
    @ExcelField(title = "颜色性状",sort = 130)
    public String getColorCharacter() {
        return colorCharacter;
    }

    public void setColorCharacter(String colorCharacter) {
        this.colorCharacter = colorCharacter;
    }
    @ExcelField(title = "样品标记（生产日期或批号）",sort = 140)
    public String getSampleMarking() {
        return sampleMarking;
    }

    public void setSampleMarking(String sampleMarking) {
        this.sampleMarking = sampleMarking;
    }
    @ExcelField(title = "保质期或限期使用日期（包装标注的保质期或限期使用日期）",sort = 150)
    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
//    @ExcelField(title = "保质期（技术要求中显示的保质期",sort = 160)
    public String getTechnologyDateOfExpiry() {
        return technologyDateOfExpiry;
    }

    public void setTechnologyDateOfExpiry(String technologyDateOfExpiry) {
        this.technologyDateOfExpiry = technologyDateOfExpiry;
    }
//    @ExcelField(title = "气味",sort = 170)
    public String getSmell() {
        return smell;
    }

    public void setSmell(String smell) {
        this.smell = smell;
    }
    @ExcelField(title = "规格",sort = 180)
    public String getSpecifications() {
        return specifications;
    }

    public void setSpecifications(String specifications) {
        this.specifications = specifications;
    }
    @ExcelField(title = "行政许可送检时间",sort = 190)
    public Date getAdministrativeLicenseInspectionTime() {
        return administrativeLicenseInspectionTime;
    }

    public void setAdministrativeLicenseInspectionTime(Date administrativeLicenseInspectionTime) {
        this.administrativeLicenseInspectionTime = administrativeLicenseInspectionTime;
    }

    @ExcelField(title = "行政许可检验机构",sort = 200)
    public String getAdministrativeLicenseInspectionOrganization() {
        return administrativeLicenseInspectionOrganization;
    }

    public void setAdministrativeLicenseInspectionOrganization(String administrativeLicenseInspectionOrganization) {
        this.administrativeLicenseInspectionOrganization = administrativeLicenseInspectionOrganization;
    }

    @ExcelField(title = "行政许可检验项目",sort = 210)
    public String getAdministrativeLicenseInspectionProject() {
        return administrativeLicenseInspectionProject;
    }

    public void setAdministrativeLicenseInspectionProject(String administrativeLicenseInspectionProject) {
        this.administrativeLicenseInspectionProject = administrativeLicenseInspectionProject;
    }
    @ExcelField(title = "行政许可送检数量",sort = 220)
    public String getAdministrativeLicenseInspectionNumber() {
        return administrativeLicenseInspectionNumber;
    }

    public void setAdministrativeLicenseInspectionNumber(String administrativeLicenseInspectionNumber) {
        this.administrativeLicenseInspectionNumber = administrativeLicenseInspectionNumber;
    }
}
