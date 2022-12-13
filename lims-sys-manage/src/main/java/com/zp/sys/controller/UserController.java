package com.zp.sys.controller;


import com.zp.response.R;
import com.zp.sys.controller.dto.RegisterDTO;
import com.zp.sys.entity.SysUser;
import com.zp.sys.service.IUserService;
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
public class UserController {
    private final IUserService userService;

    @ApiOperation(value = "用户注册")
    @PostMapping("/register")
    public R<?> register(@Validated @RequestBody RegisterDTO registerDTO) {
        SysUser sysUser = userService.register(registerDTO);
        if (sysUser == null) {
            return R.failed();
        }
        return R.success(sysUser);
    }


}
