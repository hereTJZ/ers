package com.example.ers.utils;

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
}
