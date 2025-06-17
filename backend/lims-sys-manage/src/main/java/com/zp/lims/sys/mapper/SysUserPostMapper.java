package com.zp.lims.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zp.lims.sys.entity.SysUserPost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * SysUserPost
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户岗位表数据访问层
 */
public interface SysUserPostMapper extends BaseMapper<SysUserPost> {
    /**
     * 根据用户ID查询岗位列表
     * @param userId
     * @return
     */
    List<SysUserPost> selectByUserId(@Param("userId") Long userId);
    
    /**
     * 根据岗位ID查询用户列表
     * @param postId
     * @return
     */
    List<SysUserPost> selectByPostId(@Param("postId") Long postId);
} 