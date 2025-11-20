package com.zp.lims.sys.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.zp.lims.sys.controller.dto.RoleDTO;
import com.zp.lims.sys.entity.SysRole;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.entity.SysUserRole;
import com.zp.lims.sys.mapper.SysRoleMapper;
import com.zp.lims.sys.service.ISysRoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * RoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public Boolean saveRole(RoleDTO roleDTO) {
        SysRole sysRole = new SysRole();
        BeanUtils.copyProperties(roleDTO, sysRole);
        baseMapper.insert(sysRole);
        return Boolean.TRUE;
    }

    @Override
    public boolean updateStatusById(String id, Integer status) {
        return baseMapper.updateStatusById(id, status) > 0;
    }
}
