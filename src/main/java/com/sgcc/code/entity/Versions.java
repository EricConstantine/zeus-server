package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <p>
 * 版本
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
public class Versions implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "versionid", type = IdType.AUTO)
    private String versionid;
    
    /**
     * 版本号
     */
    private String verson;
    
    /**
     * 更新内容
     */
    private String remark;

    /**
     * 文件名称
     */
    private String fileName;

    /**
     * 文件类型
     */
    private String fileType;

    /**
     * 上传类型 1本地上传 2线上打包
     */
    private Integer uploadType;
    
    /**
     * windows更新包路径
     */
    private String winPath;

    /**
     * mac更新包路径
     */
    private String macPath;

    /**
     * 当前版本标识 1是0否
     */
    private String currentVersion;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    
    /**
     * 更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    

	public Versions() {
		super();
	}

    public Versions(String versionid, String verson, String remark, String fileName, String fileType, Integer uploadType, String winPath, String macPath, String currentVersion, Timestamp createTime, Date updateTime) {
        this.versionid = versionid;
        this.verson = verson;
        this.remark = remark;
        this.fileName = fileName;
        this.fileType = fileType;
        this.uploadType = uploadType;
        this.winPath = winPath;
        this.macPath = macPath;
        this.currentVersion = currentVersion;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public String getVersionid() {
        return versionid;
    }

    public void setVersionid(String versionid) {
        this.versionid = versionid;
    }

    public String getVerson() {
        return verson;
    }

    public void setVerson(String verson) {
        this.verson = verson;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public Integer getUploadType() {
        return uploadType;
    }

    public void setUploadType(Integer uploadType) {
        this.uploadType = uploadType;
    }

    public String getWinPath() {
        return winPath;
    }

    public void setWinPath(String winPath) {
        this.winPath = winPath;
    }

    public String getMacPath() {
        return macPath;
    }

    public void setMacPath(String macPath) {
        this.macPath = macPath;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "Versions{" +
                "versionid='" + versionid + '\'' +
                ", verson='" + verson + '\'' +
                ", remark='" + remark + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", uploadType=" + uploadType +
                ", winPath='" + winPath + '\'' +
                ", macPath='" + macPath + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}