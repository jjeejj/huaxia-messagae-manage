package com.thinkgem.jeesite.modules.mms.utils;

import java.math.BigDecimal;

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
}
