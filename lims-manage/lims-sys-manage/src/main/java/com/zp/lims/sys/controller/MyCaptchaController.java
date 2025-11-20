package com.zp.lims.sys.controller;

import cn.hutool.core.util.StrUtil;
import com.anji.captcha.model.vo.CaptchaVO;
import com.anji.captcha.service.CaptchaService;
import com.zp.lims.common.core.response.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Author : zhengpanone
 * Date : 2024/4/26 10:05
 * Version : v1.0.0
 * Description: TODO
 * @author zhengpanone
 */
@RestController
@RequestMapping("/captcha")
public class MyCaptchaController {

    @Autowired
    private CaptchaService captchaService;

    @PostMapping("/generate")
    public R<CaptchaVO> getCaptcha(@RequestBody CaptchaVO data, HttpServletRequest request) {
        assert request.getRemoteHost() != null;
        data.setBrowserInfo(getRemoteId(request));
        CaptchaVO repData = (CaptchaVO)captchaService.get(data).getRepData();
        return R.success(repData);
    }

    public static final String getRemoteId(HttpServletRequest request) {
        String xfwd = request.getHeader("X-Forwarded-For");
        String ip = getRemoteIpFromXfwd(xfwd);
        String ua = request.getHeader("User-Agent");
        return StrUtil.isNotBlank(ip) ? ip + ua : request.getRemoteAddr() + ua;
    }

    public static final String getRemoteIpFromXfwd(String xfwd) {
        if (StrUtil.isNotBlank(xfwd)) {
            String[] ipList = xfwd.split(",");
            return StrUtil.trim(ipList[0]);
        } else {
            return null;
        }
    }
}
