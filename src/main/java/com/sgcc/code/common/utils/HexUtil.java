package com.sgcc.code.common.utils;

/**
 * 十六进制 处理工具
 */
public class HexUtil {
    /**
     * 将二进制转换成十六进制
     * @param bytes
     * @return
     */
    public static String parseByteToHexStr(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for(int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(bytes[i] & 0xFF);
            if(hex.length() == 1) {
                hex = '0'+ hex;
            }
            sb.append(hex.toUpperCase());
        }
        return sb.toString();
    }

    /**
     * 将十六进制转换为二进制
     * @param hexStr
     * @return
     */
    public static byte[] parseHexStrToByte(String hexStr) {
        if(hexStr.length() < 1) {
            return null ;
        }else{
            byte[] result =new byte[hexStr.length() / 2];
            for(int i = 0; i < hexStr.length() / 2; i++) {
                int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
                int low = Integer.parseInt(hexStr.substring(i * 2 + 1,i * 2 + 2),16);
                result[i] = (byte) (high * 16 + low);
            }
            return result;
        }
    }
}
