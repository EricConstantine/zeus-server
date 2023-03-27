package com.sgcc.code.entity;

import java.io.Serializable;

/**
 * <p>
 * 数据库字段封装类
 * </p>
 *
 * @author liugohua
 * @since Tue Jan 10 09:42:21 CST 2023
 */
public class ColumnClass implements Serializable {

	/** 数据库字段名称 **/
	private String filed_name;
	/** 数据库字段名称（小驼峰） **/
	private String filedName;
	/** 数据库字段名称（大驼峰） **/
	private String FILEDNAME;
	/** 数据库字段中文名 **/
	private String filedDesc;
	/** 是否为数据库主键 **/
	private String isKey;
	/** 数据库字段长度 **/
	private String filedLen;
	/** 数据库真实类型 **/
	private String realType;
	/** 字段Java类型 **/
	private String javaType;
	/** 字段Xml类型 **/
	private String xmlType;
	/** 小数点位数 **/
	private String scale;

	public ColumnClass() {
		super();
	}

	public ColumnClass(String filed_name, String filedName, String FILEDNAME, String filedDesc, String isKey, String filedLen, String realType, String javaType, String xmlType) {
		this.filed_name = filed_name;
		this.filedName = filedName;
		this.FILEDNAME = FILEDNAME;
		this.filedDesc = filedDesc;
		this.isKey = isKey;
		this.filedLen = filedLen;
		this.realType = realType;
		this.javaType = javaType;
		this.xmlType = xmlType;
	}

	public String getFiled_name() {
		return filed_name;
	}

	public void setFiled_name(String filed_name) {
		this.filed_name = filed_name;
	}

	public String getFiledName() {
		return filedName;
	}

	public void setFiledName(String filedName) {
		this.filedName = filedName;
	}

	public String getFILEDNAME() {
		return FILEDNAME;
	}

	public void setFILEDNAME(String FILEDNAME) {
		this.FILEDNAME = FILEDNAME;
	}

	public String getFiledDesc() {
		return filedDesc;
	}

	public void setFiledDesc(String filedDesc) {
		this.filedDesc = filedDesc;
	}

	public String getIsKey() {
		return isKey;
	}

	public void setIsKey(String isKey) {
		this.isKey = isKey;
	}

	public String getFiledLen() {
		return filedLen;
	}

	public void setFiledLen(String filedLen) {
		this.filedLen = filedLen;
	}

	public String getRealType() {
		return realType;
	}

	public void setRealType(String realType) {
		this.realType = realType;
	}

	public String getJavaType() {
		return javaType;
	}

	public void setJavaType(String javaType) {
		this.javaType = javaType;
	}

	public String getXmlType() {
		return xmlType;
	}

	public void setXmlType(String xmlType) {
		this.xmlType = xmlType;
	}


	public String getScale() {
		return scale;
	}

	public void setScale(String scale) {
		this.scale = scale;
	}

	@Override
	public String toString() {
		return "ColumnClass{" +
				"filed_name='" + filed_name + '\'' +
				", filedName='" + filedName + '\'' +
				", FILEDNAME='" + FILEDNAME + '\'' +
				", filedDesc='" + filedDesc + '\'' +
				", isKey='" + isKey + '\'' +
				", filedLen='" + filedLen + '\'' +
				", realType='" + realType + '\'' +
				", javaType='" + javaType + '\'' +
				", xmlType='" + xmlType + '\'' +
				'}';
	}
}
