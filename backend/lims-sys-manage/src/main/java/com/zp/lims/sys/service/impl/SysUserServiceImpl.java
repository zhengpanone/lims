package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.entity.SysUserRole;
import com.zp.lims.sys.mapper.SysUserMapper;
import com.zp.lims.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

/**
 * RoleServiceImpl
 *
 * @author zhengpanone
 * @description: 用户服务实现类
 * @since 2022-11-28
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    private final PasswordEncoder passwordEncoder;

    private final ISysUserRoleService sysUserRoleService;

    @Override
    public SysUser findByUsername(String username) {
        return baseMapper.findByUsername(username);
    }

    @Override
    public boolean existsByUsername(String username) {
        return baseMapper.existsByUsername(username) > 0;
    }

    @Override
    public boolean updateStatusByUsername(String username, Integer status) {
        return baseMapper.updateStatusByUsername(username, status) > 0;
    }

    /**
     * 保存用户
     *
     * @param userDTO
     * @return
     */

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean saveUser(UserDTO userDTO) {
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(userDTO, sysUser);
        // 密码加密
        if (StringUtils.hasText(sysUser.getPassword())) {
            sysUser.setPassword(passwordEncoder.encode(sysUser.getPassword()));
        }
        baseMapper.insert(sysUser);

        // 分配角色
        if (!CollectionUtils.isEmpty(userDTO.getRoleIds())) {
            userDTO.getRoleIds().forEach(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(roleId);
                sysUserRoleService.save(sysUserRole);
            });
        }
        return Boolean.TRUE;
    }


}
