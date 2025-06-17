package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserOrg;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserOrg
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组织表数据访问层
 */
public interface SysUserOrgMapper extends BaseMapper<SysUserOrg> {
    /**
     * 根据用户ID查询组织列表
     * @param userId
     * @return
     */
    List<SysUserOrg> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据组织ID查询用户列表
     * @param orgId
     * @return
     */
    List<SysUserOrg> selectByOrgId(@Param("orgId") Long orgId);
} 