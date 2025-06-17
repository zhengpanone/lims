package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysOrg;
import org.apache.ibatis.annotations.Param;

/**
 * SysOrg
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织表数据访问层
 */
public interface SysOrgMapper extends BaseMapper<SysOrg> {
    /**
     * 根据组织代码查询组织
     * @param orgCode
     * @return
     */
    SysOrg selectByOrgCode(@Param("orgCode") String orgCode);
} 