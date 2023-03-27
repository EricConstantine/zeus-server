package com.sgcc.code.entity.io.projectuser;

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
 * 项目成员
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
@ApiModel(value="新增项目成员参数类",description="新增项目成员请求体")
public class ProjectuserAddIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目id不能为空！")
	@Size(max = 32, message = "项目id最多32个字符！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;
    
	//@NotNull(message = "用户id不能为空！")
	@Size(max = 32, message = "用户id最多32个字符！")
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
	//@NotNull(message = "人员状态 1启用0禁用不能为空！")
	@Size(max = 1, message = "人员状态 1启用0禁用最多1个字符！")
    @ApiModelProperty(value="人员状态 1启用0禁用",name="status",example = "1")
    private String status;
    
	//@NotNull(message = "创建时间不能为空！")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	public ProjectuserAddIo() {
		super();
	}

	public ProjectuserAddIo(String projectid,String userid,String status,Date createTime) {
		super();
		this.projectid = projectid;
		this.userid = userid;
		this.status = status;
		this.createTime = createTime;
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
        return "ProjectuserAddIo{" +
    	" projectid=" + projectid +
    	", userid=" + userid +
    	", status=" + status +
    	", createTime=" + createTime +
        "}";
    }

}