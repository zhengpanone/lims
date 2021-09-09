package com.zp.utils;

import lombok.Data;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * RSA256Key
 *
 * @author zhengpanone
 * @since 2021-09-09
 * 非对称加密实体类
 */
@Data
public class RSA256Key {
    private RSAPublicKey publicKey;
    private RSAPrivateKey privateKey;
}
