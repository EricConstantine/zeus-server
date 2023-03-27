package com.sgcc.code.entity.io.dictionary;

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
 * 字典
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
@ApiModel(value="新增字典参数类",description="新增字典请求体")
public class DictionaryAddIo implements Serializable {

	private static final long serialVersionUID = 1L;

	//@NotNull(message = "项目id不能为空！")
	@Size(max = 32, message = "项目id最多32个字符！")
    @ApiModelProperty(value="项目id",name="projectid",example = "1")
    private String projectid;

    //@NotNull(message = "字典类型不能为空！")
    @Min(value = -1000000000, message = "字典类型值不能小于-1000000000！")
    @Max(value = 1000000000, message = "字典类型值不能大于1000000000！")
    @ApiModelProperty(value="字典类型",name="types",example = "1")
    private Integer types;

    //@NotNull(message = "数组内容不能为空！")
    @Size(max = 500, message = "数组内容最多500个字符！")
    @ApiModelProperty(value="数组内容",name="listContent",example = "1")
    private String listContent;
    
	//@NotNull(message = "字典key不能为空！")
	@Size(max = 100, message = "字典key最多100个字符！")
    @ApiModelProperty(value="字典key",name="dictionaryKey",example = "1")
    private String dictionaryKey;
    
	//@NotNull(message = "字典value不能为空！")
	@Size(max = 100, message = "字典value最多100个字符！")
    @ApiModelProperty(value="字典value",name="dictionaryValue",example = "1")
    private String dictionaryValue;
    
	//@NotNull(message = "描述不能为空！")
	@Size(max = 255, message = "描述最多255个字符！")
    @ApiModelProperty(value="描述",name="dictionaryDescribe",example = "1")
    private String dictionaryDescribe;
    
	//@NotNull(message = "创建时间不能为空！")
    @ApiModelProperty(value="创建时间",name="createTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date createTime;
    
	//@NotNull(message = "更新时间不能为空！")
    @ApiModelProperty(value="更新时间",name="updateTime",example = "1")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")//若需要时分秒 则设置为“yyyy-MM-dd HH:mm:ss”
    private Date updateTime;
    
	public DictionaryAddIo() {
		super();
	}

    public DictionaryAddIo(String projectid, Integer types, String listContent, String dictionaryKey, String dictionaryValue, String dictionaryDescribe, Date createTime, Date updateTime) {
        this.projectid = projectid;
        this.types = types;
        this.listContent = listContent;
        this.dictionaryKey = dictionaryKey;
        this.dictionaryValue = dictionaryValue;
        this.dictionaryDescribe = dictionaryDescribe;
        this.createTime = createTime;
        this.updateTime = updateTime;
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
        return "DictionaryAddIo{" +
                "projectid='" + projectid + '\'' +
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