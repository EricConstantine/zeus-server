package com.sgcc.code.common.web;

import java.time.LocalDate;
import java.util.Date;

public class ResponseHeader {

    private String respID;//返回流水号
    private LocalDate respTime;//返回时间（YYYY-MM-DD HH:mm:mm sss）
    private String reqID;//对应的请求流水号
    private String respCode;//业务返回代码
    private String respDesc;//业务返回描述

    public String getRespID() {
        return respID;
    }

    public void setRespID(String respID) {
        this.respID = respID;
    }

    public LocalDate getRespTime() {
        return respTime;
    }

    public void setRespTime(LocalDate respTime) {
        this.respTime = respTime;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public String getRespCode() {
        return respCode;
    }

    public void setRespCode(String respCode) {
        this.respCode = respCode;
    }

    public String getRespDesc() {
        return respDesc;
    }

    public void setRespDesc(String respDesc) {
        this.respDesc = respDesc;
    }

    @Override
    public String toString() {
        return "ResponseHeader{" +
                "respID='" + respID + '\'' +
                ", respTime='" + respTime + '\'' +
                ", reqID='" + reqID + '\'' +
                ", respCode='" + respCode + '\'' +
                ", respDesc='" + respDesc + '\'' +
                '}';
    }
}
