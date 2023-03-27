package com.sgcc.code.common.exception;

/**
 * 自定义统一类处理
 * @author Eric
 *
 */
public class CommonException extends RuntimeException {

	private int code;
	public CommonException(int code, String message) {
		super(message);
		this.code = code;
	}
	public int getCode() {
		return code;
	}

}
