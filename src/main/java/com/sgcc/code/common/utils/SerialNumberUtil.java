package com.sgcc.code.common.utils;

/**
 * 序列号生成工具类
 */
public class SerialNumberUtil {

    /**
     * 获取序号号
     *
     * @param prefix       前缀
     * @param serialNumber 序列数字
     * @param maxLength    最大长度（包括前缀）
     * @return
     */
    public static String getSerialNumber(String prefix, Integer serialNumber, int maxLength) {
        if (serialNumber == null) {
            return "";
        }
        int prefixLength = 0;
        if (!StringUtil.isEmpty(prefix)) {
            prefixLength = prefix.length();
        }
        int temp = maxLength - prefixLength;
        return (prefix == null ? "" : prefix) + String.format("%0" + temp + "d", serialNumber);
    }

    /**
     * 获取序号号
     *
     * @param serialNumber 序列数字
     * @param maxLength    最大长度
     * @return
     */
    public static String getSerialNumber(Integer serialNumber, int maxLength) {
        return getSerialNumber(null, serialNumber, maxLength);
    }
}
