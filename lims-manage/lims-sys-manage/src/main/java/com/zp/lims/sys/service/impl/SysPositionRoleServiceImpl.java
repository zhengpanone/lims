package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysPositionRole;
import com.zp.lims.sys.mapper.SysPositionRoleMapper;
import com.zp.lims.sys.service.ISysPositionRoleService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * SysPositionRoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 职位角色服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysPositionRoleServiceImpl extends ServiceImpl<SysPositionRoleMapper, SysPositionRole> implements ISysPositionRoleService {

    @Override
    public List<SysPositionRole> getByPositionId(Long positionId) {
        return baseMapper.selectByPositionId(positionId);
    }

    @Override
    public List<SysPositionRole> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean assignRolesToPosition(Long positionId, List<Long> roleIds) {
        // 先删除职位原有角色
        LambdaQueryWrapper<SysPositionRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysPositionRole::getPositionId, positionId);
        this.remove(wrapper);
        
        // 分配新角色
        if (roleIds != null && !roleIds.isEmpty()) {
            for (Long roleId : roleIds) {
                SysPositionRole positionRole = new SysPositionRole();
                positionRole.setPositionId(positionId);
                positionRole.setRoleId(roleId);
                this.save(positionRole);
            }
        }
        return true;
    }
} 