package com.zp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.zp.sys.entity.SysI18nEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface SysI18nMapper extends BaseMapper<SysI18nEntity> {
    IPage<SysI18nEntity> queryI18nPage(@Param("page") IPage<SysI18nEntity> page,
            @Param("i18n") SysI18nEntity sysI18nEntity);
}
