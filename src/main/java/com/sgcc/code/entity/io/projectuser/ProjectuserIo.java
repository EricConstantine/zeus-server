package com.sgcc.code.entity.io.projectuser;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目成员
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
@ApiModel(value="项目成员默认返回参数类",description="项目成员接口默认返回类")
public class ProjectuserIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;
    
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
    @ApiModelProperty(value="人员状态 1启用0禁用",name="status",example = "1")
    private String status;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    

	public ProjectuserIo() {
		super();
	}

	public ProjectuserIo(Long id,String projectid,String userid,String status,Date createTime) {
		super();
		this.id = id;
		this.projectid = projectid;
		this.userid = userid;
		this.status = status;
		this.createTime = createTime;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectid() {
        return this.projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }


	@Override
    public String toString() {
        return "ProjectuserIo{" +
    	" id=" + id +
    	", projectid=" + projectid +
    	", userid=" + userid +
    	", status=" + status +
    	", createTime=" + createTime +
        "}";
    }

}