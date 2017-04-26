package com.thinkgem.jeesite.modules.mms.utils;

import com.thinkgem.jeesite.common.utils.SpringContextHolder;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.mms.constant.MmsConstant;
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
     * 使用目的，数字表示和信息描述相互转换
     * @param typeNum 数字表示
     * @param typeMessage 信息描述
     * @return
     */
    public static String purposeOfUseTypeNum2Message(String typeNum,String typeMessage){
        if(StringUtils.isNoneBlank(typeNum)){ //有数字
            switch (typeNum){
                case MmsConstant.PURPOSE_OF_USE_2:
                    typeMessage = MmsConstant.PURPOSE_OF_USE_MESSAGE_2;
                    break;
                case MmsConstant.PURPOSE_OF_USE_3:
                    typeMessage = MmsConstant.PURPOSE_OF_USE_MESSAGE_3;
                    break;
                case MmsConstant.PURPOSE_OF_USE_4:
                    typeMessage = MmsConstant.PURPOSE_OF_USE_MESSAGE_4;
                    break;
                default:
                    typeMessage = MmsConstant.PURPOSE_OF_USE_MESSAGE_1;
                    break;

            }
            return typeMessage;

        }else if(StringUtils.isNoneBlank(typeMessage)){
            switch (typeMessage){
                case MmsConstant.PURPOSE_OF_USE_MESSAGE_2:
                    typeNum = MmsConstant.PURPOSE_OF_USE_2;
                    break;
                case MmsConstant.PURPOSE_OF_USE_MESSAGE_3:
                    typeNum = MmsConstant.PURPOSE_OF_USE_3;
                    break;
                case MmsConstant.PURPOSE_OF_USE_MESSAGE_4:
                    typeNum = MmsConstant.PURPOSE_OF_USE_4;
                    break;
                default:
                    typeNum = MmsConstant.PURPOSE_OF_USE_1;
                    break;
            }
            return typeNum;
        }
        return typeMessage;
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
