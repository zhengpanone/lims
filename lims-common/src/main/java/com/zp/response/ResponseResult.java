package com.zp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:37.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Data
@NoArgsConstructor
public class ResponseResult implements Response {
    /**
     * 操作是否成功
     */
    boolean success = SUCCESS;
    /**
     * 操作代码
     */
    int code = SUCCESS_CODE;
    /**
     * 提示信息
     */
    String message;

    QueryResult queryResult;


    public ResponseResult(ResultCode resultCode) {
        this.success = resultCode.success();
        this.code = resultCode.code();
        this.message = resultCode.message();
    }
    public ResponseResult(Result result){
        this.message = result.getMessage();
        this.code = result.getCode();
        this.success = result.isSuccess();
    }

    public ResponseResult(Result result ,QueryResult queryResult){

    }

    public static ResponseResult SUCCESS() {
        return new ResponseResult(CommonCode.SUCCESS);
    }

    public static ResponseResult FAIL() {
        return new ResponseResult(CommonCode.FAIL);
    }
}
