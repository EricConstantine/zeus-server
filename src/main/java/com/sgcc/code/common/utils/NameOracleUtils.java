package com.sgcc.code.common.utils;

import com.sgcc.code.entity.ColumnClass;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * @Author mww
 * @Date 2023/03/20
 * @Description Oracle数据库字段名转换工具类
 */
public class NameOracleUtils {


	public static String getJavaDataType(String dataType, String scale){
		switch (dataType){
			case "VARCHAR2":
				dataType = dataType.replace("VARCHAR2", "String");
				break;
			case "CHAR":
				dataType = dataType.replace("CHAR", "String");
				break;
			case "NVARCHAR2":
				dataType = dataType.replace("NVARCHAR2", "String");
				break;
			case "DATE":
				dataType = dataType.replace("DATE", "DATE");
				break;
			case "NUMBER":
				if(numberParse(scale) > 0){
					dataType = dataType.replace("NUMBER", "BigDecimal");
				}else{
					dataType = dataType.replace("NUMBER", "Integer");
				}
				break;
			case "FLOAT":
				dataType = dataType.replace("FLOAT", "BigDecimal");
				break;
			case "BINARY_DOUBLE":
				dataType = dataType.replace("BINARY_DOUBLE", "BigDecimal");
				break;
			default:
				break;
		}
		return dataType;
	}

	public static String getXmlDataType(String dataType, String scale){
		switch (dataType){
			case "VARCHAR2":
				dataType = dataType.replace("VARCHAR2", "VARCHAR");
				break;
			case "CHAR":
				dataType = dataType.replace("CHAR", "VARCHAR");
				break;
			case "NVARCHAR2":
				dataType = dataType.replace("NVARCHAR2", "VARCHAR");
				break;
			case "DATE":
				dataType = dataType.replace("DATE", "DATE");
				break;

			case "NUMBER":
				if(numberParse(scale) > 0){
					dataType = dataType.replace("NUMBER", "DECIMAL");
				}else{
					dataType = dataType.replace("NUMBER", "INTEGER");
				}
				break;
			case "datFLOATetime":
				dataType = dataType.replace("FLOAT", "DECIMAL");
				break;
			case "BINARY_DOUBLE":
				dataType = dataType.replace("BINARY_DOUBLE", "DECIMAL");
				break;
			default:
				break;
		}
		return dataType;
	}

	public static String getTableDataType(String str){
//		str = str.replace("\"integer\"", "\"int\"");
		str = str.replace("\"decimal\"", "\"decimal(6,2)\"");
//		str = str.replace("\"integer\"", "\"int\"");
		return str;
	}

	//获取数据库名称对应的驼峰名
	public static String getTableDataTFName(String str){
		str = str.toLowerCase();
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
		str = str.toLowerCase();
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
				String dataType = columnMap.get("dataType");
				columnClass.setRealType(dataType);

				//获取字段长度
				String columnLen = replace(String.valueOf(columnMap.get("columnLen")));
				if(StringUtils.isNotBlank(columnLen)){
					columnClass.setFiledLen(columnLen);
				}
				//获取字段精度
				String scale = replace(String.valueOf(columnMap.get("scale")));
				columnClass.setScale(scale);

				//转换字段名称，如 sys_name 变成 SysName
				columnClass.setFILEDNAME(replaceUnderLineAndUpperCase(columnMap.get("columnName")));
				//转换字段名称，如 sys_name 变成 sysName //首字母小写
				columnClass.setFiledName(replaceUnderLine(columnMap.get("columnName")));
				//字段在前端展示时的展示类型
				columnClass.setIsKey(columnMap.get("isKey").equals("Y")?"true":"false");
				//把字段中文名称放进对象里面
				columnClass.setFiledDesc(columnMap.get("commentName"));
				//生成额外字段
				columnClass.setJavaType(getJavaDataType(dataType,scale));
				columnClass.setXmlType(getXmlDataType(dataType,scale));
				columnClassList.add(columnClass);
			}
		}
		return columnClassList;
	}

	/**
	 * 使用java正则表达式去掉多余的.与0
	 * @param s
	 * @return  string
	 */
	public static String replace(String s){
		if(null != s && s.indexOf(".") > 0){
			s = s.replaceAll("0+?$", "");//去掉多余的0
			s = s.replaceAll("[.]$", "");//如最后一位是.则去掉
		}
		return s;
	}

	/**
	 * 整数转换
	 * @param str
	 * @return
	 */
	public static Integer numberParse(String str){
		if(str.matches("^[0-9]*$") && (Integer.parseInt(str) > 0)){
			return Integer.parseInt(str);
		}else{
			return 0;
		}
	}

	public static void main(String[] args) {
		String str = "code_table";
		String tableDataTFName = NameOracleUtils.replaceUnderLineAndUpperCase(str);
		System.out.println(tableDataTFName);
	}

}
