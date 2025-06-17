package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysPermission;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysPermission
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 权限表数据访问层
 */
public interface SysPermissionMapper extends BaseMapper<SysPermission> {
    /**
     * 根据权限代码查询权限
     * @param code
     * @return
     */
    SysPermission selectByCode(@Param("code") String code);
    
    /**
     * 根据角色ID查询权限列表
     * @param roleId
     * @return
     */
    List<SysPermission> selectByRoleId(@Param("roleId") Long roleId);
} 