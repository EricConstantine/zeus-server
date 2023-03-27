package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;

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
public class CommonInfos implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 项目id
     */
    private Integer projectid;

    /**
     * 公共配置信息名称
     */
    private String name;
    
    /**
     * 公共配置信息描述
     */
    private String describes;
    
    /**
     * 公共配置信息内容
     */
    private String content;
    
    /**
     * 创建时间
     */
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;


    //扩展字段
    @TableField(exist = false)
    private List<Map> contents;
    

	public CommonInfos() {
		super();
	}

    public CommonInfos(Long id, Integer projectid, String name, String describes, String content, Date createTime) {
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
        return "CommonInfos{" +
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