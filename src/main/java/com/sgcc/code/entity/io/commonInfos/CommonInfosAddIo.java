package com.sgcc.code.entity.io.commonInfos;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 项目公共配置信息
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 31 10:16:22 CST 2023
 */
@ApiModel(value="新增项目公共配置信息参数类",description="新增项目公共配置信息请求体")
public class CommonInfosAddIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目id不能为空！")
	@Min(value = -1000000000, message = "项目id值不能小于-1000000000！")
	@Max(value = 1000000000, message = "项目id值不能大于1000000000！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private Integer projectid;

    //@NotNull(message = "公共配置信息名称不能为空！")
    @Size(max = 100, message = "公共配置信息名称最多100个字符！")
    @ApiModelProperty(value="公共配置信息名称",name="name",example = "1")
    private String name;
    
	//@NotNull(message = "公共配置信息描述不能为空！")
	@Size(max = 200, message = "公共配置信息描述最多200个字符！")
    @ApiModelProperty(value="公共配置信息描述",name="describes",example = "1")
    private String describes;
    
	//@NotNull(message = "公共配置信息内容不能为空！")
	@Size(max = 1000, message = "公共配置信息内容最多1000个字符！")
    @ApiModelProperty(value="公共配置信息内容",name="content",example = "1")
    private String content;
    
	//@NotNull(message = "创建时间不能为空！")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;

    //公共配置信息内容json格式
    private List<Map> contents;
    
	public CommonInfosAddIo() {
		super();
	}

    public CommonInfosAddIo(Integer projectid, String name, String describes, String content, Date createTime) {
        this.projectid = projectid;
        this.name = name;
        this.describes = describes;
        this.content = content;
        this.createTime = createTime;
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
        return "CommonInfosAddIo{" +
                "projectid=" + projectid +
                ", name='" + name + '\'' +
                ", describes='" + describes + '\'' +
                ", content='" + content + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}