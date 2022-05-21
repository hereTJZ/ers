package com.example.ers.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {
    /**
     * 用户身份识别
     */
    public static String userIdentity(int num) {
        switch (num) {
            case 1:
                return "管理员";
            case 2:
                return "老师";
            case 3:
                return "学生";
            case 4:
                return "社会人员";
            default:
                return "身份码错误！";
        }
    }

    /**
     * 实验状态识别
     */
    public static String experimentState(int num) {
        switch (num) {
            case 0:
                return "已取消";
            case 1:
                return "已预约";
            case 2:
                return "进行中";
            case 3:
                return "已完成";
            case 4:
                return "超时";
            default:
                return "状态码错误！";
        }
    }


    // 常用的正则表达
    /**
     * 验证手机号（简单）
     */
    private static final String REGEX_MOBILE_SIMPLE = "^[1]\\d{10}$";
    //复杂规则
    private static final String REGEX_MOBILE_COMPLICATED = "^1[3|5|8|7]\\d{9}$";//^代表開始，1是必须1开头，[3|5|8|7] 必须第二位是3或者5,8,7之间的一个，\d{9}任意九个个数

    /**
     * 验证手机号（精确）
     * 移动：134(0-8)、135、136、137、138、139、147、150、151、152、157、158、159、178、182、183、184、187、188
     * 联通：130、131、132、145、155、156、175、176、185、186
     * 电信：133、153、173、177、180、181、189
     * 全球星：1349
     * 虚拟运营商：170
     */
    private static final String REGEX_MOBILE_EXACT = "^((13[0-9])|(14[5,7])|(15[0-3,5-8])|(17[0,3,5-8])|(18[0-9])|(147))\\d{8}$";

    /**
     * 验证座机号,正确格式：xxxxx-xxxxxxxxxxxxx/
     */
    private static final String REGEX_TEL = "^0\\d{2,3}[- ]?\\d{7,8}";

    /**
     * 验证邮箱
     */
    private static final String REGEX_EMAIL = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$";

    /**
     * 验证url
     */
    private static final String REGEX_URL = "http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w-.%&=]*)?";

    /**
     * 验证数字
     */
    private static final String REGEX_NUMBER = "\\d+";

    /**
     * 验证汉字
     */
    private static final String REGEX_CHZ = "^[\\u4e00-\\u9fa5]+$";

    /**
     * 验证真实姓名，中文名或英文名
     */
    private static final String REGEX_REAL_NAME = "^[a-zA-Z\\s]+$|^[\\u4e00-\\u9fa5]+$";

    /**
     * 验证用户名,取值范围为a-z,A-Z,0-9,_,汉字，不能以"_"结尾,用户名必须是6-20位
     */
    private static final String REGEX_USERNAME = "^[\\w\\u4e00-\\u9fa5]{6,20}(?<!_)$";

    /**
     * 验证密码,取值范围为a-z,A-Z,0-9,_,-,@,#,$,%,^,&,*,必须是6-20位
     */
    private static final String REGEX_PASSWORD = "^[\\w!@#%&\\-\\$\\^\\*\\u4e00-\\u9fa5]{6,20}$";

    /**
     * 验证IP地址
     */
    private static final String REGEX_IP = "((2[0-4]\\d|25[0-5]|[01]?\\d\\d?)\\.){3}(2[0-4]\\d|25[0-5]|[01]?\\d\\d?)";

    /**
     * 是否是数字
     */
    public static boolean isNumber(String s) {
        Pattern pattern = Pattern.compile(REGEX_NUMBER);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 是否是手机号
     */
    public static boolean isMobilePhone(String s) {
        Pattern pattern = Pattern.compile(REGEX_MOBILE_SIMPLE);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 是否是邮箱
     */
    public static boolean isEmail(String s) {
        Pattern pattern = Pattern.compile(REGEX_EMAIL);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 是否是合法密码
     */
    public static boolean isVerifyPassword(String s) {
        Pattern pattern = Pattern.compile(REGEX_PASSWORD);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 是否是合法真实姓名
     */
    public static boolean isRealName(String s) {
        Pattern pattern = Pattern.compile(REGEX_REAL_NAME);
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }

    /**
     * 随机生成 n 位验证码
     */
    public static String createCode(int n) {
        return (int)((Math.random() * 9 + 1) * Math.pow(10, n - 1)) + "";
    }
}
