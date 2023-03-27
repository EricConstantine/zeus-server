package com.sgcc.code.common.web;

import java.util.Date;
import java.util.List;

//请求体
public class RequestHeader {

//    微服务接口编码
    private String bizCode;

//    请求流水号（写入文件日志）
    private String reqID;

//    请求发起时间
    private Date reqTime;

//    请求渠道编码
    private String channelCode;

//    请求端系统编码
    private String reqSysCode;

//    交易优先级 （5最高，1最低，默认为3）
    private int priorityLevel;

//    供电单位编码
    private String orgNo;

//    操作用户编码
    private String userNo;

//    操作用户工号
    private String jobNumber;

//    操作用户名称
    private String username;

//    操作用户权限
    private String roleCode;

//    操作用户IP地址
    private String userIP;

//    令牌
    private String token;

//    接口版本号（初始为“1.0”）
    private String version;

//    模块名
    private String moduleName;

//    方法名
    private String funcName;

//    机构权限
    private List<String> organizationCodes;

    public String getBizCode() {
        return bizCode;
    }

    public void setBizCode(String bizCode) {
        this.bizCode = bizCode;
    }

    public String getReqID() {
        return reqID;
    }

    public void setReqID(String reqID) {
        this.reqID = reqID;
    }

    public Date getReqTime() {
        return reqTime;
    }

    public void setReqTime(Date reqTime) {
        this.reqTime = reqTime;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getReqSysCode() {
        return reqSysCode;
    }

    public void setReqSysCode(String reqSysCode) {
        this.reqSysCode = reqSysCode;
    }

    public int getPriorityLevel() {
        return priorityLevel;
    }

    public void setPriorityLevel(int priorityLevel) {
        this.priorityLevel = priorityLevel;
    }

    public String getOrgNo() {
        return orgNo;
    }

    public void setOrgNo(String orgNo) {
        this.orgNo = orgNo;
    }

    public String getUserNo() {
        return userNo;
    }

    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getUserIP() {
        return userIP;
    }

    public void setUserIP(String userIP) {
        this.userIP = userIP;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getFuncName() {
        return funcName;
    }

    public void setFuncName(String funcName) {
        this.funcName = funcName;
    }

    public List<String> getOrganizationCodes() {
        return organizationCodes;
    }

    public void setOrganizationCodes(List<String> organizationCodes) {
        this.organizationCodes = organizationCodes;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    @Override
    public String toString() {
        return "RequestHeader{" +
                "bizCode='" + bizCode + '\'' +
                ", reqID='" + reqID + '\'' +
                ", reqTime=" + reqTime +
                ", channelCode='" + channelCode + '\'' +
                ", reqSysCode='" + reqSysCode + '\'' +
                ", priorityLevel=" + priorityLevel +
                ", orgNo='" + orgNo + '\'' +
                ", userNo='" + userNo + '\'' +
                ", jobNumber='" + jobNumber + '\'' +
                ", username='" + username + '\'' +
                ", roleCode='" + roleCode + '\'' +
                ", userIP='" + userIP + '\'' +
                ", token='" + token + '\'' +
                ", version='" + version + '\'' +
                ", moduleName='" + moduleName + '\'' +
                ", funcName='" + funcName + '\'' +
                ", organizationCodes='" + organizationCodes + '\'' +
                '}';
    }
}
