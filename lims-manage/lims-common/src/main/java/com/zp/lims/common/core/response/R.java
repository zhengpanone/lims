package com.zp.lims.common.core.response;

import com.zp.lims.common.core.response.enums.CommonResponseEnum;
import com.zp.lims.common.core.response.enums.IResult;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zhengpanone
 * @Description: 统一返回数据结构
 * @Date:Created in 2021/08/06 21:13.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@ApiModel
@Data
public class R<T> implements Serializable {

    /**
     * 返回码
     */
    @ApiModelProperty(value = "返回码", example = "200")
    private int code;

    /**
     * 返回信息
     */
    @ApiModelProperty(value = "返回码描述", example = "OK")
    private String msg;
    /**
     * 返回数据
     */
    @ApiModelProperty(value = "返回结果")
    private T data;

    /**
     * 无参构造函数
     */
    protected R() {

    }

    /**
     * code和message构造函数
     *
     * @param code
     * @param message
     */
    protected R(int code, String message) {
        this.code = code;
        this.msg = message;
    }

    /**
     * 全参构造函数
     *
     * @param code
     * @param message
     * @param data
     */
    protected R(int code, String message, T data) {
        this.code = code;
        this.msg = message;
        this.data = data;
    }


    public static <T> R<T> success() {
        return new R<>(CommonResponseEnum.SUCCESS.getCode(), CommonResponseEnum.SUCCESS.getMessage());
    }

    public static <T> R<T> success(String message) {
        return new R<T>(CommonResponseEnum.SUCCESS.getCode(), message);
    }

    public static <T> R<T> success(T data) {
        return new R<>(CommonResponseEnum.SUCCESS.getCode(), CommonResponseEnum.SUCCESS.getMessage(), data);
    }

    public static <T> R<T> success(String message, T data) {
        return new R<>(CommonResponseEnum.SUCCESS.getCode(), message, data);
    }

    public static <T> R<T> error() {
        return new R<>(CommonResponseEnum.COMMON_FAILED.getCode(), CommonResponseEnum.COMMON_FAILED.getMessage());
    }

    public static <T> R<T> error(String message) {
        return new R<>(CommonResponseEnum.FAILED.getCode(), message);
    }

    public static <T> R<T> error(CommonResponseEnum commonResponseEnum, String message) {
        return new R<>(commonResponseEnum.getCode(), message);
    }

    public static <T> R<T> error(int code, String message) {
        return new R<>(code, message, null);
    }

    public static <T> R<T> error(IResult errorResult) {
        return new R<>(errorResult.getCode(), errorResult.getMessage());
    }

    public static <T> R<T> instance(Integer code, String message, T data) {
        R<T> result = new R<>();
        result.setCode(code);
        result.setMsg(message);
        result.setData(data);
        return result;
    }

}
