package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.config.JwtTokenProvider;
import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.dto.UserDTO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.entity.SysUserRole;
import com.zp.lims.sys.mapper.SysUserMapper;
import com.zp.lims.sys.mapper.SysUserRoleMapper;
import com.zp.lims.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;

/**
 * RoleServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-28
 * @description: 用户服务实现类
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {
    
    private final SysUserMapper sysUserMapper;
    private final SysUserRoleMapper sysUserRoleMapper;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

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
        sysUserMapper.insert(sysUser);
        
        // 分配角色
        if (userDTO.getRole() != null && !userDTO.getRole().isEmpty()) {
            userDTO.getRole().forEach(roleId -> {
                SysUserRole sysUserRole = new SysUserRole();
                sysUserRole.setUserId(sysUser.getId());
                sysUserRole.setRoleId(roleId);
                sysUserRoleMapper.insert(sysUserRole);
            });
        }
        return Boolean.TRUE;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public SysUser register(RegisterDTO registerDTO) {
        // 查询用户是否存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, registerDTO.getUserName());
        SysUser existUser = this.getOne(wrapper);
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }
        
        // 检查邮箱是否已存在
        if (StringUtils.hasText(registerDTO.getEmail())) {
            LambdaQueryWrapper<SysUser> emailWrapper = new LambdaQueryWrapper<>();
            emailWrapper.eq(SysUser::getEmail, registerDTO.getEmail());
            SysUser existEmailUser = this.getOne(emailWrapper);
            if (existEmailUser != null) {
                throw new RuntimeException("邮箱已被使用");
            }
        }
        
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerDTO, sysUser);
        
        // 密码加密
        sysUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        
        // 设置默认状态为激活
        sysUser.setStatus(1);
        
        // 设置创建时间
        sysUser.setCreateTime(new Date());
        sysUser.setUpdateTime(new Date());
        
        sysUserMapper.insert(sysUser);
        return sysUser;
    }

    @Override
    public LoginResponseVO login(LoginDTO loginDTO) {
        // 根据用户名查询用户
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUserName, loginDTO.getAccount());
        SysUser user = this.getOne(wrapper);
        
        if (user == null) {
            throw new RuntimeException("用户不存在");
        }
        
        // 验证密码
        if (!passwordEncoder.matches(loginDTO.getPwd(), user.getPassword())) {
            throw new RuntimeException("密码错误");
        }
        
        // 检查用户状态
        if (user.getStatus() != null && user.getStatus() != 1) {
            throw new RuntimeException("用户已被禁用");
        }
        
        // 创建UserDetails
        UserDetails userDetails = User.builder()
                .username(user.getUserName())
                .password(user.getPassword())
                .authorities(new ArrayList<>())
                .build();
        
        // 生成JWT Token
        String accessToken = jwtTokenProvider.generateAccessToken(userDetails);
        String refreshToken = jwtTokenProvider.generateRefreshToken(userDetails);
        
        // 构建响应
        LoginResponseVO response = new LoginResponseVO();
        response.setAccessToken(accessToken);
        response.setRefreshToken(refreshToken);
        response.setExpiresIn(86400L); // 24小时
        response.setUserId(user.getId());
        response.setUsername(user.getUserName());
        response.setNickName(user.getUserName()); // 可以根据需要设置昵称
        response.setEmail(user.getEmail());
        
        return response;
    }
}
