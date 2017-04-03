package com.thinkgem.jeesite.modules.mms.vo;

import com.thinkgem.jeesite.common.persistence.DataEntity;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;

/**
 * 统计产品负责人各个状态的产品数量
 *
 * 老板看业绩使用的
 * Created by jiang on 2017/4/3.
 */
public class ProductStatusVo extends DataEntity<ProductStatusVo> {

    private String productLeader;		// 产品负责人
    private String trialProductNumber;		// 初审产品数量
    private String inspectionProductNumber;		// 送检产品数量
    private String declareProductNumber;		// 申报产品数量
    private String perfectInformationProductNumber;		// 完善资料产品数量
    private String getApprovalProductNumber;		// 取得批件产品数量
    private String notApprovedProductNumber;		// 不予批准产品数量
    private String terminationDeclarationProductNumber;		// 终止申报产品数量

    private String startDate;//开始日期
    private String endDate; //结束日期


    @ExcelField(title = "产品负责人",sort = 10)
    public String getProductLeader() {
        return productLeader;
    }

    public void setProductLeader(String productLeader) {
        this.productLeader = productLeader;
    }

    @ExcelField(title = "初审产品数量",sort = 20)
    public String getTrialProductNumber() {
        return trialProductNumber;
    }

    public void setTrialProductNumber(String trialProductNumber) {
        this.trialProductNumber = trialProductNumber;
    }

    @ExcelField(title = "送检产品数量",sort = 30)
    public String getInspectionProductNumber() {
        return inspectionProductNumber;
    }

    public void setInspectionProductNumber(String inspectionProductNumber) {
        this.inspectionProductNumber = inspectionProductNumber;
    }

    @ExcelField(title = "申报产品数量",sort = 40)
    public String getDeclareProductNumber() {
        return declareProductNumber;
    }

    public void setDeclareProductNumber(String declareProductNumber) {
        this.declareProductNumber = declareProductNumber;
    }

    @ExcelField(title = "完善资料产品数量",sort = 50)
    public String getPerfectInformationProductNumber() {
        return perfectInformationProductNumber;
    }

    public void setPerfectInformationProductNumber(String perfectInformationProductNumber) {
        this.perfectInformationProductNumber = perfectInformationProductNumber;
    }

    @ExcelField(title = "取得批件产品数量",sort = 60)
    public String getGetApprovalProductNumber() {
        return getApprovalProductNumber;
    }

    public void setGetApprovalProductNumber(String getApprovalProductNumber) {
        this.getApprovalProductNumber = getApprovalProductNumber;
    }

    @ExcelField(title = "不予批准产品数量",sort = 70)
    public String getNotApprovedProductNumber() {
        return notApprovedProductNumber;
    }

    public void setNotApprovedProductNumber(String notApprovedProductNumber) {
        this.notApprovedProductNumber = notApprovedProductNumber;
    }

    @ExcelField(title = "终止申报产品数量",sort = 80)
    public String getTerminationDeclarationProductNumber() {
        return terminationDeclarationProductNumber;
    }

    public void setTerminationDeclarationProductNumber(String terminationDeclarationProductNumber) {
        this.terminationDeclarationProductNumber = terminationDeclarationProductNumber;
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
}
