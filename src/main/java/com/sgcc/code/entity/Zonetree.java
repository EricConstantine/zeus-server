package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * <p>
 * 空间树
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
public class Zonetree implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 父级id
     */
    private Long pid;
    
    /**
     * 项目id
     */
    private String projectid;
    
    /**
     * 当前层级名称
     */
    private String name;
    
    /**
     * 类型 1文件夹 2文件
     */
    private String type;
    
    /**
     * 文件路径
     */
    private String path;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    
    /**
     * 更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp updateTime;

    /**
     * 扩展字段
     */
    @TableField(exist = false)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<Zonetree> child;
    

	public Zonetree() {
		super();
	}

	public Zonetree(Long id,Long pid,String projectid,String name,String type,String path,Timestamp createTime,Timestamp updateTime) {
		super();
		this.id = id;
		this.pid = pid;
		this.projectid = projectid;
		this.name = name;
		this.type = type;
		this.path = path;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return this.pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getProjectid() {
        return this.projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPath() {
        return this.path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }

    public List<Zonetree> getChild() {
        return child;
    }

    public void setChild(List<Zonetree> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "Zonetree{" +
	    " id=" + id +
	    ", pid=" + pid +
	    ", projectid=" + projectid +
	    ", name=" + name +
	    ", type=" + type +
	    ", path=" + path +
	    ", createTime=" + createTime +
	    ", updateTime=" + updateTime +
        "}";
    }

}