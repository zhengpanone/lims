package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.sys.entity.SysLookupClassifyEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 数据字典分类Mapper接口
 *
 * @author zhengp
 */
@Mapper
public interface SysLookupClassifyMapper extends BaseMapper<SysLookupClassifyEntity> {

    /**
     * 分页查询数据字典分类
     *
     * @param page 分页参数
     * @param classify 查询条件
     * @return 分页结果
     */
    IPage<SysLookupClassifyEntity> queryPage(Page<SysLookupClassifyEntity> page, @Param("classify") SysLookupClassifyEntity classify);
}
