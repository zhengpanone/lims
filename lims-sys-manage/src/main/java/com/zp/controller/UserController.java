package com.zp.controller;

import com.zp.api.sys.UserControllerApi;
import com.zp.domain.sys.User;
import com.zp.response.ResponseResult;
import com.zp.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import controller.BaseController;

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
    public ResponseResult save(@RequestBody User user) {
        user.setCompanyId(companyId);
        user.setCompanyName(companyName);
        userService.save(user);
        return ResponseResult.SUCCESS();
    }

}
