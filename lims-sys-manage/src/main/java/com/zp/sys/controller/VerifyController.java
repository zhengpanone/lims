package com.zp.sys.controller;

import com.zp.annotation.LimitRequest;
import com.zp.response.R;
import com.zp.sys.service.VerifyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * VerifyController
 *
 * @author zhengpanone
 * @since 2022-11-25
 */
@RestController
@Api(tags = "图片验证码")
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyController {
    private final VerifyService verifyService;

    @LimitRequest(count = 5)
    @GetMapping("/getCode")
    @ApiOperation("获取图片验证码")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        verifyService.createCode(request, response);
    }

    @GetMapping("/checkCode")
    @ApiOperation("校验图片验证码")
    public R<?> checkCode(@RequestParam("code") String code) {
        return verifyService.checkCode(code);
    }
}
