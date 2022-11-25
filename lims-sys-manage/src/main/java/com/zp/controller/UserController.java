package com.zp.controller;


import com.zp.RedisUtils;
import com.zp.controller.dto.LoginDTO;
import com.zp.controller.vo.LoginInfoVO;
import com.zp.controller.vo.LoginVO;
import com.zp.controller.vo.UserInfo;
import com.zp.response.R;
import com.zp.service.VerifyService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhengpanone
 * @Description:
 * @Date:Created in 2021/08/06 21:07.
 * @Email zhengpanone@hotmail.com
 * @Modified By:
 */
@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    RedisUtils redisUtils;
    @Autowired
    VerifyService verifyService;

    @PostMapping("/login")
    public R<LoginVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        LOGGER.info("提交的用户数据为:" + loginDTO.toString());
        verifyService.checkCode(loginDTO.getImgCode());
        LoginVO loginVO = new LoginVO();
        loginVO.setToken("token");
        loginVO.setVersion("1.0.0");
        loginVO.setExpiresTime(new Date());
        UserInfo userInfo = new UserInfo();
        userInfo.setId("1");
        userInfo.setAccount(loginDTO.getAccount());
        loginVO.setUserInfo(userInfo);
        return R.success(loginVO);
    }

    @PostMapping("/register")
    public R<?> register(@Validated LoginDTO loginDTO) {
        return R.success();
    }

    /**
     * 登录页 图片数据
     * @return
     */
    @GetMapping("/login/info")
    public R<LoginInfoVO> loginInfo() {
        LoginInfoVO infoVO = new LoginInfoVO();
        infoVO.setSlide(new String[]{"a","b","c"});
        infoVO.setLoginLogo("loginLogo");
        infoVO.setLogoRectangle("logo rectangle");
        infoVO.setLogoSquare("logo square");

        return R.success(infoVO);
    }
    @GetMapping("/redis")
    public R<?> testRedis(){
        redisUtils.set("test","test");
        return R.success();
    }

    @GetMapping("/redis/{key}")
    public R<?> testRedisKey(@PathVariable String key){
        Object value = redisUtils.get(key);
        Map<String,Object> data = new HashMap<String, Object>();
        data.put(key, value);
        return R.success(data);
    }
}
