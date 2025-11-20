package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.SysUserPost;

import java.util.List;

/**
 * ISysUserPostService
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户岗位服务接口
 */
public interface ISysUserPostService extends IService<SysUserPost> {
    /**
     * 根据用户ID查询岗位列表
     * @param userId
     * @return
     */
    List<SysUserPost> getByUserId(Long userId);
    
    /**
     * 根据岗位ID查询用户列表
     * @param postId
     * @return
     */
    List<SysUserPost> getByPostId(Long postId);
    
    /**
     * 为用户分配岗位
     * @param userId
     * @param postIds
     * @return
     */
    Boolean assignPostsToUser(Long userId, List<Long> postIds);
} 