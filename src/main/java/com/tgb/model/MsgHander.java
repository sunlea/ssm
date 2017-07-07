package com.tgb.model;

/**
 * 通用消息传输模型
 * 
 */
public class MsgHander {
	/**
	 * 错误代码
	 */
	public static final String CONTROLLER_CODE_ERROR = "500";
	/**
	 * 成功代码
	 */
	public static final String CONTROLLER_CODE_SUCCESS = "200";
	/**
	 * 警告代码
	 */
	public static final String CONTROLLER_CODE_WARING = "205";

	/**
	 * 处理状态
	 */
	private String status;

	/**
	 * 消息句柄
	 */
	private String message;

	/**
	 * 内容句柄
	 */
	private Object context;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getContext() {
		return context;
	}

	public void setContext(Object context) {
		this.context = context;
	}

}
