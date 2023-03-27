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
@ApiModel(value="版本查询参数类",description="查询版本请求体")
public class VersionsQueryIo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 32, message = "版本号最多32个字符！")
    @ApiModelProperty(value="版本号",name="verson",example = "1")
    private String verson;

	@Size(max = 255, message = "更新内容最多255个字符！")
    @ApiModelProperty(value="更新内容",name="remark",example = "1")
    private String remark;

    @Min(value = -1000000000, message = "上传类型值不能小于-1000000000！")
    @Max(value = 1000000000, message = "上传类型值不能大于1000000000！")
    @ApiModelProperty(value="上传类型",name="uploadType",example = "1")
    private Integer uploadType;

    @Size(max = 100, message = "windows更新包路径最多100个字符！")
    @ApiModelProperty(value="windows更新包路径",name="winPath",example = "1")
    private String winPath;

    @Size(max = 100, message = "mac更新包路径最多100个字符！")
    @ApiModelProperty(value="mac更新包路径",name="macPath",example = "1")
    private String macPath;

	@Size(max = 1, message = "当前版本标识 1是0否最多1个字符！")
    @ApiModelProperty(value="当前版本标识 1是0否",name="currentVersion",example = "1")
    private String currentVersion;

    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;

	public VersionsQueryIo() {
		super();
	}

    public VersionsQueryIo(String verson, String remark, Integer uploadType, String winPath, String macPath, String currentVersion, Date createTime, Date updateTime) {
        this.verson = verson;
        this.remark = remark;
        this.uploadType = uploadType;
        this.winPath = winPath;
        this.macPath = macPath;
        this.currentVersion = currentVersion;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
        return "VersionsQueryIo{" +
                "verson='" + verson + '\'' +
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