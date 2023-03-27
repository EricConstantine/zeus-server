package com.sgcc.code.common.utils;

import com.sgcc.code.entity.ColumnClass;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NameMysqlUtils {

	public static String getName(String serverName) {
		if(StringUtils.isEmpty(serverName)) {
			return "null";
		}
		if(serverName.startsWith("ms-base-")) {
			return serverName.replace("ms-base-", "");
		}
		if(serverName.startsWith("ma-base-")) {
			return serverName.replace("ma-base-", "");
		}
		if(serverName.startsWith("ms-")) {
			return serverName.replace("ms-", "");
		}
		if(serverName.startsWith("ma-")) {
			return serverName.replace("ma-", "");
		}
		//若serverName是xxx-xxx-xxx-xxx 总返回最后一个xxx
		String[] arr = serverName.split("-");
		if(arr.length>2) {
			return arr[arr.length-1];
		}
		return "else";
	}

	public static String getJavaDataType(String str){
		switch (str){
			case "varchar":
				str = str.replace("varchar", "String");
				break;
			case "double":
				str = str.replace("double", "Double");
				break;
			case "char":
				str = str.replace("char", "String");
				break;
			case "text":
				str = str.replace("text", "String");
				break;
			case "int":
				str = str.replace("int", "Integer");//int
				break;
			case "tinyint":
				str = str.replace("tinyint", "Integer");//int
				break;
			case "bigint":
				str = str.replace("bigint", "Long");//long
				break;
			case "numeric":
				str = str.replace("numeric", "Double");//double
				break;
			case "decimal":
				str = str.replace("decimal", "Float");//float
				break;
			case "date":
				str = str.replace("date", "Date");
				break;
			case "datetime":
				str = str.replace("datetime", "Timestamp");
				break;
			case "timestamp":
				str = str.replace("timestamp", "Timestamp");
				break;
			default:
				break;
		}
		return str;
	}

	public static String getXmlDataType(String str){
		switch (str){
			case "varchar":
				str = str.replace("varchar", "VARCHAR");
				break;
			case "double":
				str = str.replace("double", "DOUBLE");
				break;
			case "char":
				str = str.replace("char", "VARCHAR");
				break;
			case "text":
				str = str.replace("text", "LONGVARCHAR");
				break;
			case "int":
				str = str.replace("int", "INTEGER");
				break;
			case "tinyint":
				str = str.replace("tinyint", "TINYINT");
				break;
			case "bigint":
				str = str.replace("bigint", "BIGINT");
				break;
			case "numeric":
				str = str.replace("numeric", "DOUBLE");
				break;
			case "decimal":
				str = str.replace("decimal", "FLOAT");
				break;
			case "date":
				str = str.replace("date", "DATE");
				break;
			case "datetime":
				str = str.replace("datetime", "TIMESTAMP");
				break;
			case "timestamp":
				str = str.replace("timestamp", "TIMESTAMP");
				break;
			default:
				break;
		}
		return str;
	}

	public static String getTableDataType(String str){
//		str = str.replace("\"integer\"", "\"int\"");
		str = str.replace("\"decimal\"", "\"decimal(6,2)\"");
//		str = str.replace("\"integer\"", "\"int\"");
		return str;
	}

	//获取数据库名称对应的驼峰名
	public static String getTableDataTFName(String str){
		String[] arr = str.split("_");
		String result = arr[0];
		for(int i=1;i<arr.length;i++){
			String first_char = arr[i].substring(0,1);
			String else_char = arr[i].substring(1);
			result += first_char.toUpperCase()+else_char;
		}
		return result;
	}

	//用匿名内部类的方式把数据库类型转换成Java类型
	@SuppressWarnings("serial")
	public static Map<String,String> map = new HashMap<String,String>(){{
		put("varchar","String");
		put("char","String");
		put("blob","Byte[]");
		put("text","String");

		put("integer","Integer");
		put("tinyint","Integer");
		put("smallint","Integer");
		put("int","Integer");
		put("mediumint","Integer");
		put("bit","Boolean");
		put("bigint","Long");
		put("float","Float");
		put("double","Double");
		put("decimal","Bigdecimal");
		put("boolean","Integer");

		put("id","Long");
		put("date","Date");
		put("time","Time");
		put("datetime","Timestamp");
		put("timestamp","Timestamp");
		put("year","Date");
	}};

	//把数据库表名转换成实体类的名称 首字母小写
	public static String replaceUnderLine(String str){
		StringBuffer sb = new StringBuffer();
		sb.append(str);
		int count = sb.indexOf("_");
		while(count!=0){
			int num = sb.indexOf("_",count);
			count = num + 1;
			if(num != -1){
				char ss = sb.charAt(count);
				char ia = (char) (ss - 32);
				sb.replace(count , count + 1,ia + "");
			}
		}
		String result = sb.toString().replaceAll("_","");
		return result;
	}

	//把数据库表名转换成实体类的名称 首字母大写
	public static String replaceUnderLineAndUpperCase(String str){
		String result = replaceUnderLine(str);
		return org.springframework.util.StringUtils.capitalize(result);
	}

	//转换字段类型为不带括号的
	public static String subStr(String str){
		if(str.contains("(")){
			str = str.substring(0, str.indexOf("("));
		}
		return str;
	}

	//转换List<Map>为List<Class>
	public static List<ColumnClass> convertMapToClassOfList(List<Map<String,String>> columnList){
		List<ColumnClass> columnClassList = new ArrayList<>();
		ColumnClass columnClass = null;
		if(columnList!=null && columnList.size()>0){
			for (Map<String,String> columnMap : columnList) {
				columnClass = new ColumnClass();
				//把字段名称放进对象里面
				columnClass.setFiled_name(columnMap.get("columnName"));
				//获取字段类型
				columnClass.setRealType(columnMap.get("dataType"));
				//获取字段长度
				if(StringUtils.isNotBlank(columnMap.get("columnLen"))) columnClass.setFiledLen(columnMap.get("columnLen"));
				//转换字段名称，如 sys_name 变成 SysName
				columnClass.setFILEDNAME(replaceUnderLineAndUpperCase(columnMap.get("columnName")));
				//转换字段名称，如 sys_name 变成 sysName //首字母小写
				columnClass.setFiledName(replaceUnderLine(columnMap.get("columnName")));
				//字段在前端展示时的展示类型
				//columnClass.setIsKey(columnMap.get("constraintName").equals("PRIMARY KEY")?"true":"false");
				columnClass.setIsKey(columnMap.get("isKey").equals("Y")?"true":"false");
				//把字段中文名称放进对象里面
				columnClass.setFiledDesc(columnMap.get("commentName"));
				//生成额外字段
				columnClass.setJavaType(getJavaDataType(columnMap.get("dataType")));
				columnClass.setXmlType(getXmlDataType(columnMap.get("dataType")));
				columnClassList.add(columnClass);
			}
		}
		return columnClassList;
	}

	public static void main(String[] args) {
		String str = "code_table";
		String tableDataTFName = NameMysqlUtils.replaceUnderLineAndUpperCase(str);
		System.out.println(tableDataTFName);
	}

}
