package com.thinkgem.jeesite.modules.mms.utils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.modules.mms.dao.InstitutionalInformationDao;
import com.thinkgem.jeesite.modules.mms.entity.InstitutionalInformation;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 *
 * Created by jjeejj on 2017/3/6.
 */
public class MmsUtils {

    private static InstitutionalInformationDao institutionalInformationDao = SpringContextHolder.getBean(InstitutionalInformationDao.class);

    /**
     * 保留浮点数有效位数
     *
     * @param f         处理的浮点数
     * @param effective 有效位数
     * @return 返回处理过的浮点数的四舍五入的
     */
    public static float handleFloat(float f, int effective) {

        BigDecimal b = new BigDecimal(f);
        float f1 = b.setScale(effective, BigDecimal.ROUND_HALF_UP).floatValue();
        System.out.println(f1);
        return f1;
    }


    private static final String STR_FORMAT = "0000";

    /**
     * 处理4为流水号加一
     * @param serialNumber
     * @return
     */
    public static String handleSerialNumber(String serialNumber){
        Integer intSerialNumber = Integer.parseInt(serialNumber);
        intSerialNumber++;
        DecimalFormat df = new DecimalFormat(STR_FORMAT);
        return df.format(intSerialNumber);
    }

    /**
     * 查到总产品，进行翻页
     */


    /**
     * 根据机构类型获取机构
     * @param type 机构类型
     * @return
     */
    public static List<InstitutionalInformation> getInstitutionalByType(String type){

        InstitutionalInformation institutionalInformation = new InstitutionalInformation();

        institutionalInformation.setInstitutionalType(type);

        List<InstitutionalInformation> institutionalInformationList = institutionalInformationDao.findList(institutionalInformation);

        return institutionalInformationList;
    }
}
