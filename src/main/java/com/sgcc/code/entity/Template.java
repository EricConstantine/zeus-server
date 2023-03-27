package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 模板
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
public class Template implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 空间树id
     */
    private String treeid;
    
    /**
     * 模板名称
     */
    private String templateName;
    
    /**
     * 模板内容
     */
    private String templateContent;
    
    /**
     * 备注
     */
    private String remark;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
    /**
     * 更新时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    

	public Template() {
		super();
	}

	public Template(Long id,String treeid,String templateName,String templateContent,String remark,Date createTime,Date updateTime) {
		super();
		this.id = id;
		this.treeid = treeid;
		this.templateName = templateName;
		this.templateContent = templateContent;
		this.remark = remark;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTreeid() {
        return this.treeid;
    }

    public void setTreeid(String treeid) {
        this.treeid = treeid;
    }

    public String getTemplateName() {
        return this.templateName;
    }

    public void setTemplateName(String templateName) {
        this.templateName = templateName;
    }

    public String getTemplateContent() {
        return this.templateContent;
    }

    public void setTemplateContent(String templateContent) {
        this.templateContent = templateContent;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
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
        return "Template{" +
	    " id=" + id +
	    ", treeid=" + treeid +
	    ", templateName=" + templateName +
	    ", templateContent=" + templateContent +
	    ", remark=" + remark +
	    ", createTime=" + createTime +
	    ", updateTime=" + updateTime +
        "}";
    }

}