package com.ssycoding.iutils.demo;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * -
 * 手机号正则校验
 *
 * @author Songxinlei 2021年12月14日
 * -
 */
public class PhoneUtil {

    private static String REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0-9])|(19[0-9]))\\d{8}$";

    private static Integer PHONE_LENGTH = 11;

    private static Pattern P;

    private PhoneUtil() { }

    public static boolean checkPhone(String phone) {
        if (phone != null && phone.length() == PHONE_LENGTH) {
            Matcher m = P.matcher(phone);
            return m.matches();
        } else {
            return Boolean.FALSE;
        }
    }

    static {
        P = Pattern.compile(REGEX);
    }
}
