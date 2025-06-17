package com.zp.lims.common.core.response;

import com.baomidou.mybatisplus.core.metadata.IPage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * Page
 *
 * @author zhengpanone
 * @since 2022-11-27
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class PageResult <T> implements Serializable {
    Long total;
    List<T> list;

    public static <T>PageResult<T> pageResult(List<T> list, Long total){
        return new PageResult<>(total,list);
    }

    public static <T>PageResult<T> pageResult(IPage<T> pageInfo){
        return new PageResult<>(pageInfo.getTotal(),pageInfo.getRecords());
    }


}
