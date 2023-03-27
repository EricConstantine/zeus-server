package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目成员
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 03 10:07:47 CST 2023
 */
public class Projectuser implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 项目id
     */
    private String projectid;
    
    /**
     * 用户id
     */
    private String userid;
    
    /**
     * 人员状态 1启用0禁用
     */
    private String status;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    

	public Projectuser() {
		super();
	}

	public Projectuser(Long id,String projectid,String userid,String status,Date createTime) {
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
        return "Projectuser{" +
	    " id=" + id +
	    ", projectid=" + projectid +
	    ", userid=" + userid +
	    ", status=" + status +
	    ", createTime=" + createTime +
        "}";
    }

}