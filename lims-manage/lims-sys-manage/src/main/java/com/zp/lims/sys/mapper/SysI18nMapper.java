package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.lims.sys.entity.SysI18nEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 国际化信息Mapper接口
 *
 * @author zhengp
 */
@Mapper
public interface SysI18nMapper extends BaseMapper<SysI18nEntity> {

    /**
     * 分页查询国际化信息
     *
     * @param page 分页参数
     * @param i18n 查询条件
     * @return 分页结果
     */
    IPage<SysI18nEntity> queryPage(Page<SysI18nEntity> page, @Param("i18n") SysI18nEntity i18n);
}
