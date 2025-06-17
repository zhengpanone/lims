package com.zp.lims.sys.service;

import com.zp.lims.common.core.response.R;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * VerifyService
 *
 * @author zhengpanone
 * @since 2022-11-25
 */
public interface VerifyService {
    /**
     * 创建图片验证码
     *
     * @param request
     * @param response
     */
    void createCode(HttpServletRequest request, HttpServletResponse response)throws IOException;

    /**
     * 检查图片验证码
     *
     * @param verificationCode
     * @return
     */
    R<?> checkCode(String verificationCode);
}
