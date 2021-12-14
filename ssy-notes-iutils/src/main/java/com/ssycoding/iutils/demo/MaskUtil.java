package com.ssycoding.iutils.demo;

import org.apache.commons.lang3.StringUtils;

/**
 * -
 * 数字 字符掩码工具类
 *
 * @author Songxinlei 2021年12月14日
 * -
 */
public class MaskUtil {

    private static final String MASK_STYLE_ASTERISK = "*";

    private MaskUtil() { }

    /**
     * 数字脱敏
     *
     * @param enCode 需要掩码的字串
     * @param before 前部分明文长度
     * @param after 后部分明文长度
     * @return
     */
    public static String hide(String enCode, int before, int after) {
        String beforeNum = enCode.substring(0, before);
        String afterNum = enCode.substring(enCode.length() - after, enCode.length());
        return beforeNum + "" + StringUtils.leftPad(afterNum, enCode.length() - before, MASK_STYLE_ASTERISK);
    }

    /**
     * 手机号默认掩码 前3后4
     *
     * @param phone
     * @return
     */
    public static String defaultHidePhone(String phone) {
        return hide(phone, 3, 4);
    }

    /**
     * 身份证号默认掩码 前6后4
     *
     * @param identification
     * @return
     */
    public static String defaultHideIdentification(String identification) {
        return hide(identification, 6, 4);
    }
}
