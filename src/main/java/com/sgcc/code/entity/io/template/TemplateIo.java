package com.sgcc.code.entity.io.template;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 模板
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
@ApiModel(value="模板默认返回参数类",description="模板接口默认返回类")
public class TemplateIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="空间树id",name="treeid",example = "1")
    private String treeid;
    
    @ApiModelProperty(value="模板名称",name="templateName",example = "1")
    private String templateName;
    
    @ApiModelProperty(value="模板内容",name="templateContent",example = "1")
    private String templateContent;
    
    @ApiModelProperty(value="备注",name="remark",example = "1")
    private String remark;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;

    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;
    

	public TemplateIo() {
		super();
	}

	public TemplateIo(Long id,String treeid,String templateName,String templateContent,String remark,Date createTime,Date updateTime) {
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

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    @Override
    public String toString() {
        return "TemplateIo{" +
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