package com.sgcc.code.entity.io.versions;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Size;

/**
 * <p>
 * 版本
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
@ApiModel(value="版本默认返回参数类",description="版本接口默认返回类")
public class VersionsIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="versionid",example = "1")
    private String versionid;
    
    @ApiModelProperty(value="版本号",name="verson",example = "1")
    private String verson;

    @ApiModelProperty(value="文件名称",name="fileName",example = "1")
    private String fileName;

    @ApiModelProperty(value="文件类型",name="fileType",example = "1")
    private String fileType;
    
    @ApiModelProperty(value="更新内容",name="remark",example = "1")
    private String remark;

    @ApiModelProperty(value="上传类型",name="uploadType",example = "1")
    private Integer uploadType;

    @ApiModelProperty(value="windows更新包路径",name="winPath",example = "1")
    private String winPath;

    @ApiModelProperty(value="mac更新包路径",name="macPath",example = "1")
    private String macPath;
    
    @ApiModelProperty(value="当前版本标识 1是0否",name="currentVersion",example = "1")
    private String currentVersion;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    

	public VersionsIo() {
		super();
	}

    public VersionsIo(String versionid, String verson, String fileName, String fileType, String remark, Integer uploadType, String winPath, String macPath, String currentVersion, Timestamp createTime, Date updateTime) {
        this.versionid = versionid;
        this.verson = verson;
        this.fileName = fileName;
        this.fileType = fileType;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "VersionsIo{" +
                "versionid='" + versionid + '\'' +
                ", verson='" + verson + '\'' +
                ", fileName='" + fileName + '\'' +
                ", fileType='" + fileType + '\'' +
                ", remark='" + remark + '\'' +
                ", uploadType=" + uploadType +
                ", winPath='" + winPath + '\'' +
                ", macPath='" + macPath + '\'' +
                ", currentVersion='" + currentVersion + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}