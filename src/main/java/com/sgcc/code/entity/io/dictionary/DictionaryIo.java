package com.sgcc.code.entity.io.dictionary;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
@ApiModel(value="字典默认返回参数类",description="字典接口默认返回类")
public class DictionaryIo implements Serializable {

	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value="主键id",name="id",example = "1")
    private Long id;
    
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;

    @ApiModelProperty(value="字典类型",name="types",example = "1")
    private Integer types;

    @ApiModelProperty(value="数组内容",name="listContent",example = "1")
    private String listContent;
    
    @ApiModelProperty(value="字典key",name="dictionaryKey",example = "1")
    private String dictionaryKey;
    
    @ApiModelProperty(value="字典value",name="dictionaryValue",example = "1")
    private String dictionaryValue;
    
    @ApiModelProperty(value="描述",name="dictionaryDescribe",example = "1")
    private String dictionaryDescribe;
    
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    

	public DictionaryIo() {
		super();
	}

    public DictionaryIo(Long id, String projectid, Integer types, String listContent, String dictionaryKey, String dictionaryValue, String dictionaryDescribe, Date createTime, Date updateTime) {
        this.id = id;
        this.projectid = projectid;
        this.types = types;
        this.listContent = listContent;
        this.dictionaryKey = dictionaryKey;
        this.dictionaryValue = dictionaryValue;
        this.dictionaryDescribe = dictionaryDescribe;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectid() {
        return projectid;
    }

    public void setProjectid(String projectid) {
        this.projectid = projectid;
    }

    public Integer getTypes() {
        return types;
    }

    public void setTypes(Integer types) {
        this.types = types;
    }

    public String getListContent() {
        return listContent;
    }

    public void setListContent(String listContent) {
        this.listContent = listContent;
    }

    public String getDictionaryKey() {
        return dictionaryKey;
    }

    public void setDictionaryKey(String dictionaryKey) {
        this.dictionaryKey = dictionaryKey;
    }

    public String getDictionaryValue() {
        return dictionaryValue;
    }

    public void setDictionaryValue(String dictionaryValue) {
        this.dictionaryValue = dictionaryValue;
    }

    public String getDictionaryDescribe() {
        return dictionaryDescribe;
    }

    public void setDictionaryDescribe(String dictionaryDescribe) {
        this.dictionaryDescribe = dictionaryDescribe;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "DictionaryIo{" +
                "id=" + id +
                ", projectid='" + projectid + '\'' +
                ", types=" + types +
                ", listContent='" + listContent + '\'' +
                ", dictionaryKey='" + dictionaryKey + '\'' +
                ", dictionaryValue='" + dictionaryValue + '\'' +
                ", dictionaryDescribe='" + dictionaryDescribe + '\'' +
                ", createTime=" + createTime +
                ", updateTime=" + updateTime +
                '}';
    }
}