package com.zp.lims.sys.service.impl;

import com.zp.lims.sys.entity.CustomUserDetails;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 14:39
 * Version : v1.0.0
 * Description: 自定义UserDetailsService
 */
@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements UserDetailsService {

    private final ISysUserService sysUserService;
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser user = sysUserService.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户不存在: " + username);
        }
        return CustomUserDetails.create(user);
    }
}
