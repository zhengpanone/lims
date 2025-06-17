package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysPosition;
import org.apache.ibatis.annotations.Param;

/**
 * SysPosition
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位表数据访问层
 */
public interface SysPositionMapper extends BaseMapper<SysPosition> {
    /**
     * 根据职位代码查询职位
     * @param code
     * @return
     */
    SysPosition selectByCode(@Param("code") String code);
} 