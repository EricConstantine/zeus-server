package com.sgcc.code.entity.io.project;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.sgcc.code.entity.Dictionary;
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
@ApiModel(value="项目修改参数类",description="修改项目请求体")
public class ProjectUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目id不能为空!")
	@Min(value = -1000000000, message = "项目id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "项目id值不能大于1000000000！")
    @ApiModelProperty(value="项目id",name="id",example = "1")
    private Long id;
    
	//@NotNull(message = "项目名称不能为空!")
	@Size(max = 100, message = "项目名称最多100个字符！")
    @ApiModelProperty(value="项目名称",name="projectName",example = "1")
    private String projectName;
    
	//@NotNull(message = "项目描述不能为空!")
	@Size(max = 200, message = "项目描述最多200个字符！")
    @ApiModelProperty(value="项目描述",name="projectDescribe",example = "1")
    private String projectDescribe;
    
	//@NotNull(message = "是否公开 1是 0否不能为空!")
	@Size(max = 1, message = "是否公开 1是 0否最多1个字符！")
    @ApiModelProperty(value="是否公开 1是 0否",name="ispublic",example = "1")
    private String ispublic;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    //@NotNull(message = "字典集合不能为空！")
    @ApiModelProperty(value="字典集合",name="dictionaryList",example = "1")
    private List<Dictionary> dictionaryList;

    //@NotNull(message = "项目成员集合不能为空！")
    @ApiModelProperty(value="项目成员集合",name="userList",example = "1")
    private List<String> userList;
	public ProjectUpdateIo() {
		super();
	}

	public ProjectUpdateIo(Long id,String projectName,String projectDescribe,String ispublic,Date createTime) {
		super();
		this.id = id;
		this.projectName = projectName;
		this.projectDescribe = projectDescribe;
		this.ispublic = ispublic;
		this.createTime = createTime;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<Dictionary> getDictionaryList() {
        return dictionaryList;
    }

    public void setDictionaryList(List<Dictionary> dictionaryList) {
        this.dictionaryList = dictionaryList;
    }

    public List<String> getUserList() {
        return userList;
    }

    public void setUserList(List<String> userList) {
        this.userList = userList;
    }

    @Override
    public String toString() {
        return "ProjectUpdateIo{" +
    	" id=" + id +
    	", projectName=" + projectName +
    	", projectDescribe=" + projectDescribe +
    	", ispublic=" + ispublic +
    	", createTime=" + createTime +
        "}";
    }

}