package com.zp.response;

import lombok.Data;

import java.util.List;

/**
 * QueryResult
 *
 * @author zhengpanone
 * @since 2021-09-28
 */
@Data
public class QueryResult<T> {
    /**
     * 数据列表
     */
    private List<T> list;
    /**
     * 数据总数
     */
    private long total;

}
