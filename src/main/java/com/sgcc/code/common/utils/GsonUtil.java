package com.sgcc.code.common.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.springframework.cglib.beans.BeanMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GsonUtil {

	public static final Gson gson = new GsonBuilder().
			setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES) //驼峰转换
			.disableHtmlEscaping() // 特殊字符转义
			.setDateFormat("yyyy-MM-dd")
			.create();

	private GsonUtil(){}
	
	public static Gson getGson(){
		return gson;
	}

	/**
	 * 将对象转成json格式
	 *
	 * @param object
	 * @return String
	 */
	public static String beanToJson(Object object) {
		String gsonString = null;
		try {
			gsonString = gson.toJson(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gsonString;
	}

	/**
	 * 将map装换为javabean对象
	 *
	 * @param map
	 * @param bean
	 * @return
	 */
	public static <T> T mapToBean(Map<String, Object> map, T bean) {
		BeanMap beanMap = BeanMap.create(bean);
		beanMap.putAll(map);
		return bean;
	}

	/**
	 * 将json转成特定的cls的对象
	 *
	 * @param json
	 * @param cls
	 * @return
	 */
	public static <T> T jsonToBean(String json, Class<T> cls) {
		T t = null;
		try {
			//传入json对象和对象类型,将json转成对象
			t = gson.fromJson(json, cls);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return t;
	}

	/**
	 * 将List<map>转成list
	 *
	 * @param source
	 * @param cls
	 * @return
	 */
	public static <T> List<T> gsonToList(List<Map<String,String>> source, Class<T> cls) {
		List<T> list = new ArrayList<>();
		for (Map<String,String> map : source) {
			String jsonstr = gson.toJson(map);
			list.add(gson.fromJson(jsonstr,cls));
		}
		return list;
	}

	/**
	 * json字符串转成list
	 * 解决泛型问题
	 * 备注：
	 * List<T> list=gson.fromJson(gsonString, new TypeToken<List<T>>() {}.getType());
	 * 该方法会报泛型类型擦除问题
	 *
	 * @param json
	 * @param cls
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> stringToList(String json, Class<T> cls) {
		List<T> list = new ArrayList<T>();
		try {
			list = gson.fromJson(json,
					new TypeToken<List<T>>() {
					}.getType());

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

}
