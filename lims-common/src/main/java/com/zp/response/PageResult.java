package com.zp.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:44.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult<T> {
    /**
     * 数据列表
     */
    private List<T> list;
    /**
     * 数据总数
     */
    private long total;
}
