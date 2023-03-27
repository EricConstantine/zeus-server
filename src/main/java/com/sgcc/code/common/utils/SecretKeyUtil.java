package com.sgcc.code.common.utils;

import sun.misc.BASE64Encoder;

import javax.crypto.KeyGenerator;

/**
 * 加密3DES秘钥生成工具类
 * @author : liuguohua
 * @date : 2023/3/6 10:19
 * @modyified By :
 */
public class SecretKeyUtil {

    /**
     * 生成32位随机密钥
     * @return
     * @throws Exception
     */
    public static String generateSecretKey() throws Exception {
        //对称加密3DES秘钥生成
        KeyGenerator kg = KeyGenerator.getInstance("DESede");
        kg.init(112);//must be equal to 112 or 168\
        System.out.println("SecretKey:");
        System.out.println(new BASE64Encoder().encode(kg.generateKey().getEncoded()));
        return new BASE64Encoder().encode(kg.generateKey().getEncoded());
    }

}
