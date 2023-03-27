package com.sgcc.code.common.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MathUtils {
    public static void main(String[] args) {
    }

    /**
     * 金额转换 double类型单位元 转换成 integer类型单位分
     */
    public static int changeAmountYuanToFen(double yuan) {
        BigDecimal yuanB = new BigDecimal(yuan);
        BigDecimal fenB = decimalMultiply(yuanB, new BigDecimal(100));
        return fenB.intValue();
    }

    /**
     * 金额转换 integer类型单位分 转换成 double类型单位元
     */
    public static double changeAmountFenToYuan(int fen) {
        BigDecimal fenB = new BigDecimal(fen);
        BigDecimal yuanB = decimalDivide(fenB, new BigDecimal(100), 2);
        return yuanB.doubleValue();
    }

    /**
     * 金额转换 double类型单位元 转换成 integer类型单位分
     */
    public static int changeY2F(String yuan) {
        if (StringUtils.isNull(yuan))
            return 0;
        BigDecimal yuanB = new BigDecimal(yuan);
        BigDecimal fenB = decimalMultiply(yuanB, new BigDecimal(100));
        return fenB.intValue();
    }

    public static int changeY2F(double yuan) {
        return changeAmountYuanToFen(yuan);
    }

    //分转元
    public static BigDecimal changeF2Y_b(int fen) {
        BigDecimal fenB = new BigDecimal(fen);
        BigDecimal yuanB = decimalDivide(fenB, new BigDecimal(100), 2);
        return yuanB;
    }

    public static String changeF2Y(int fen) {
        return changeF2Y_b(fen).toString();
    }

    public static double changeF2Y_d(int fen) {
        return changeF2Y_b(fen).doubleValue();
    }

    /**
     * 精确的小数加法(num1+num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double decimalAdd(double num1, double num2) {
        BigDecimal bDouble1 = new BigDecimal(Double.valueOf(num1).toString());
        BigDecimal bDouble2 = new BigDecimal(Double.valueOf(num2).toString());
        BigDecimal result = bDouble1.add(bDouble2);
        return result.doubleValue();
    }

    /**
     * 精确的小数减法(num1-num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double decimalSubtract(double num1, double num2) {
        BigDecimal bDouble1 = new BigDecimal(Double.valueOf(num1).toString());
        BigDecimal bDouble2 = new BigDecimal(Double.valueOf(num2).toString());
        BigDecimal result = bDouble1.subtract(bDouble2);
        return result.doubleValue();
    }

    /**
     * 精确的小数乘法(num1*num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static double decimalMultiply(double num1, double num2) {
        BigDecimal bDouble1 = new BigDecimal(Double.valueOf(num1).toString());
        BigDecimal bDouble2 = new BigDecimal(Double.valueOf(num2).toString());
        BigDecimal result = bDouble1.multiply(bDouble2);
        return result.doubleValue();
    }

    /**
     * 精确的小数除法(num1/num2)
     *
     * @param num1  被除数
     * @param num2  除数
     * @param index 保留几位小数
     * @return
     */
    public static double decimalDivide(double num1, double num2, int index) {
        BigDecimal bDouble1 = new BigDecimal(Double.valueOf(num1).toString());
        BigDecimal bDouble2 = new BigDecimal(Double.valueOf(num2).toString());
        BigDecimal result = bDouble1.divide(bDouble2, index, RoundingMode.HALF_UP);
        return result.doubleValue();
    }

    /**
     * 精确的小数加法(num1+num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal decimalAdd(BigDecimal num1, BigDecimal num2) {
        BigDecimal result = num1.add(num2);
        return result;
    }

    /**
     * 精确的小数减法(num1-num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal decimalSubtract(BigDecimal num1, BigDecimal num2) {
        BigDecimal result = num1.subtract(num2);
        return result;
    }

    /**
     * 精确的小数乘法(num1*num2)
     *
     * @param num1
     * @param num2
     * @return
     */
    public static BigDecimal decimalMultiply(BigDecimal num1, BigDecimal num2) {
        BigDecimal result = num1.multiply(num2);
        return result;
    }

    /**
     * 精确的小数除法(num1/num2)
     *
     * @param num1  被除数
     * @param num2  除数
     * @param index 保留几位小数
     * @return
     */
    public static BigDecimal decimalDivide(BigDecimal num1, BigDecimal num2, int index) {
        BigDecimal result = num1.divide(num2, index, RoundingMode.HALF_UP);
        return result;
    }

}
