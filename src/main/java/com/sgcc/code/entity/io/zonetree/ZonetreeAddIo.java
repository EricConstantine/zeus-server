package com.sgcc.code.entity.io.zonetree;

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
 * 空间树
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 09:58:13 CST 2022
 */
@ApiModel(value="新增空间树参数类",description="新增空间树请求体")
public class ZonetreeAddIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "父级id不能为空！")
	@Min(value = -1000000000, message = "父级id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "父级id值不能大于1000000000！")
    @ApiModelProperty(value="父级id",name="pid",example = "1")
    private Long pid;
    
	//@NotNull(message = "项目id不能为空！")
	@Size(max = 32, message = "项目id最多32个字符！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;
    
	//@NotNull(message = "当前层级名称不能为空！")
	@Size(max = 32, message = "当前层级名称最多32个字符！")
    @ApiModelProperty(value="当前层级名称",name="name",example = "1")
    private String name;
    
	//@NotNull(message = "类型 1文件夹 2文件不能为空！")
	@Size(max = 1, message = "类型 1文件夹 2文件最多1个字符！")
    @ApiModelProperty(value="类型 1文件夹 2文件",name="type",example = "1")
    private String type;
    
	//@NotNull(message = "文件路径不能为空！")
	@Size(max = 100, message = "文件路径最多100个字符！")
    @ApiModelProperty(value="文件路径",name="path",example = "1")
    private String path;
    
	//@NotNull(message = "创建时间不能为空！")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	//@NotNull(message = "更新时间不能为空！")
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    
	public ZonetreeAddIo() {
		super();
	}

	public ZonetreeAddIo(Long pid,String projectid,String name,String type,String path,Date createTime,Date updateTime) {
		super();
		this.pid = pid;
		this.projectid = projectid;
		this.name = name;
		this.type = type;
		this.path = path;
		this.createTime = createTime;
		this.updateTime = updateTime;
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

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return this.updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }


	@Override
    public String toString() {
        return "ZonetreeAddIo{" +
    	" pid=" + pid +
    	", projectid=" + projectid +
    	", name=" + name +
    	", type=" + type +
    	", path=" + path +
    	", createTime=" + createTime +
    	", updateTime=" + updateTime +
        "}";
    }

}