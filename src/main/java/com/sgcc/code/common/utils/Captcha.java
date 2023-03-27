package com.sgcc.code.common.utils;

public class Captcha {

    /**
     * 验证码内容
     */
    private String content;

    /**
     * jpg 验证码图片base64
     */
    private String imgBase64;

    public String getContent() {
        return content;
    }

     void setContent(String content) {
        this.content = content;
    }

    public String getImgBase64() {
        return imgBase64;
    }

     void setImgBase64(String imgBase64) {
        this.imgBase64 = imgBase64;
    }
}
