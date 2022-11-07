package com.zp.controller;


import com.zp.controller.dto.LoginDTO;
import com.zp.controller.vo.UserVO;
import com.zp.response.R;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@RequestMapping("/user")
@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/login")
    public R<UserVO> login(@Validated @RequestBody LoginDTO loginDTO) {
        LOGGER.info("提交的用户数据为:" + loginDTO.toString());
        UserVO userVO = new UserVO();
        userVO.setUserId(1);
        userVO.setUserName(loginDTO.getUserName());
        userVO.setUserLevel(10);
        return R.success(userVO);
    }

    @PostMapping("/register")
    public R<?> register(@Validated LoginDTO loginDTO) {
        return R.success();
    }
}
