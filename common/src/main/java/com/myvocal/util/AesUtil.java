package com.myvocal.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * @author csq
 * @description:
 * @date 2024/3/7 13:51
 */
public class AesUtil {

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            // 创建AES的密钥规范
            Key aesKey = new SecretKeySpec(secret.getBytes("UTF-8"), "AES");
            // 获取AES Cipher实例
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            // 初始化Cipher为加密模式
            cipher.init(Cipher.ENCRYPT_MODE, aesKey);
            // 执行加密操作
            byte[] encrypted = cipher.doFinal(strToEncrypt.getBytes("UTF-8"));
            // 将加密后的字节数组转换为Base64编码的字符串
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
