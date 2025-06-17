package com.zp.lims.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zp.lims.sys.entity.SysRole;
import com.zp.lims.sys.mapper.SysRoleMapper;
import com.zp.lims.sys.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * RoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    private SysRoleMapper roleMapper;
}
