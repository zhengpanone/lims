package com.zp.response;

/**
 * @Author: zhengpanone
 * @Description: 响应码枚举
 * @Date:Created in 2021/08/06 21:15.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
public interface ResultCode {
    /**
     * 操作是否成功
     * @return
     */
    boolean success();

    /**
     * 操作代码
     * @return
     */
    int code();

    /**
     * 提示信息
     * @return
     */
    String message();

}
