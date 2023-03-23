package com.zp.sys.controller;


import com.zp.base.controller.BaseController;
import com.zp.response.R;
import com.zp.sys.controller.dto.LoginDTO;
import com.zp.sys.controller.dto.RegisterDTO;
import com.zp.sys.entity.SysUser;
import com.zp.sys.service.ISysUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:07.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@RestController
@Api(tags = "用户模块")
@RequestMapping("/user")
@RequiredArgsConstructor
@Slf4j
public class UserController extends BaseController {
    private final ISysUserService sysUserService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R<?> register(@Validated @RequestBody RegisterDTO registerDTO) {
        SysUser sysUser = sysUserService.register(registerDTO);
        if (sysUser == null) {
            return R.failed();
        }
        return R.success(sysUser);
    }
@ApiOperation(value = "用户登录,返回token")
    @PostMapping("/login")
    public R<?> login(LoginDTO loginDTO){
    String token = sysUserService.login(loginDTO);
    return R.success();
    }


}
