package com.zp.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;


/**
 * JwtUtilsTest
 *
 * @author zhengpanone
 * @since 2021-09-09
 */
public class JwtUtilsTest {
    private static String token;

    @BeforeAll
    @DisplayName("生成Token")
    public static void createToken() throws NoSuchAlgorithmException {
//        Algorithm algorithm = Algorithm.HMAC256("secret"); // 算法实例
        RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key();//获取公私钥
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        token = JwtUtils.createToken(algorithm, "admin");
        Assertions.assertNotNull(token);
        System.out.println("生成Token");
        System.out.println(token);
    }

    @Test
    @DisplayName("校验Token")
    public void verifierToken() throws NoSuchAlgorithmException {
        DecodedJWT decode = JwtUtils.verifierToken(token);
        System.out.println("获取算法: " + decode.getAlgorithm());
        System.out.println("获取token类型: " + decode.getType());
        System.out.println("获取token发布者: " + decode.getIssuer());
        System.out.println("获取过期时间: " + decode.getExpiresAt());
        System.out.println("获取生产日期: " + decode.getIssuedAt());
        System.out.println("获取Claim中的值: " + decode.getClaims());
    }

    @Test
    @DisplayName("解码token")
    public void decodeToken() {
        DecodedJWT decode = JWT.decode(token);
        System.out.println("===========decode Token==========");
        System.out.println("获取算法: " + decode.getAlgorithm());
        System.out.println("获取token类型: " + decode.getType());
        System.out.println("获取token发布者: " + decode.getIssuer());
        System.out.println("获取过期时间: " + decode.getExpiresAt());
        System.out.println("获取生产日期: " + decode.getIssuedAt());
        System.out.println("获取Claim中的值: " + decode.getClaims());
    }
}
