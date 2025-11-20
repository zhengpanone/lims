package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysRoleMutex;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysRoleMutex
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 角色互斥表数据访问层
 */
public interface SysRoleMutexMapper extends BaseMapper<SysRoleMutex> {
    /**
     * 根据角色ID查询互斥角色列表
     * @param roleId
     * @return
     */
    List<SysRoleMutex> selectByRoleId(@Param("roleId") Long roleId);
} 