package com.zp.utils;

import cn.hutool.core.lang.UUID;
import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.commons.lang3.time.DateUtils;

import java.security.NoSuchAlgorithmException;
import java.util.Calendar;
import java.util.Date;

/**
 * JWTUtils
 *
 * @author zhengpanone
 * @since 2021-08-30
 */
public class JwtUtils {
    private static final String ISSUER = "BACK_API";

    /**
     * 获取签发的token，返回给前端
     *
     * @param object
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static String generTokenByRSA256(Object object) throws NoSuchAlgorithmException {
        RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key();//获取公私钥
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        return createToken(algorithm, object);

    }


    public static String createToken(Algorithm algorithm, Object data) {

        String[] audience = {"app", "web"};

        Calendar nowTime = Calendar.getInstance();
        nowTime.add(Calendar.MINUTE, 30);
        /*Date expiresDate = nowTime.getTime();*/
//            Algorithm algorithm = Algorithm.HMAC256(userId + "secret"); // 算法实例
        Date expiresDate = DateUtils.addHours(new Date(), 2);
        return JWT.create().withAudience(audience) //签发对象
                .withIssuer(ISSUER) // 发布者
                .withIssuedAt(new Date())// 生成签名时间
                .withExpiresAt(expiresDate)// 有效时间
                .withClaim("data", JSON.toJSONString(data)) // 载荷
                .withJWTId(UUID.randomUUID().toString()) // 编号
                .sign(algorithm);// 加密



    }


    public static DecodedJWT verifierToken(String token) throws NoSuchAlgorithmException {
        RSA256Key rsa256Key = SecretKeyUtils.getRSA256Key();// 获取公钥/私钥
        Algorithm algorithm = Algorithm.RSA256(rsa256Key.getPublicKey(), rsa256Key.getPrivateKey());
        // Algorithm algorithm = Algorithm.HMAC256(userId + "secret");

        JWTVerifier verifier = JWT.require(algorithm)
                .withIssuer(ISSUER) // 配置指定的token发布者auth0
                .build();
        return verifier.verify(token);


    }


}
