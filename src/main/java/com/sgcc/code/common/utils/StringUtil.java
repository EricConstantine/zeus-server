package com.sgcc.code.common.utils;


import com.sgcc.code.common.exception.CommonException;

/**
 * 工具类
 * */
public class StringUtil {

    /**为空校验 为空返回true*/
    public static boolean isEmpty(Object str) {
        return (str == null || "".equals(str)) ? true : false;
    }

    /**不为空校验 不为空返回true*/
    public static boolean isNotEmpty(Object str) {
        return !isEmpty(str);
    }

    /**校验ids*/
    public static void isValidIds(String str) {
        if(str==null || "".equals(str)){
            throw new CommonException(500,"ids不能为空");
        }else if("undefined".equals(str) || "null".equals(str)){
            throw new CommonException(500,"ids格式不正确");
        }
    }
    /**
     * 使用java正则表达式去掉多余的.与0
     * @param s
     * @return  string
     */
    public static String replace(String s){
        if(null != s && s.indexOf(".") > 0){
            s = s.replaceAll("0+?$", "");//去掉多余的0
            s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
        }
        return s;
    }

    public static String toString(Object obj) {
        return obj == null ? "" : obj.toString();
    }

    public static String toString(Object obj, String nullStr) {
        return obj == null ? nullStr : obj.toString();
    }
}
