package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysPermission;
import com.zp.lims.sys.mapper.SysPermissionMapper;
import com.zp.lims.sys.service.ISysPermissionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysPermissionServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 权限服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysPermissionServiceImpl extends ServiceImpl<SysPermissionMapper, SysPermission> implements ISysPermissionService {

    @Override
    public SysPermission getByCode(String code) {
        LambdaQueryWrapper<SysPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPermission::getCode, code);
        return this.getOne(wrapper);
    }

    @Override
    public List<SysPermission> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public List<SysPermission> getByUserId(Long userId) {
        // TODO: 根据用户ID查询权限，需要关联查询用户角色、用户组角色等
        return null;
    }

    @Override
    public List<SysPermission> getPermissionTree() {
        // 获取所有权限
        List<SysPermission> allPermissions = this.list();
        // TODO: 构建树形结构
        return allPermissions;
    }
} 