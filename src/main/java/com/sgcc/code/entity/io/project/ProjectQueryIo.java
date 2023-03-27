package com.sgcc.code.entity.io.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
@ApiModel(value="项目查询参数类",description="查询项目请求体")
public class ProjectQueryIo implements Serializable {

	private static final long serialVersionUID = 1L;

	@Size(max = 100, message = "项目名称最多100个字符！")
    @ApiModelProperty(value="项目名称",name="projectName",example = "1")
    private String projectName;

	@Size(max = 200, message = "项目描述最多200个字符！")
    @ApiModelProperty(value="项目描述",name="projectDescribe",example = "1")
    private String projectDescribe;

	@Size(max = 1, message = "是否公开 1是 0否最多1个字符！")
    @ApiModelProperty(value="是否公开 1是 0否",name="ispublic",example = "1")
    private String ispublic;

    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    //扩展字段
    @ApiModelProperty(value="时间区间",name="timeInterval",example = "1")
    private List<String> timeInterval;

	public ProjectQueryIo() {
		super();
	}

	public ProjectQueryIo(String projectName,String projectDescribe,String ispublic,Date createTime) {
		super();
		this.projectName = projectName;
		this.projectDescribe = projectDescribe;
		this.ispublic = ispublic;
		this.createTime = createTime;
	}

    public String getProjectName() {
        return this.projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescribe() {
        return this.projectDescribe;
    }

    public void setProjectDescribe(String projectDescribe) {
        this.projectDescribe = projectDescribe;
    }

    public String getIspublic() {
        return this.ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<String> getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(List<String> timeInterval) {
        this.timeInterval = timeInterval;
    }

    @Override
    public String toString() {
        return "ProjectQueryIo{" +
    	" projectName=" + projectName +
    	", projectDescribe=" + projectDescribe +
    	", ispublic=" + ispublic +
    	", createTime=" + createTime +
        "}";
    }

}