package com.sgcc.code.entity.io.versions;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 版本
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 16:35:58 CST 2023
 */
@ApiModel(value="版本修改参数类",description="修改版本请求体")
public class VersionsUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Size(max = 32, message = "主键id最多32个字符！")
    @ApiModelProperty(value="主键id",name="versionid",example = "1")
    private String versionid;
    
	//@NotNull(message = "版本号不能为空!")
	@Size(max = 32, message = "版本号最多32个字符！")
    @ApiModelProperty(value="版本号",name="verson",example = "1")
    private String verson;
    
	//@NotNull(message = "更新内容不能为空!")
	@Size(max = 255, message = "更新内容最多255个字符！")
    @ApiModelProperty(value="更新内容",name="remark",example = "1")
    private String remark;

    //@NotNull(message = "上传类型不能为空!")
    @Min(value = -1000000000, message = "上传类型值不能小于-1000000000！")
    @Max(value = 1000000000, message = "上传类型值不能大于1000000000！")
    @ApiModelProperty(value="上传类型",name="uploadType",example = "1")
    private Integer uploadType;

    //@NotNull(message = "windows更新包路径不能为空！")
    @Size(max = 100, message = "windows更新包路径最多100个字符！")
    @ApiModelProperty(value="windows更新包路径",name="winPath",example = "1")
    private String winPath;

    //@NotNull(message = "mac更新包路径不能为空！")
    @Size(max = 100, message = "mac更新包路径最多100个字符！")
    @ApiModelProperty(value="mac更新包路径",name="macPath",example = "1")
    private String macPath;
    
	//@NotNull(message = "当前版本标识 1是0否不能为空!")
	@Size(max = 1, message = "当前版本标识 1是0否最多1个字符！")
    @ApiModelProperty(value="当前版本标识 1是0否",name="currentVersion",example = "1")
    private String currentVersion;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	//@NotNull(message = "更新时间不能为空!")
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    
	public VersionsUpdateIo() {
		super();
	}

    public VersionsUpdateIo(String versionid, String verson, String remark, Integer uploadType, String winPath, String macPath, String currentVersion, Date createTime, Date updateTime) {
        this.versionid = versionid;
        this.verson = verson;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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
        return "VersionsUpdateIo{" +
                "versionid='" + versionid + '\'' +
                ", verson='" + verson + '\'' +
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