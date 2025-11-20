package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.entity.SysUserGroup;
import com.zp.lims.sys.mapper.SysUserGroupMapper;
import com.zp.lims.sys.service.ISysUserGroupService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * SysUserGroupServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户组服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserGroupServiceImpl extends ServiceImpl<SysUserGroupMapper, SysUserGroup> implements ISysUserGroupService {

    @Override
    public SysUserGroup getByName(String name) {
        LambdaQueryWrapper<SysUserGroup> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserGroup::getName, name);
        return this.getOne(wrapper);
    }
} 