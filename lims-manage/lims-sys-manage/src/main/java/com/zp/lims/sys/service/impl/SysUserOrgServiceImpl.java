package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserOrg;
import com.zp.lims.sys.mapper.SysUserOrgMapper;
import com.zp.lims.sys.service.ISysUserOrgService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysUserOrgServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组织服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserOrgServiceImpl extends ServiceImpl<SysUserOrgMapper, SysUserOrg> implements ISysUserOrgService {

    @Override
    public List<SysUserOrg> getByUserId(Long userId) {
        return baseMapper.selectByUserId(userId);
    }

    @Override
    public List<SysUserOrg> getByOrgId(Long orgId) {
        return baseMapper.selectByOrgId(orgId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignOrgsToUser(Long userId, List<Long> orgIds) {
        // 先删除用户原有组织
        LambdaQueryWrapper<SysUserOrg> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserOrg::getUserId, userId);
        this.remove(wrapper);
        
        // 分配新组织
        if (orgIds != null && !orgIds.isEmpty()) {
            for (Long orgId : orgIds) {
                SysUserOrg userOrg = new SysUserOrg();
                userOrg.setUserId(userId);
                userOrg.setOrgId(orgId);
                this.save(userOrg);
            }
        }
        return true;
    }
} 