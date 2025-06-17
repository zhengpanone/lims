package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserGroupRole;
import com.zp.lims.sys.mapper.SysUserGroupRoleMapper;
import com.zp.lims.sys.service.ISysUserGroupRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserGroupRoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组角色服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserGroupRoleServiceImpl extends ServiceImpl<SysUserGroupRoleMapper, SysUserGroupRole> implements ISysUserGroupRoleService {

    @Override
    public List<SysUserGroupRole> getByUserGroupId(Long userGroupId) {
        return baseMapper.selectByUserGroupId(userGroupId);
    }

    @Override
    public List<SysUserGroupRole> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignRolesToUserGroup(Long userGroupId, List<Long> roleIds) {
        // 先删除用户组原有角色
        LambdaQueryWrapper<SysUserGroupRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserGroupRole::getUserGroupId, userGroupId);
        this.remove(wrapper);
        
        // 分配新角色
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                SysUserGroupRole userGroupRole = new SysUserGroupRole();
                userGroupRole.setUserGroupId(userGroupId);
                userGroupRole.setRoleId(roleId);
                this.save(userGroupRole);
            }
        }
        return true;
    }
} 