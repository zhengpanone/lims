package com.zp.lims.sys.service.impl;

import com.zp.lims.RedisUtils;
import com.zp.lims.common.exception.BusinessException;
import com.zp.lims.common.core.response.R;
import com.zp.lims.sys.service.VerifyService;
import com.zp.lims.util.CaptchaVerifyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.stereotype.Service;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.time.Duration;

/**
 * VerifyServiceImpl
 *
 * @author zhengpanone
 * @since 2022-11-25
 */
@Service
public class VerifyServiceImpl implements VerifyService {
    @Autowired
    RedisUtils redisUtils;

    /**
     * 生成图片验证码
     *
     * @param request
     * @param response
     */
    @Override
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // 获取session
        HttpSession session = request.getSession();
        // 获取sessionId
        String sessionId = session.getId();
        ResponseCookie cookie = ResponseCookie.from("JSESSIONID", sessionId)
                .secure(true)
                .domain("")
                .path("/")
                .maxAge(Duration.ofHours(1))
                .sameSite("None").build();
        // 清除之前缓存订单图片验证码
        if (!String.valueOf(request.getSession().getAttribute("SESSION_VERIFY_CODE_" + sessionId)).isEmpty()) {
            String getVerify = String.valueOf(request.getSession().getAttribute("SESSION_VERIFY_CODE_" + sessionId));
            redisUtils.del(getVerify);
        }
        // 生成图片验证码
        Object[] verify = CaptchaVerifyUtil.newBuilder().build().createImage();
        String verifyCode = ((String) verify[0]).toUpperCase();
        // 将验证码存入session
        session.setAttribute("SESSION_VERIFY_CODE_" + sessionId, verifyCode);
        // 将验证码存入redis
        redisUtils.set(verifyCode, sessionId);
        // 将图片传给浏览器
        BufferedImage image = (BufferedImage) verify[1];
        response.setContentType("image/png");
        response.setHeader(HttpHeaders.SET_COOKIE, cookie.toString());
        ServletOutputStream outputStream = response.getOutputStream();
        ImageIO.write(image, "png", outputStream);


    }

    @Override
    public R<?> checkCode(String verificationCode) {
        if (!redisUtils.hasKey(verificationCode.toUpperCase())) {
            throw new BusinessException("验证码错误");
        }
        redisUtils.del(verificationCode);
        return R.success();
    }
}
