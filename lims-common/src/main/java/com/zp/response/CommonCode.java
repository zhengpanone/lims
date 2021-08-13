package com.zp.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:31.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */

@ToString
@NoArgsConstructor
@AllArgsConstructor
public enum CommonCode implements ResultCode {
    /**
     * 成功
     */
    SUCCESS(true, 200, "操作成功"),
    /**
     * 失败
     */
    FAIL(false, 400, "操作失败");
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

    @Override
    public boolean success() {
        return false;
    }

    @Override
    public int code() {
        return 0;
    }

    @Override
    public String message() {
        return null;
    }
}
