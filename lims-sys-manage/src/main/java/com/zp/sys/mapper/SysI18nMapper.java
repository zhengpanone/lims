package com.zp.sys.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zp.sys.entity.SysI18nEntity;

@Mapper
public interface SysI18nMapper extends BaseMapper<SysI18nEntity> {
    IPage<SysI18nEntity> queryI18nList(@Param("page") Page<SysI18nEntity> page,
            @Param("i18n") SysI18nEntity sysI18nEntity);
}
