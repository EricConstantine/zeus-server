package com.sgcc.code.entity.io.webLibrary;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * <p>
 * 网页库
 * </p>
 *
 * @author liugohua
 * @since Mon Mar 06 17:53:18 CST 2023
 */
@ApiModel(value="新增网页库参数类",description="新增网页库请求体")
public class WebLibraryAddIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目ID不能为空！")
	@Min(value = -1000000000, message = "项目ID值不能小于-1000000000！")
	@Max(value = 1000000000, message = "项目ID值不能大于1000000000！")
    @ApiModelProperty(value="项目ID",name="projectId",example = "1")
    private Integer projectId;
    
	//@NotNull(message = "是否公开不能为空！")
	@Size(max = 255, message = "是否公开最多255个字符！")
    @ApiModelProperty(value="是否公开",name="ispublic",example = "1")
    private String ispublic;
    
	//@NotNull(message = "网页库标题不能为空！")
	@Size(max = 255, message = "网页库标题最多255个字符！")
    @ApiModelProperty(value="网页库标题",name="webTitle",example = "1")
    private String webTitle;
    
	//@NotNull(message = "网页库描述不能为空！")
	@Size(max = 255, message = "网页库描述最多255个字符！")
    @ApiModelProperty(value="网页库描述",name="webDescribe",example = "1")
    private String webDescribe;
    
	//@NotNull(message = "网页库内容不能为空！")
	@Size(max = 255, message = "网页库内容最多255个字符！")
    @ApiModelProperty(value="网页库内容",name="webContent",example = "1")
    private String webContent;
    
	//@NotNull(message = "创建时间不能为空！")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    
	public WebLibraryAddIo() {
		super();
	}

	public WebLibraryAddIo(Integer projectId,String ispublic,String webTitle,String webDescribe,String webContent,Timestamp createTime) {
		super();
		this.projectId = projectId;
		this.ispublic = ispublic;
		this.webTitle = webTitle;
		this.webDescribe = webDescribe;
		this.webContent = webContent;
		this.createTime = createTime;
	}

    public Integer getProjectId() {
        return this.projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getIspublic() {
        return this.ispublic;
    }

    public void setIspublic(String ispublic) {
        this.ispublic = ispublic;
    }

    public String getWebTitle() {
        return this.webTitle;
    }

    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    public String getWebDescribe() {
        return this.webDescribe;
    }

    public void setWebDescribe(String webDescribe) {
        this.webDescribe = webDescribe;
    }

    public String getWebContent() {
        return this.webContent;
    }

    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }

    public Timestamp getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }


	@Override
    public String toString() {
        return "WebLibraryAddIo{" +
    	" projectId=" + projectId +
    	", ispublic=" + ispublic +
    	", webTitle=" + webTitle +
    	", webDescribe=" + webDescribe +
    	", webContent=" + webContent +
    	", createTime=" + createTime +
        "}";
    }

}