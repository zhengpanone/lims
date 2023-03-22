package com.zp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.sys.entity.SysUser;
import com.zp.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Mapper;

/**
 * RoleMapper
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Mapper
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {
}
