package com.sgcc.code.entity.io.todo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 待办
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
@ApiModel(value="待办默认返回参数类",description="待办接口默认返回类")
public class TodoIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
    @ApiModelProperty(value="代办标题",name="title",example = "1")
    private String title;
    
    @ApiModelProperty(value="代办内容",name="content",example = "1")
    private String content;
    
    @ApiModelProperty(value="代办状态 0未完成 1已完成",name="status",example = "1")
    private Integer status;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    

	public TodoIo() {
		super();
	}

	public TodoIo(Long id,String userid,String title,String content,Integer status,Date createTime) {
		super();
		this.id = id;
		this.userid = userid;
		this.title = title;
		this.content = content;
		this.status = status;
		this.createTime = createTime;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserid() {
        return this.userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getStatus() {
        return this.status;
    }

    public void setStatus(Integer status) {
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
        return "TodoIo{" +
    	" id=" + id +
    	", userid=" + userid +
    	", title=" + title +
    	", content=" + content +
    	", status=" + status +
    	", createTime=" + createTime +
        "}";
    }

}