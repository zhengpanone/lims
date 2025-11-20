package com.zp.lims.sys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zp.lims.sys.config.JwtTokenProvider;
import com.zp.lims.sys.entity.RefreshToken;
import com.zp.lims.sys.mapper.RefreshTokenMapper;
import com.zp.lims.sys.service.IRefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

/**
 * @author : zhengpanone
 * Date : 2025/6/20 13:58
 * Version : v1.0.0
 * Description: 刷新令牌服务实现
 */
@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl extends ServiceImpl<RefreshTokenMapper, RefreshToken> implements IRefreshTokenService {

    private final JwtTokenProvider jwtTokenProvider;

    @Override
    public RefreshToken createRefreshToken(String username) {
        // 撤销用户现有的刷新令牌
        revokeAllRefreshTokensByUsername(username);
        RefreshToken refreshToken = new RefreshToken();
        refreshToken.setUsername(username);
        refreshToken.setToken(jwtTokenProvider.generateRefreshToken(username));
        refreshToken.setExpiryDate(LocalDateTime.now().plusSeconds(jwtTokenProvider.getRefreshTokenExpiration() / 1000));
        refreshToken.setRevoked(false);
        save(refreshToken);

        return refreshToken;
    }

    @Override
    public RefreshToken findByToken(String token) {
        return baseMapper.findByToken(token);
    }

    @Override
    public boolean verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().isBefore(LocalDateTime.now())) {
            removeById(token.getId());
            return false;
        }
        return true;
    }

    @Override
    public boolean revokeRefreshToken(String token) {
        return baseMapper.revokeByToken(token) > 0;
    }

    @Override
    public boolean revokeAllRefreshTokensByUsername(String username) {
        return baseMapper.revokeAllByUsername(username) > 0;
    }

    @Override
    public boolean isTokenRevoked(String token) {
        RefreshToken refreshToken = findByToken(token);
        return refreshToken == null || refreshToken.isRevoked();
    }

    @Override
    @Scheduled(fixedRate = 3600000) // 每小时执行一次
    public void cleanExpiredTokens() {
        baseMapper.deleteExpiredTokens();
    }
}
