package com.zp.lims.sys.controller;

import com.zp.lims.response.R;
import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.SysUser;
import com.zp.lims.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@Api(tags = "认证模块")
@RequestMapping("/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final ISysUserService sysUserService;

    @ApiOperation("用户注册")
    @PostMapping("/register")
    public R<SysUser> register(@Validated @RequestBody RegisterDTO registerDTO) {
        try {
            SysUser sysUser = sysUserService.register(registerDTO);
            return R.success(sysUser);
        } catch (Exception e) {
            log.error("用户注册失败", e);
            return R.failed(e.getMessage());
        }
    }

    @ApiOperation("用户登录")
    @PostMapping("/login")
    public R<LoginResponseVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        try {
            LoginResponseVO loginResponse = sysUserService.login(loginDTO);
            return R.success(loginResponse);
        } catch (Exception e) {
            log.error("用户登录失败", e);
            return R.failed(e.getMessage());
        }
    }

    @ApiOperation("刷新令牌")
    @PostMapping("/refresh")
    public R<LoginResponseVO> refresh(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        // TODO: 实现刷新token逻辑
        return R.failed("暂未实现");
    }

    @ApiOperation("用户登出")
    @PostMapping("/logout")
    public R<Boolean> logout(HttpServletRequest request) {
        // TODO: 实现登出逻辑（如token拉黑、前端删除token等）
        return R.success(true);
    }

    // 刷新token请求体
    public static class RefreshTokenRequest {
        public String refreshToken;
        public String getRefreshToken() { return refreshToken; }
        public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    }
} 