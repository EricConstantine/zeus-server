package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class WebLibrary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    
    /**
     * 项目ID
     */
    private Integer projectId;
    
    /**
     * 是否公开
     */
    private String ispublic;
    
    /**
     * 网页库标题
     */
    private String webTitle;
    
    /**
     * 网页库描述
     */
    private String webDescribe;
    
    /**
     * 网页库内容
     */
    private String webContent;
    
    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Timestamp createTime;
    

	public WebLibrary() {
		super();
	}

	public WebLibrary(Integer id,Integer projectId,String ispublic,String webTitle,String webDescribe,String webContent,Timestamp createTime) {
		super();
		this.id = id;
		this.projectId = projectId;
		this.ispublic = ispublic;
		this.webTitle = webTitle;
		this.webDescribe = webDescribe;
		this.webContent = webContent;
		this.createTime = createTime;
	}

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "WebLibrary{" +
	    " id=" + id +
	    ", projectId=" + projectId +
	    ", ispublic=" + ispublic +
	    ", webTitle=" + webTitle +
	    ", webDescribe=" + webDescribe +
	    ", webContent=" + webContent +
	    ", createTime=" + createTime +
        "}";
    }

}