package com.example.ers.utils;

public class ErsResult {
    /**
     * http状态码
     * 100~199  信息提示类（Info）  服务器收到请求，表示请求正在执行
     * 200~299	请求成功（Success）  操作被成功接收并处理
     * 300~399	重定向（Redirection）    需要进一步的操作以完成请求
     * 400~499	客户端错误（Client Error） 请求包含语法错误或无法完成请求
     * 500~599	服务器错误（Server Error） 服务器在处理请求的过程中发生了错误
     * */
    private int code;
    // 响应消息
    private String msg;
    // 响应数据
    private Object data;

    /**
     * 构造函数
     * */
    public ErsResult() {

    }
    public ErsResult(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

     /**
     * getter and setter
     * */
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
