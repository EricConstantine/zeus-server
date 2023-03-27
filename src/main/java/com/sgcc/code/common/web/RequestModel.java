package com.sgcc.code.common.web;

import javax.validation.Valid;
import javax.validation.constraints.Min;


//请求体
public class RequestModel<T> {

	@Valid
//	请求参数
	private T parameter;

	@Min(value = 1, message = "当前页最小值为1")
//	当前页
	private int current=1;

	@Min(value = 1, message = "当前页最小值为1")
//	每页条数
	private int size=20;

//	排序字段名称
	private String column = "";

//	排序方式
	private String order = "";

	private RequestHeader header;

	public RequestModel() {}

	private RequestModel(T parameter, int current, int size,RequestHeader header) {
		this.parameter = parameter;
		this.current = current;
		this.size = size;
		this.header = header;
	}

	private RequestModel(T parameter, int current, int size) {
		this.parameter = parameter;
		this.current = current;
		this.size = size;
	}

	private RequestModel(T parameter,RequestHeader header) {
		this.parameter = parameter;
		this.header = header;
	}

	private RequestModel(T parameter) {
		this.parameter = parameter;
	}

	public static final <T> RequestModel<T> getInstance(T parameter, int current, int size,RequestHeader header){
		return new RequestModel<T>(parameter,current,size,header);
	}

	public RequestModel(@Valid T parameter, @Min(value = 1, message = "当前页最小值为1") int current, @Min(value = 1, message = "当前页最小值为1") int size, String column, String order) {
		this.parameter = parameter;
		this.current = current;
		this.size = size;
		this.column = column;
		this.order = order;
	}

	public String getColumn() {
		return column;
	}

	public void setColumn(String column) {
		this.column = column;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public static final <T> RequestModel<T> getInstance(T parameter, RequestHeader header){
		return new RequestModel<T>(parameter,header);
	}

	public static final <T> RequestModel<T> getInstance(T parameter, int current, int size){
		return new RequestModel<T>(parameter,current,size);
	}

	public static final <T> RequestModel<T> getInstance(T parameter){
		return new RequestModel<T>(parameter);
	}

	public T getParameter() {
		return parameter;
	}
	public void setParameter(T parameter) {
		this.parameter = parameter;
	}
	public int getCurrent() {
		return current;
	}
	public void setCurrent(int current) {
		this.current = current;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}

	public RequestHeader getHeader() {
		return header;
	}

	public void setHeader(RequestHeader header) {
		this.header = header;
	}
}
