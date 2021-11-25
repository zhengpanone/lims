package com.zp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhengpanone
 * @Description: 统一API响应结果封装
 * @Date:Created in 2021/08/06 21:13.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */

@Data
@AllArgsConstructor
public class Result<T> {
    /**
     * 是否成功
     */
    private boolean success;
    /**
     * 返回码
     */
    private int code;
    /**
     * 返回信息
     */
    private String message;
    /**
     * 返回数据
     */
    private T data;
    /**
     * 时间戳
     */
    private long timestamp;

    public Result(){
        this.timestamp = System.currentTimeMillis();
    }

    public Result(ResultCode code) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
        this.timestamp = System.currentTimeMillis();
    }

    public Result(ResultCode code, T data) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
        this.data = data;
        this.timestamp = System.currentTimeMillis();
    }

    public Result(Integer code,String message,boolean success){
        this.code=code;
        this.message = message;
        this.success = success;
        this.timestamp = System.currentTimeMillis();
    }

    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }
}
