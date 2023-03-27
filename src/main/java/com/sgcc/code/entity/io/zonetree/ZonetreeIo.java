package com.sgcc.code.entity.io.zonetree;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 空间树
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@ApiModel(value="空间树默认返回参数类",description="空间树接口默认返回类")
public class ZonetreeIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="父级id",name="pid",example = "1")
    private Long pid;
    
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;
    
    @ApiModelProperty(value="当前层级名称",name="name",example = "1")
    private String name;
    
    @ApiModelProperty(value="类型 1文件夹 2文件",name="type",example = "1")
    private String type;
    
    @ApiModelProperty(value="文件路径",name="path",example = "1")
    private String path;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp updateTime;

    /**
     * 扩展字段
     */
    private List<ZonetreeIo> child;
    

	public ZonetreeIo() {
		super();
	}

	public ZonetreeIo(Long id,Long pid,String projectid,String name,String type,String path,Timestamp createTime,Timestamp updateTime) {
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

    public List<ZonetreeIo> getChild() {
        return child;
    }

    public void setChild(List<ZonetreeIo> child) {
        this.child = child;
    }

    @Override
    public String toString() {
        return "ZonetreeIo{" +
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