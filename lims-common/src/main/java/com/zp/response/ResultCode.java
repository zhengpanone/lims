package com.zp.response;

/**
 * @Author: zhengpanone
 * @Description: 响应码枚举
 * @Date:Created in 2021/08/06 21:15.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public enum  ResultCode {
    /**
     * 操作成功
     */
    SUCCESS(true,10000,"操作成功"),
    FAIL(false,40000,"");
    ResultCode(boolean success,int code,String message){
        this.success=success;
        this.code = code;
        this.message = message;
        this.timestamp = System.currentTimeMillis();
    }

    /**
     * 操作是否成功
     */
    boolean success;

    /**
     * 操作代码
     */
    int code;

    /**
     * 提示信息
     */
    String message;
    /**
     * 时间戳
     */
    long timestamp;



    public boolean success(){
        return success;
    }
    public int code(){
        return code;
    }
    public String message(){
        return message;
    }

}
