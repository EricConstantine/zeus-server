package com.sgcc.code.common.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 业务ID工具类
 * @author: eric
 * @date: 2022/12/5 9:40
 */
public class ServiceIdUtils {

    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /**
     * 根据prefix 生成规则小类时间戳
     *
     * @return
     */
    public static String getRuleId(String prefix) {
        String serviceId = "";
        if (prefix == null || prefix.trim().length() == 0) {
            return "";
        }
        String firstLetter = StringConvertLetterUtil.getAllFirstLetter(prefix, true);
        String dateStr = DATE_FORMAT.format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","");
        serviceId = firstLetter + dateStr;
        return serviceId;
    }

    /**
     * 生成业务规则时间戳
     *
     * @return
     */
    public static String getServiceRuleId() {
        return "YWGZ"+DATE_FORMAT.format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","");
    }

    /**
     * 生成校验作业时间戳
     *
     * @return
     */
    public static String getTaskId() {
        return "JYZY"+DATE_FORMAT.format(new Date()).replaceAll("-","").replaceAll(" ","").replaceAll(":","");
    }

    public static void main(String[] args) {
        String yzx = ServiceIdUtils.getRuleId("一致性");
        String yzx1 = ServiceIdUtils.getServiceRuleId();
        String yzx2 = ServiceIdUtils.getTaskId();
        System.out.println(yzx);
        System.out.println(yzx1);
        System.out.println(yzx2);
    }

}
