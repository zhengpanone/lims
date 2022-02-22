package com.zp.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel
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
    @ApiModelProperty(value = "返回码",example = "200")
    private Integer code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回码描述", example = "OK")
    private String message;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回结果")
    private T data;
    /**
     * 时间戳
     */
    @ApiModelProperty(value = "响应时间戳", example = "2020-08-12 14:37:11")
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

    public static Result ok(){
        return new Result(ResultCode.SUCCESS);
    }

    public static Result fail(){
        return new Result(ResultCode.FAIL);
    }

    public Result<T> message(String message){
        this.setMessage(message);
        return this;
    }
    public Result<T> data(T data){
        this.setData(data);
        return this;
    }
}
