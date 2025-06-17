package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysRoleMutex;
import com.zp.lims.sys.mapper.SysRoleMutexMapper;
import com.zp.lims.sys.service.ISysRoleMutexService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * SysRoleMutexServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 角色互斥服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysRoleMutexServiceImpl extends ServiceImpl<SysRoleMutexMapper, SysRoleMutex> implements ISysRoleMutexService {

    @Override
    public List<SysRoleMutex> getByRoleId(Long roleId) {
        return baseMapper.selectByRoleId(roleId);
    }

    @Override
    public Boolean isMutex(Long roleId1, Long roleId2) {
        LambdaQueryWrapper<SysRoleMutex> wrapper = new LambdaQueryWrapper<>();
        wrapper.and(w -> w.and(ww -> ww.eq(SysRoleMutex::getRoleId, roleId1).eq(SysRoleMutex::getMutexRoleId, roleId2))
                .or(ww -> ww.eq(SysRoleMutex::getRoleId, roleId2).eq(SysRoleMutex::getMutexRoleId, roleId1)));
        return this.count(wrapper) > 0;
    }
} 