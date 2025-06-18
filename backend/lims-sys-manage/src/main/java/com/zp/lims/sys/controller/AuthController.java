package com.zp.lims.sys.controller;

import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RefreshTokenDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.vo.AccessTokenVO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.IAuthService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RestController
@Api(tags = "认证模块")
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final IAuthService authService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R<?> register(@Valid @RequestBody RegisterDTO registerDTO) {
        try {
            SysUser sysUser = authService.register(registerDTO);
            return R.success(sysUser);
        } catch (Exception e) {
            log.error("用户注册失败", e);
            return R.error(e.getMessage());
        }
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R<LoginResponseVO> login(@Valid @RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseVO loginResponse = authService.login(loginDTO);
            return R.success(loginResponse);
        } catch (Exception e) {
            log.error("用户登录失败", e);
            return R.error(e.getMessage());
        }
    }

    @ApiOperation("刷新令牌")
    @PostMapping("/refresh")
    public R<?> refresh(@Valid @RequestBody RefreshTokenDTO refreshTokenDTO) {
        try {
            AccessTokenVO accessTokenVO = authService.refreshToken(refreshTokenDTO);
            return R.success("刷新令牌成功", accessTokenVO);
        } catch (Exception e) {
            log.error("刷新令牌失败", e);
            return R.error(401, e.getMessage());
        }

    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public R<Void> logout(@RequestBody RefreshTokenDTO refreshTokenDTO) {
        try {
            authService.logout(refreshTokenDTO.getRefreshToken());
            return R.success("注销成功");
        } catch (Exception e) {
            log.error("用户登出失败", e);
            return R.error("注销失败：" + e.getMessage());
        }
    }

    @ApiOperation("注销所有设备")
    @PostMapping("/logout-all")
    public R<Void> logoutAll(Authentication authentication) {
        try {
            authService.logoutAll(authentication.getName());
            return R.success("已注销所有设备", null);
        } catch (Exception e) {
            return R.error("操作失败：" + e.getMessage());
        }
    }


} 