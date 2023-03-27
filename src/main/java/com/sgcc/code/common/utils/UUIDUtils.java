package com.sgcc.code.common.utils;

import java.util.UUID;

/**
 * UUID工具类
 *
 * @author shenhl
 */
public class UUIDUtils {

    /**
     * 生成带“-”的36位UUID
     *
     * @return
     */
    public static String uuid36() {

        return UUID.randomUUID().toString();
    }

    /**
     * 生成不带“-”的32位UUID
     *
     * @return
     */
    public static String uuid() {

        return uuid32();
    }

    /**
     * 生成不带“-”的32位UUID
     *
     * @return
     */
    public static String uuid32() {

        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
