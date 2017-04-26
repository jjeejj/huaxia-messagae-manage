package com.thinkgem.jeesite.modules.mms.constant;

/**
 * Created by jjeejj on 2017/2/28.
 */
public class MmsConstant {

    /**
     * 成分类型
     * 成分类型(1:正常，2：禁用，3：限用)
     */
    public static final String COMPONENT_TYPE_NORMAL = "1";
    public static final String COMPONENT_TYPE_FORBIDDEN = "2";
    public static final String COMPONENT_TYPE_LIMITED = "3";

    /**
     * 1：限用成分，2：防腐剂，3：防晒剂
     * 4:着色剂
     */

    public static final String PURPOSE_OF_USE_1 = "1";
    public static final String PURPOSE_OF_USE_2 = "2";
    public static final String PURPOSE_OF_USE_3 = "3";
    public static final String PURPOSE_OF_USE_4 = "4";

    public static final String PURPOSE_OF_USE_MESSAGE_1 = "限用成分";
    public static final String PURPOSE_OF_USE_MESSAGE_2 = "防腐剂";
    public static final String PURPOSE_OF_USE_MESSAGE_3 = "防晒剂";
    public static final String PURPOSE_OF_USE_MESSAGE_4 = "着色剂";


    /**
     *   标准中文名称或INCI名的状态(
     * 0:正常,
     * 1:标准中文名称和INCI名不一致,
     * 2：标准中文名称未出现,
     * 3：INCI名未出现,
     * 4:标准中文名称和INCI名都未出现)'
     */
    public static final String NAME_OR_INIC_STATUS_NORMAL = "0"; //0:正常
    public static final String NAME_OR_INIC_STATUS_NOT_MATCH = "1"; //1:标准中文名称和INCI名不一致
    public static final String NAME_STATUS_NOT_FIND = "2"; //2：标准中文名称未出现
    public static final String INIC_STATUS_NOT_FIND = "3"; //3：INCI名未出现
    public static final String NAME_OR_INIC_STATUS_All_NOT_FIND = "4"; //4：标准中文名称和INCI名都未出现

    /**
     * 对于限用成分的实际成份含量状态(
     * 1:符合标准，
     * 2：不符合标准)
     */
    public static final String ACTUAL_COMPONENT_CONTENT_STATUS_NORMAL = "1"; //1:符合标准
    public static final String ACTUAL_COMPONENT_CONTENT_STATUS_NO_NORMAL = "2"; //2：不符合标准
	
	/**
	* 植物成分判断的正则表达式
	*
	*/
	public static final String plantComponentRegex = "\\(\\w*\\)(提取)*[油水汁粉]|\\(\\w*\\)[\\w\\W\\u4e00-\\u9fa5]*\\/";
//    String regex1 = "\\(\\w*\\))[油水汁粉]|\\(\\w*\\)[\\w\\W\\u4e00-\\u9fa5]*\\/";

    /**
     * 着色剂 标准中文名称的 匹配正则表达式
     */
    public static final String colorantRegex = "\\d{5}";

    public static final String PLANT_COMPONENT_YES = "1"; //1:是
    public static final String PLANT_COMPONENT_NO = "2"; //2:不是

    /**
     * 产品状态
     *  初审---1
     *  送检---2
     *   申报----3
     *   完善资料 ---4
     *   取得批件 ----5
     *   不予批准 ----6
     *   终止申报 ----7
     */
    public static final String PRODUCT_STATUS_1 = "1"; //1:初审
    public static final String PRODUCT_STATUS_2 = "2"; //2:送检
    public static final String PRODUCT_STATUS_3 = "3"; //3:申报
    public static final String PRODUCT_STATUS_4 = "4"; //4:完善资料
    public static final String PRODUCT_STATUS_5 = "5"; //5:取得批件
    public static final String PRODUCT_STATUS_6 = "6"; //6:不予批准
    public static final String PRODUCT_STATUS_7 = "7"; //7:终止申报

    /**
     * 产品备注状态
     * 1：通过审批，
     * 2：不予批准
     * 3：终止申报
     */
    public static final String PRODUCT_STATUS_REMARK_1 = "1"; //1:通过审批
    public static final String PRODUCT_STATUS_REMARK_2 = "2"; //2:不予批准
    public static final String PRODUCT_STATUS_REMARK_3 = "3"; //3:终止申报

    /**
     * 产品进度
     *  初审---20%
     *  送检---40%
     *   申报----60%
     *   完善资料 ---80%
     *   取得批件 ----100%
     *   不予批准 ----100%
     *   终止申报 ----100%
     */
    public static final String PRODUCT_PROCESS_1 = "20"; //1:初审
    public static final String PRODUCT_PROCESS_2 = "40"; //2:送检
    public static final String PRODUCT_PROCESS_3 = "60"; //3:申报
    public static final String PRODUCT_PROCESS_4 = "80"; //4:完善资料
    public static final String PRODUCT_PROCESS_5 = "100"; //5:取得批件
    public static final String PRODUCT_PROCESS_6 = "100"; //6:不予批准
    public static final String PRODUCT_PROCESS_7 = "100"; //7:终止申报


    /**
     * 原料类型（
     * 0：单一原料，
     * 1：复配原料）
     */
    public static final String MATERIAL_TYPE_0 = "0"; //0:单一原料
    public static final String MATERIAL_TYPE_1 = "1"; //1:复配原料

    /**
     * 企业类型
     * 1：申请企业、
     * 2：实际生产企、
     * 3：在华责任单位
     */
    public static final String APPLY_ENTERPRISE_TYPE = "1"; //1:申请企业
    public static final String ACTUAL_ENTERPRISE_TYPE = "2"; //2:实际生产企业
    public static final String CHINA_ENTERPRISE_TYPE = "3"; //3:在华责任单位


}
