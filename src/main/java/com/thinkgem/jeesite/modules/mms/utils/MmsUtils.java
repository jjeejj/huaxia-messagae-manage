package com.thinkgem.jeesite.modules.mms.utils;

import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 *
 * Created by jjeejj on 2017/3/6.
 */
public class MmsUtils {
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
}
