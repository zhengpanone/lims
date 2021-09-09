package com.zp.utils;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;


/**
 * SecretKeyUtils
 *
 * @author zhengpanone
 * @since 2021-09-09
 */
public class SecretKeyUtils {
    public static final String KEY_ALGORITHM = "RSA";
    public static final String PUBLIC_KEY = "RSAPubicKey";
    public static final String PRIVATE_KEY = "RSAPrivateKey";
    private static RSA256Key rsa256Key;

    /**
     * 获得公钥
     *
     * @param keyMap
     * @return
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        //获得map中的公钥对象 转为key对象
        Key publicKey = (Key) keyMap.get(PUBLIC_KEY);
        //编码返回字符串
        return encryptBASE64(publicKey.getEncoded());
    }


    public static String getPublicKey(RSA256Key rsa256Key) {
        RSAPublicKey publicKey = rsa256Key.getPublicKey();
        return encryptBASE64(publicKey.getEncoded());
    }

    /**
     * 获得私钥
     *
     * @param keyMap
     * @return
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        //获得map中的私钥对象 转为key对象
        Key privateKey = (Key) keyMap.get(PRIVATE_KEY);
        //编码返回字符串
        return encryptBASE64(privateKey.getEncoded());
    }

    public static String getPrivateKey(RSA256Key rsa256Key) {
        RSAPrivateKey privateKey = rsa256Key.getPrivateKey();
        return encryptBASE64(privateKey.getEncoded());
    }

    /**
     * 编码返回字符串
     *
     * @param key
     * @return
     */
    public static String encryptBASE64(byte[] key) {
        return Base64.getEncoder().encodeToString(key);
    }

    /**
     * 解码返回byte
     *
     * @param key
     * @return
     */
    public static byte[] decryptBASE64(String key) {
        return Base64.getDecoder().decode(key);
    }

    /**
     * 使用KeyPairGenerator 生成公私钥，存放于map对象中
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static Map<String, Object> initKey() throws NoSuchAlgorithmException {
        /* RSA算法要求有一个可信任的随机数源 */
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        //获得对象 KeyPairGenerator 参数 RSA 1024个字节
        keyPairGenerator.initialize(1024);
        //通过对象 KeyPairGenerator 生成密匙对 KeyPair
        KeyPair keyPair = keyPairGenerator.generateKeyPair();
        //通过对象 KeyPair 获取RSA公私钥对象RSAPublicKey RSAPrivateKey
        RSAPublicKey publicKey = (RSAPublicKey)keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey)keyPair.getPrivate();
        HashMap<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * 获取公私钥
     * @return
     * @throws NoSuchAlgorithmException
     */
    public static synchronized RSA256Key getRSA256Key() throws NoSuchAlgorithmException {
        if(rsa256Key==null){
            synchronized (RSA256Key.class){
                if(rsa256Key==null){
                    rsa256Key = new RSA256Key();
                    Map<String, Object> map = initKey();
                    rsa256Key.setPublicKey((RSAPublicKey) map.get(PUBLIC_KEY));
                    rsa256Key.setPrivateKey((RSAPrivateKey) map.get(PRIVATE_KEY));
                }
            }
        }
        return rsa256Key;
    }

    public static void main(String[] args) {
        Map<String,Object> keyMap;
        try {
            keyMap = initKey();
            System.out.println("公钥"+getPublicKey(keyMap));
            System.out.println("私钥"+getPrivateKey(keyMap));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
