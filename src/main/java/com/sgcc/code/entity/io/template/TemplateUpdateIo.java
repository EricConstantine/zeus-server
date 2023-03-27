package com.sgcc.code.entity.io.template;

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
 * 模板
 * </p>
 *
 * @author liugohua
 * @since Fri Dec 30 17:52:56 CST 2022
 */
@ApiModel(value="模板修改参数类",description="修改模板请求体")
public class TemplateUpdateIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "主键id不能为空!")
	@Min(value = -1000000000, message = "主键id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "主键id值不能大于1000000000！")
    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
	//@NotNull(message = "空间树id不能为空!")
	@Size(max = 32, message = "空间树id最多32个字符！")
    @ApiModelProperty(value="空间树id",name="treeid",example = "1")
    private String treeid;
    
	//@NotNull(message = "模板名称不能为空!")
	@Size(max = 100, message = "模板名称最多100个字符！")
    @ApiModelProperty(value="模板名称",name="templateName",example = "1")
    private String templateName;
    
	//@NotNull(message = "模板内容不能为空!")
	@Size(max = 65535, message = "模板内容最多65535个字符！")
    @ApiModelProperty(value="模板内容",name="templateContent",example = "1")
    private String templateContent;
    
	//@NotNull(message = "备注不能为空!")
	@Size(max = 200, message = "备注最多200个字符！")
    @ApiModelProperty(value="备注",name="remark",example = "1")
    private String remark;
    
	//@NotNull(message = "创建时间不能为空!")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	//@NotNull(message = "更新时间不能为空!")
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    
	public TemplateUpdateIo() {
		super();
	}

	public TemplateUpdateIo(Long id,String treeid,String templateName,String templateContent,String remark,Date createTime,Date updateTime) {
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
        return "TemplateUpdateIo{" +
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