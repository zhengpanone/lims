package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * RoleMapper
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {

    /**
     * 更新角色状态
     */
    @Update("UPDATE sys_role SET status = #{status}, update_time = NOW() WHERE id = #{id} AND deleted = 0")
    int updateStatusById(@Param("id") String id, @Param("status") Integer status);
}
