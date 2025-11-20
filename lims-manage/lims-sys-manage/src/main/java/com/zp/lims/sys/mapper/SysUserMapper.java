package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * SysUser
 *
 * @author zhengpanone
 * @description: 用户Mapper接口
 * @since 2022-11-28
 */
//@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 检查用户名是否存在
     *
     * @param userName
     * @return
     */
    int existsByUsername(@Param("userName") String userName);

    /**
     * 根据用户名查询用户
     */
    @Select("SELECT * FROM sys_user WHERE username = #{username} AND deleted = 0")
    SysUser findByUsername(@Param("username") String username);

    /**
     * 根据用户名更新用户状态
     */
    @Update("UPDATE sys_user SET status = #{status}, update_time = NOW() WHERE id = #{id} AND deleted = 0")
    int updateStatusById(@Param("id") String id, @Param("status") Integer status);
}
