package com.sgcc.code.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import java.util.Random;

public class CaptchaUtil {

    private static final Logger logger = LoggerFactory.getLogger(CaptchaUtil.class);
    private static char mapTable[] = {
            '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', '0', '1',
            '2', '3', '4', '5', '6', '7',
            '8', '9'};

    /**
     * 获取图片验证码
     *
     * @param width     生成图片的宽
     * @param height    生成图片的高
     * @param codeCount 验证码字符数
     * @return map：
     */
    public static Captcha getCaptcha(int width, int height, int codeCount) {
        Captcha captcha = new Captcha();
        //字体的宽度
        int fountWidth = width / (codeCount + 2);
        // 字体高度
        int fontHeight = height - 2;
        //字体处在Y轴位置
        int codeY = height - 4;
        try {
            if (width <= 0) width = 60;
            if (height <= 0) height = 20;
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            // 获取图形上下文
            Graphics g = image.getGraphics();
            //生成随机类
            Random random = new Random();
            // 设定背景色
            g.setColor(getRandColor(200, 250));
            g.fillRect(0, 0, width, height);
            //设定字体
            g.setFont(new Font("Times New Roman", Font.PLAIN, fontHeight));
            // 随机产生168条干扰线，使图象中的认证码不易被其它程序探测到
            g.setColor(getRandColor(160, 200));
            for (int i = 0; i < 168; i++) {
                int x = random.nextInt(width);
                int y = random.nextInt(height);
                int xl = random.nextInt(12);
                int yl = random.nextInt(12);
                g.drawLine(x, y, x + xl, y + yl);
            }
            //取随机产生的码
            StringBuffer strEnsure = new StringBuffer();
            for (int i = 0; i < codeCount; ++i) {
                strEnsure.append(mapTable[(int) (mapTable.length * Math.random())]);
                // 将认证码显示到图象中
                g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
                //直接生成
                String str = strEnsure.substring(i, i + 1);
                g.drawString(str, (i + 1) * fountWidth, codeY);
            }
            // 释放图形上下文
            g.dispose();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(image, "jpg", outputStream);
            String base64Img = Base64.getEncoder().encodeToString(outputStream.toByteArray());
            outputStream.close();
            captcha.setContent(strEnsure.toString());
            captcha.setImgBase64(base64Img);
        } catch (Exception e) {
            logger.error("生成验证码异常", e);
        }
        return captcha;
    }

    //给定范围获得随机颜色
    private static Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255) fc = 255;
        if (bc > 255) bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
