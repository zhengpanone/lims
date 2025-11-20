package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserPost;
import com.zp.lims.sys.mapper.SysUserPostMapper;
import com.zp.lims.sys.service.ISysUserPostService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserPostServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户岗位服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserPostServiceImpl extends ServiceImpl<SysUserPostMapper, SysUserPost> implements ISysUserPostService {

    @Override
    public List<SysUserPost> getByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SysUserPost> getByPostId(Long postId) {
        return baseMapper.selectByPostId(postId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignPostsToUser(Long userId, List<Long> postIds) {
        // 先删除用户原有岗位
        LambdaQueryWrapper<SysUserPost> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserPost::getUserId, userId);
        this.remove(wrapper);
        
        // 分配新岗位
        if (postIds != null && !postIds.isEmpty()) {
            for (Long postId : postIds) {
                SysUserPost userPost = new SysUserPost();
                userPost.setUserId(userId);
                userPost.setPostId(postId);
                this.save(userPost);
            }
        }
        return true;
    }
} 