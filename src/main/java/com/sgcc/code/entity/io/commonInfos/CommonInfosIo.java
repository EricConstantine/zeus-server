package com.sgcc.code.entity.io.commonInfos;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 项目公共配置信息
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
@ApiModel(value="项目公共配置信息默认返回参数类",description="项目公共配置信息接口默认返回类")
public class CommonInfosIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private Integer projectid;

    @ApiModelProperty(value="公共配置信息名称",name="name",example = "1")
    private String name;
    
    @ApiModelProperty(value="公共配置信息描述",name="describes",example = "1")
    private String describes;
    
    @ApiModelProperty(value="公共配置信息内容",name="content",example = "1")
    private String content;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    @ApiModelProperty(value="贡藕给你配置信息内容json格式",name="contents",example = "1")
    private List<Map> contents;
    

	public CommonInfosIo() {
		super();
	}

    public CommonInfosIo(Long id, Integer projectid, String name, String describes, String content, Date createTime) {
        this.id = id;
        this.projectid = projectid;
        this.name = name;
        this.describes = describes;
        this.content = content;
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getProjectid() {
        return projectid;
    }

    public void setProjectid(Integer projectid) {
        this.projectid = projectid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescribes() {
        return describes;
    }

    public void setDescribes(String describes) {
        this.describes = describes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<Map> getContents() {
        return contents;
    }

    public void setContents(List<Map> contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        return "CommonInfosIo{" +
                "id=" + id +
                ", projectid=" + projectid +
                ", name='" + name + '\'' +
                ", describes='" + describes + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                ", contents=" + contents +
                '}';
    }
}