package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserGroup;
import org.apache.ibatis.annotations.Param;

/**
 * SysUserGroup
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组表数据访问层
 */
public interface SysUserGroupMapper extends BaseMapper<SysUserGroup> {
    /**
     * 根据用户组名称查询用户组
     * @param name
     * @return
     */
    SysUserGroup selectByName(@Param("name") String name);
} 