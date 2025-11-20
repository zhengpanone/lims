package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserUserGroup;
import com.zp.lims.sys.mapper.SysUserUserGroupMapper;
import com.zp.lims.sys.service.ISysUserUserGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserUserGroupServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户用户组服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserUserGroupServiceImpl extends ServiceImpl<SysUserUserGroupMapper, SysUserUserGroup> implements ISysUserUserGroupService {

    @Override
    public List<SysUserUserGroup> getByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SysUserUserGroup> getByUserGroupId(Long userGroupId) {
        return baseMapper.selectByUserGroupId(userGroupId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignUserGroupsToUser(Long userId, List<Long> userGroupIds) {
        // 先删除用户原有用户组
        LambdaQueryWrapper<SysUserUserGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserUserGroup::getUserId, userId);
        this.remove(wrapper);
        
        // 分配新用户组
        if (userGroupIds != null && !userGroupIds.isEmpty()) {
            for (Long userGroupId : userGroupIds) {
                SysUserUserGroup userUserGroup = new SysUserUserGroup();
                userUserGroup.setUserId(userId);
                userUserGroup.setUserGroupId(userGroupId);
                this.save(userUserGroup);
            }
        }
        return true;
    }
} 