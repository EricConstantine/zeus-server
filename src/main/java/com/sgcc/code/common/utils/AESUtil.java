package com.sgcc.code.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;


/**
 * AES 加密和解密算法
 *
 * @author Administrator
 */
public class AESUtil {

    private static final Logger logger = LoggerFactory.getLogger(AESUtil.class);

    /**
     * 获取解密后的字符串
     *
     * @param content
     * @param passcode
     * @return
     */
    public static String decrypt(String content, String passcode) {
        byte[] decryptFrom = HexUtil.parseHexStrToByte(content);
        byte[] decryptResult = decryptToBytes(decryptFrom, passcode);
        String decryptString = new String(decryptResult);
        return decryptString;
    }

    /**
     * 获取加密后的字符串
     *
     * @param content
     * @param passcode
     * @return
     */
    public static String encrypt(String content, String passcode) {
        byte[] encryptResult = encryptToBytes(content, passcode);
        String encryptResultStr = HexUtil.parseByteToHexStr(encryptResult);
        return encryptResultStr;
    }


    /**
     * 加密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] encryptToBytes(String content, String password) {
        try {
            SecretKey secretKey = generateKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            /**创建密码器**/
            Cipher cipher = Cipher.getInstance("AES");
            byte[] byteContent = content.getBytes("utf-8");
            /**初始化密码器**/
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] result = cipher.doFinal(byteContent);
            return result;
        } catch (Exception e) {
            logger.error("AES-encryptToBytes-异常", e);
        }
        return null;
    }

    /**
     * 解密
     *
     * @param content
     * @param password
     * @return
     */
    public static byte[] decryptToBytes(byte[] content, String password) {
        try {
            SecretKey secretKey = generateKey(password);
            byte[] enCodeFormat = secretKey.getEncoded();
            SecretKeySpec key = new SecretKeySpec(enCodeFormat, "AES");
            /**创建密码器**/
            Cipher cipher = Cipher.getInstance("AES");
            /**初始化密码器**/
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] result = cipher.doFinal(content);
            return result;
        } catch (Exception e) {
            logger.error("AES-decryptToBytes-异常", e);
        }
        return null;
    }

    private static SecretKey generateKey(String password) throws Exception {
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
        secureRandom.setSeed(password.getBytes());
        KeyGenerator  kg = KeyGenerator.getInstance("AES");
        kg.init(128, secureRandom);
        // 生成密钥
        return kg.generateKey();
    }
}
