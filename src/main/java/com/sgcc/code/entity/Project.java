package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目
 * </p>
 *
 * @author yangrui
 * @since Thu Dec 29 17:20:21 CST 2022
 */
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 项目名称
     */
    private String projectName;
    
    /**
     * 项目描述
     */
    private String projectDescribe;
    
    /**
     * 是否公开 1是 0否
     */
    private String ispublic;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;

    // 扩展字段
    /**
     * 字典集合
     */
    @TableField(exist = false)
    private List<Dictionary> dictionaryList;

    /**
     * 项目成员集合
     */
    @TableField(exist = false)
    private List<String> userList;


    public Project() {
		super();
	}

	public Project(Long id,String projectName,String projectDescribe,String ispublic,Timestamp createTime) {
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
        return "Project{" +
	    " id=" + id +
	    ", projectName=" + projectName +
	    ", projectDescribe=" + projectDescribe +
	    ", ispublic=" + ispublic +
	    ", createTime=" + createTime +
        "}";
    }

}