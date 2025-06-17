package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysPositionRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysPositionRole
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位角色表数据访问层
 */
public interface SysPositionRoleMapper extends BaseMapper<SysPositionRole> {
    /**
     * 根据职位ID查询角色列表
     * @param positionId
     * @return
     */
    List<SysPositionRole> selectByPositionId(@Param("positionId") Long positionId);
    
    /**
     * 根据角色ID查询职位列表
     * @param roleId
     * @return
     */
    List<SysPositionRole> selectByRoleId(@Param("roleId") Long roleId);
} 