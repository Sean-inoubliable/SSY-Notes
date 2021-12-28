package com.ssycoding.iutils.demo;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Comments: AES加密解密工具类返回Base64
 * @Author: Sean
 * @Date: 2020/4/2 14:15
 */
@Slf4j
public class AesUtil {

    private static final String AES_KEY_TYPE = "AES/GCM/NoPadding";
    private static final String AES = "AES";
    private static final String MD5 = "MD5";
    private static MessageDigest md5Digest;

    private AesUtil() { }

    static {
        try {
            md5Digest = MessageDigest.getInstance(MD5);
        } catch (NoSuchAlgorithmException e) {
            log.error("AESUtils 初始化静态代码块出现异常。异常信息：%s", e);
        }
    }

    /**
     * 加密
     *
     * @param data 需要加密的内容
     * @param key 加密密钥
     * @return
     */
    public static String encrypt(String data, String key) { return doAes(data, key, Cipher.ENCRYPT_MODE); }

    /**
     * 解密
     *
     * @param data 待解密内容
     * @param key 解密密钥
     * @return
     */
    public static String decrypt(String data, String key) { return doAes(data, key, Cipher.DECRYPT_MODE); }

    /**
     * 加解密
     *
     * @param data
     * @param key
     * @param mode
     * @return
     */
    private static String doAes(String data, String key, int mode) {
        try {
            // 区分加解密操作
            boolean encrypt = mode == Cipher.ENCRYPT_MODE;
            byte[] content;
            if (encrypt) {
                // 需加密的 byte[]
                content = data.getBytes(StandardCharsets.UTF_8);
            } else {
                // 需解密的 byte[]
                content = Base64.decodeBase64(data.getBytes());
            }
            // param1:私钥字节数组 param2:加密方式
            SecretKeySpec keySpec = new SecretKeySpec(md5Digest.digest(key.getBytes(StandardCharsets.UTF_8)), AES);
            // 实例化加密类，创建密码器，param:加密方式
            Cipher cipher = Cipher.getInstance(AES_KEY_TYPE);
            // 初始化
            cipher.init(mode, keySpec);
            byte[] result = cipher.doFinal(content);
            if (encrypt) {
                return new String(Base64.encodeBase64(result));
            } else {
                return new String(result, StandardCharsets.UTF_8);
            }
        } catch (Exception e) {
            log.error("AESUtils %s 代码块出现异常。异常信息：%s", mode == Cipher.ENCRYPT_MODE ? "加密" : "解密", e);
        }
        return null;
    }
}
