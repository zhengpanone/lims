package com.zp.lims.sys.service;

import com.zp.lims.sys.controller.dto.LoginDTO;
import com.zp.lims.sys.controller.dto.RefreshTokenDTO;
import com.zp.lims.sys.controller.dto.RegisterDTO;
import com.zp.lims.sys.controller.vo.AccessTokenVO;
import com.zp.lims.sys.controller.vo.LoginResponseVO;
import com.zp.lims.sys.entity.SysUser;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 15:02
 * Version : v1.0.0
 * Description:
 */
public interface IAuthService {

    /**
     * 注册功能
     */
    SysUser register(RegisterDTO registerDTO);

    /**
     * 登录功能
     */
    LoginResponseVO login(LoginDTO loginDTO);

     AccessTokenVO refreshToken(RefreshTokenDTO refreshTokenDTO);

     void logout(String refreshToken);

    void logoutAll(String username);
}
