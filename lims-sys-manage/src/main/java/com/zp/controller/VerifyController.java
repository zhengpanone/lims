package com.zp.controller;

import com.zp.response.R;
import com.zp.service.VerifyService;
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
@RequestMapping("/verify")
@RequiredArgsConstructor
public class VerifyController {
    private final VerifyService verifyService;

    @GetMapping("/getCode")
    public void getCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        verifyService.createCode(request, response);
    }

    @GetMapping("/checkCode")
    public R<?> checkCode(@RequestParam("code") String code) {
        return verifyService.checkCode(code);
    }
}
