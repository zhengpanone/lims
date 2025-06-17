package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.sys.entity.SysLookupItemEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据字典项Mapper接口
 *
 * @author zhengp
 */
@Mapper
public interface SysLookupItemMapper extends BaseMapper<SysLookupItemEntity> {

    /**
     * 分页查询数据字典项
     *
     * @param page 分页参数
     * @param lookupItem 查询条件
     * @return 分页结果
     */
    IPage<SysLookupItemEntity> queryPage(Page<SysLookupItemEntity> page, @Param("lookupItem") SysLookupItemEntity lookupItem);
}