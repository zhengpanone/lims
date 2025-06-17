package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserUserGroup;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserUserGroup
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户用户组表数据访问层
 */
public interface SysUserUserGroupMapper extends BaseMapper<SysUserUserGroup> {
    /**
     * 根据用户ID查询用户组列表
     * @param userId
     * @return
     */
    List<SysUserUserGroup> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据用户组ID查询用户列表
     * @param userGroupId
     * @return
     */
    List<SysUserUserGroup> selectByUserGroupId(@Param("userGroupId") Long userGroupId);
} 