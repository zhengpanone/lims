package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.zp.lims.common.constant.SecurityConst;
import com.zp.lims.common.enums.StatusEnum;
import com.zp.lims.sys.config.JwtTokenProvider;
import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RefreshTokenDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.vo.AccessTokenVO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.RefreshToken;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.IAuthService;
import com.zp.lims.sys.service.IRefreshTokenService;
import com.zp.lims.sys.service.ISysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 15:03
 * Version : v1.0.0
 * Description:
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final IRefreshTokenService refreshTokenService;

    private final PasswordEncoder passwordEncoder;

    private final ISysUserService sysUserService;

    @Override
    public SysUser register(RegisterDTO registerDTO) {
        // 查询用户是否存在
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUser::getUsername, registerDTO.getUserName());
        SysUser existUser = sysUserService.getOne(wrapper);
        if (existUser != null) {
            throw new RuntimeException("用户名已存在");
        }

        // 检查邮箱是否已存在
        if (StringUtils.hasText(registerDTO.getEmail())) {
            LambdaQueryWrapper<SysUser> emailWrapper = new LambdaQueryWrapper<>();
            emailWrapper.eq(SysUser::getEmail, registerDTO.getEmail());
            SysUser existEmailUser = sysUserService.getOne(emailWrapper);
            if (existEmailUser != null) {
                throw new RuntimeException("邮箱已被使用");
            }
        }
        SysUser sysUser = new SysUser();
        BeanUtils.copyProperties(registerDTO, sysUser);
        // 密码加密
        sysUser.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        // 设置默认状态为激活
        sysUser.setStatus(StatusEnum.ACTIVE);

        sysUserService.save(sysUser);
        return sysUser;
    }


    @Override
    public LoginResponseVO login(LoginDTO loginDTO) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDTO.getUsername(), loginDTO.getPassword()));
        String username = authenticate.getName();
        String accessToken = jwtTokenProvider.generateAccessToken(username);
        RefreshToken refreshToken = refreshTokenService.createRefreshToken(username);
        LoginResponseVO loginResponseVO = new LoginResponseVO();
        loginResponseVO.setAccessToken(accessToken);
        loginResponseVO.setRefreshToken(refreshToken.getToken());
        loginResponseVO.setExpiresIn(jwtTokenProvider.getAccessTokenExpiration() / 1000);
        loginResponseVO.setUsername(username);
        return loginResponseVO;
    }

    @Override
    public AccessTokenVO refreshToken(RefreshTokenDTO refreshTokenDTO) {
        String tokenDTO = refreshTokenDTO.getRefreshToken();
        RefreshToken refreshToken = refreshTokenService.findByToken(tokenDTO);
        if (refreshToken == null) {
            throw new RuntimeException("刷新令牌不存在");
        }

        if (refreshTokenService.isTokenRevoked(refreshToken.getToken())) {
            throw new RuntimeException("刷新令牌已被撤销");
        }

        if (!refreshTokenService.verifyExpiration(refreshToken)) {
            throw new RuntimeException("刷新令牌已过期");
        }
        String username = refreshToken.getUsername();
        String accessToken = jwtTokenProvider.generateAccessToken(username);

        AccessTokenVO accessTokenVO = new AccessTokenVO(accessToken, SecurityConst.TOKEN_PREFIX, jwtTokenProvider.getAccessTokenExpiration() / 1000);

        return accessTokenVO;
    }

    @Override
    public void logout(String refreshToken) {
        if (refreshToken != null) {
            refreshTokenService.revokeRefreshToken(refreshToken);
        }
    }

    @Override
    public void logoutAll(String username) {
        refreshTokenService.revokeAllRefreshTokensByUsername(username);
    }
}
