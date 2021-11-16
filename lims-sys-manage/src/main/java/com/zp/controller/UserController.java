package com.zp.controller;

import com.zp.api.sys.UserControllerApi;
import com.zp.domain.sys.SysUser;
import com.zp.response.ResponseResult;
import com.zp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.Optional;

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
    public ResponseResult save(@RequestBody SysUser sysUser) {
        sysUser.setCompanyId(companyId);
        sysUser.setCompanyName(companyName);
        userService.save(sysUser);
        return ResponseResult.SUCCESS();
    }
    @Override
    @GetMapping("/user")
    public ResponseResult findUserById(@PathParam("id")String id){
        Optional<SysUser> byId = userService.findById(id);
        return ResponseResult.SUCCESS();
    }

}
