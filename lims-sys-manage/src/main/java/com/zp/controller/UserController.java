package com.zp.controller;

import com.zp.annotation.SysLog;
import com.zp.api.sys.UserControllerApi;
import com.zp.domain.sys.SysUser;
import com.zp.response.Result;
import com.zp.response.ResultCode;
import com.zp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/08 9:28.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */

@CrossOrigin // 解决跨域问题
@RestController
@RequestMapping("/sys")
public class UserController extends BaseController implements UserControllerApi {
    @Autowired
    private UserService userService;


    @Override
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    @SysLog("新增用户")
    public Result save(@RequestBody SysUser sysUser) {
        sysUser.setCompanyId(companyId);
        sysUser.setCompanyName(companyName);
        userService.save(sysUser);
        return new Result(ResultCode.SUCCESS);
    }

    @Override
    @GetMapping("/user")
    @SysLog("查询用户")
    public Result findUserById(@PathParam("id") String id) {
        userService.findById(id);
        return new Result(ResultCode.SUCCESS);
    }

}
