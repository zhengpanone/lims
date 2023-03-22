package com.zp.response;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Page
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Data
public class PageResult <T> implements Serializable {
    Integer total;
    List<T> list;
}
