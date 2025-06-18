package com.zp.lims.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zp.lims.sys.entity.RefreshToken;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 13:57
 * Version : v1.0.0
 * Description: 刷新令牌服务接口
 */
public interface IRefreshTokenService extends IService<RefreshToken> {

    RefreshToken createRefreshToken(String username);

    RefreshToken findByToken(String token);

    boolean verifyExpiration(RefreshToken token);

    boolean revokeRefreshToken(String token);

    boolean revokeAllRefreshTokensByUsername(String username);

    boolean isTokenRevoked(String token);

    void cleanExpiredTokens();
}
