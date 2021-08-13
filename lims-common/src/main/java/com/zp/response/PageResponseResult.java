package com.zp.response;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:46.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Data
@NoArgsConstructor
public class PageResponseResult extends ResponseResult {
    PageResult<?> pageResult;

    public PageResponseResult(ResultCode resultCode, PageResult<?> pageResult) {
        super(resultCode);
        this.pageResult = pageResult;
    }

}
