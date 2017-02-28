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


}
