package com.sgcc.code.common.utils;

import java.util.Random;
import java.util.UUID;

/**
 * 随机生成工具
 */
public class RandomUtil {
    private static final String SOURCE_ALL = "aA1!bB2cC#3dD$eEfFgGh{Hi%IjJkK9lLm^MnN[oO8pP6q]Q&rR5sSt=T@uU7*vVw(W+xX4)yYz-}Z0~";
    private static final String SOURCE_NORMAL = "aA1bB2cC3dDeEfFgGhHiIjJkK9lLmMnNoO8pP6qQrR5sStTuU7vVwWxX4yYzZ0";
    private static final String SOURCE_NUM = "0123456789";

    private static String random(String source, int size) {
        StringBuffer sb = new StringBuffer();
        Random random = new Random();
        for (int i = 0; i < size; i++) {
            int number = random.nextInt(source.length());
            sb.append(source.charAt(number));
        }
        return sb.toString();
    }

    public static String all(int size) {
        return random(SOURCE_ALL, size);
    }

    public static String normal(int size) {
        return random(SOURCE_NORMAL, size);
    }

    public static String num(int size) {
        return random(SOURCE_NUM, size);
    }

    /**
     * 获取UUid
     * @return
     */
    public static final String getUUID(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取随机数
     * @param length
     * @return
     */
    public static String getRandomNums(int length) {
        String str="0123456789";
        Random random=new Random();
        StringBuffer sf = new StringBuffer();
        for(int i=0;i<length;i++)
        {
            int number=random.nextInt(10);//0~10
            sf.append(str.charAt(number));
        }
        return sf.toString();
    }

}
