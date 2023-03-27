package com.sgcc.code.entity.io.todo;

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
 * 待办
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
@ApiModel(value="待办修改参数类",description="修改待办请求体")
public class TodoUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
	//@NotNull(message = "用户id不能为空!")
	@Size(max = 32, message = "用户id最多32个字符！")
    @ApiModelProperty(value="用户id",name="userid",example = "1")
    private String userid;
    
	//@NotNull(message = "代办标题不能为空!")
	@Size(max = 100, message = "代办标题最多100个字符！")
    @ApiModelProperty(value="代办标题",name="title",example = "1")
    private String title;
    
	//@NotNull(message = "代办内容不能为空!")
	@Size(max = 400, message = "代办内容最多400个字符！")
    @ApiModelProperty(value="代办内容",name="content",example = "1")
    private String content;
    
	//@NotNull(message = "代办状态 0未完成 1已完成不能为空!")
	@Min(value = -1000000000, message = "代办状态 0未完成 1已完成值不能小于-1000000000！")
	@Max(value = 1000000000, message = "代办状态 0未完成 1已完成值不能大于1000000000！")
    @ApiModelProperty(value="代办状态 0未完成 1已完成",name="status",example = "1")
    private Integer status;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	public TodoUpdateIo() {
		super();
	}

	public TodoUpdateIo(Long id,String userid,String title,String content,Integer status,Date createTime) {
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
        return "TodoUpdateIo{" +
    	" id=" + id +
    	", userid=" + userid +
    	", title=" + title +
    	", content=" + content +
    	", status=" + status +
    	", createTime=" + createTime +
        "}";
    }

}