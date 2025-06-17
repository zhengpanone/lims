package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysOrgRole;
import com.zp.lims.sys.mapper.SysOrgRoleMapper;
import com.zp.lims.sys.service.ISysOrgRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysOrgRoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 组织角色服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysOrgRoleServiceImpl extends ServiceImpl<SysOrgRoleMapper, SysOrgRole> implements ISysOrgRoleService {

    @Override
    public List<SysOrgRole> getByOrgId(Long orgId) {
        return baseMapper.selectByOrgId(orgId);
    }

    @Override
    public List<SysOrgRole> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignRolesToOrg(Long orgId, List<Long> roleIds) {
        // 先删除组织原有角色
        LambdaQueryWrapper<SysOrgRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysOrgRole::getOrgId, orgId);
        this.remove(wrapper);
        
        // 分配新角色
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                SysOrgRole orgRole = new SysOrgRole();
                orgRole.setOrgId(orgId);
                orgRole.setRoleId(roleId);
                this.save(orgRole);
            }
        }
        return true;
    }
} 