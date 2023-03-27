package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 待办
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:45 CST 2023
 */
public class Todo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 用户id
     */
    private String userid;
    
    /**
     * 代办标题
     */
    private String title;
    
    /**
     * 代办内容
     */
    private String content;
    
    /**
     * 代办状态 0未完成 1已完成
     */
    private Integer status;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    

	public Todo() {
		super();
	}

	public Todo(Long id,String userid,String title,String content,Integer status,Date createTime) {
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
        return "Todo{" +
	    " id=" + id +
	    ", userid=" + userid +
	    ", title=" + title +
	    ", content=" + content +
	    ", status=" + status +
	    ", createTime=" + createTime +
        "}";
    }

}