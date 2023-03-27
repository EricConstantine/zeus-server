package com.sgcc.code.entity.io.project;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableField;
import com.sgcc.code.entity.Dictionary;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
@ApiModel(value="项目默认返回参数类",description="项目接口默认返回类")
public class ProjectIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="项目id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="项目名称",name="projectName",example = "1")
    private String projectName;
    
    @ApiModelProperty(value="项目描述",name="projectDescribe",example = "1")
    private String projectDescribe;
    
    @ApiModelProperty(value="是否公开 1是 0否",name="ispublic",example = "1")
    private String ispublic;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;

    // 扩展字段
    @ApiModelProperty(value="字典集合",name="dictionaryList",example = "1")
    private List<Dictionary> dictionaryList;

    @ApiModelProperty(value="项目成员集合",name="userList",example = "1")
    private List<String> userList;
    

	public ProjectIo() {
		super();
	}

	public ProjectIo(Long id,String projectName,String projectDescribe,String ispublic,Timestamp createTime) {
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

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
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
        return "ProjectIo{" +
    	" id=" + id +
    	", projectName=" + projectName +
    	", projectDescribe=" + projectDescribe +
    	", ispublic=" + ispublic +
    	", createTime=" + createTime +
        "}";
    }

}