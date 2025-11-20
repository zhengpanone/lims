package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysOrgRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysOrgRole
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织角色表数据访问层
 */
public interface SysOrgRoleMapper extends BaseMapper<SysOrgRole> {
    /**
     * 根据组织ID查询角色列表
     * @param orgId
     * @return
     */
    List<SysOrgRole> selectByOrgId(@Param("orgId") Long orgId);
    
    /**
     * 根据角色ID查询组织列表
     * @param roleId
     * @return
     */
    List<SysOrgRole> selectByRoleId(@Param("roleId") Long roleId);
} 