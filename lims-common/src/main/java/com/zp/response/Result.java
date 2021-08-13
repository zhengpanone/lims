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
@NoArgsConstructor
@AllArgsConstructor
public class Result {
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
    private Object data;

    public Result(ResultCode code) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
    }

    public Result(ResultCode code, Object data) {
        this.success = code.success();
        this.code = code.code();
        this.message = code.message();
        this.data = data;
    }
}
