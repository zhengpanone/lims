package com.zp.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

/**
 * SysUser
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户表数据访问层
 */
//@Mapper
public interface SysUserMapper extends BaseMapper<SysUser> {
    /**
     * 判断是否存在用户
     * @param userName
     * @return
     */
    Integer existUserName(@Param("userName") String userName);
}
