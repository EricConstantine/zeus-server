package com.sgcc.code.common.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class StringUtils {

    /**
     * 根据文件大小格式化显示
     */
    public static String formatFileSize(String fileSize) {
        return formatFileSize(Integer.parseInt(fileSize));
    }

    /**
     * 根据文件大小格式化显示
     */
    public static String formatFileSize(int fileSize) {
        String str = "";
        if (fileSize > 1000000) {
            double a = fileSize / 1000000;
            return a + "MB";
        }
        if (fileSize > 1000) {
            double a = fileSize / 1000;
            return a + "KB";
        }
        return fileSize + "字节";
    }

    public final static boolean isNull(Object[] objs) {
        if (objs == null || objs.length == 0) return true;
        return false;
    }

    public final static boolean isNull(Integer integer) {
        if (integer == null || integer == 0) return true;
        return false;
    }

    public final static boolean isNull(Byte bbb) {
        if (bbb == null || bbb == 0) return true;
        return false;
    }

    public final static boolean isNull(Double ddd) {
        if (ddd == null || ddd == 0.0) return true;
        return false;
    }

    public final static boolean isNull(Collection collection) {
        if (collection == null || collection.size() == 0) return true;
        return false;
    }

    public final static boolean isNull(Map map) {
        if (map == null || map.size() == 0) return true;
        return false;
    }

    public final static boolean isNull(String str) {
        return str == null || "".equals(str.trim()) || "null".equals(str.toLowerCase());
    }


    public final static boolean isNull(Long longs) {
        if (longs == null || longs == 0) return true;
        return false;
    }

    public final static boolean isNotNull(Long longs) {
        return !isNull(longs);
    }

    public final static boolean isNotNull(String str) {
        return !isNull(str);
    }

    public final static boolean isNotNull(Collection collection) {
        return !isNull(collection);
    }

    public final static boolean isNotNull(Map map) {
        return !isNull(map);
    }

    public final static boolean isNotNull(Integer integer) {
        return !isNull(integer);
    }

    public final static boolean isNotNull(Object[] objs) {
        return !isNull(objs);
    }

    public final static boolean isNotNull(Byte bbb) {
        return !isNull(bbb);
    }

    public static List<String> stringToList(String str, String split) {
        if (isNull(str)) {
            return new ArrayList<>();
        }
        String[] strs = str.split(split);
        List<String> strList = new ArrayList<>();
        for (String s : strs) {
            if (isNotNull(s)) {
                strList.add(s);
            }
        }
        return strList;
    }

    public static List<Long> stringToListForLong(String str, String split) {
        if (isNull(str)) {
            return new ArrayList<>();
        }
        String[] strs = str.split(split);
        List<Long> strList = new ArrayList<>();
        for (String s : strs) {
            s = s.trim();
            if (isNotNull(s)) {
                strList.add(Long.parseLong(s));
            }
        }
        return strList;
    }

    public static List<Integer> stringToListForInt(String str, String split) {
        if (isNull(str)) {
            return new ArrayList<>();
        }
        String[] strs = str.split(split);
        List<Integer> strList = new ArrayList<>();
        for (String s : strs) {
            s = s.trim();
            if (isNotNull(s)) {
                strList.add(Integer.parseInt(s));
            }
        }
        return strList;
    }

    public static List<Byte> stringToListForByte(String str, String split) {
        if (isNull(str)) {
            return new ArrayList<>();
        }
        String[] strs = str.split(split);
        List<Byte> strList = new ArrayList<>();
        for (String s : strs) {
            s = s.trim();
            if (isNotNull(s)) {
                strList.add(Byte.parseByte(s));
            }
        }
        return strList;
    }

    public static String listToString(List list, String split) {
        if (isNull(list)) {
            return "";
        }
        String str = "";
        for (Object item : list) {
            str += split + item;
        }
        if (str.length() > 0)
            str = str.substring(1);
        return str;
    }

    /**
     * 字符串补0到strLength长度
     *
     * @param str
     * @param strLength
     * @param side      为真则左补,假则右补
     * @return
     */
    public static String addZeroForNum(String str, int strLength, boolean side) {
        int strLen = str.length();
        if (strLen < strLength) {
            while (strLen < strLength) {
                StringBuffer sb = new StringBuffer();
                if (side) {
                    sb.append("0").append(str);// 左补0
                } else {
                    sb.append(str).append("0");//右补0
                }
                str = sb.toString();
                strLen = str.length();
            }
        }

        return str;
    }

    public static String substrBefore(String str, int num) {
        return str.substring(0, num);
    }

    public static String substrAfter(String str, int num) {
        return str.substring(str.length() - num, str.length());
    }

    /**
     * 获取人性化的异常信息
     *
     * @param throwable 异常
     * @return 信息
     */
    public static String toException(Throwable throwable) {
        StackTraceElement[] stackTrace = throwable.getStackTrace();
        StackTraceElement ste = (stackTrace != null && stackTrace.length > 0) ? stackTrace[0] : null;
        if (ste != null) {
            return String.format("%s - [%s] [%s.%s(%s)]", throwable.getMessage(), ste.getFileName(), ste.getClassName(), ste.getMethodName(), ste.getLineNumber());
        } else {
            return throwable.getMessage();
        }
    }

    /**
     * 验证 密码至少包含数字、小写字母、大写字母、特殊字符的几种
     *
     * @param pwd
     * @return
     */
    public static int checkPasswordLevel(String pwd) {
        int result = 0;
        if (StringUtils.isNull(pwd)) {
            return result;
        }
        if (pwd.matches(".*[0-9].*")) {
            result++;
        }
        if (pwd.matches(".*[a-z].*")) {
            result++;
        }
        if (pwd.matches(".*[A-Z].*")) {
            result++;
        }
        if (pwd.matches(".*[^0-9a-zA-Z].*")) {
            result++;
        }
        return result;
    }

    /**
     * 处理字符串长度
     */
    public static String formatStrSize(String str, int size) {
        if (isNull(str)) {
            return str;
        }
        if (str.length() > size) {
            str = substrBefore(str, size);
        }
        return str;
    }

    public static String format(String template, String s, String e, Map<String, Object> params) {
        if (isNull(template) || isNull(s) || isNull(e) || isNull(params)) {
            return template;
        }
        String result = template;
        for (Map.Entry<String, Object> param : params.entrySet()) {
            result = result.replace(s + param.getKey() + e, param.getValue().toString());
        }
        return result;
    }

    public static void main(String[] args) {
    }

}
