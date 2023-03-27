package com.sgcc.code.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * <p>
 * 字典
 * </p>
 *
 * @author liugohua
 * @since Wed Jan 04 12:23:13 CST 2023
 */
public class Dictionary implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@TableId(value = "id", type = IdType.AUTO)
    private Long id;
    
    /**
     * 项目id
     */
    private String projectid;

    /**
     * 字典类型 1 字符串 2数组 3字段属性列表
     */
    private Integer types;

    /**
     * 数组内容
     */
    private String listContent;

    /**
     * 字典key
     */
    private String dictionaryKey;
    
    /**
     * 字典value
     */
    private String dictionaryValue;
    
    /**
     * 描述
     */
    private String dictionaryDescribe;
    
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
    

	public Dictionary() {
		super();
	}

    public Dictionary(Long id, String projectid, Integer types, String listContent, String dictionaryKey, String dictionaryValue, String dictionaryDescribe, Date createTime, Date updateTime) {
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
        return "Dictionary{" +
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