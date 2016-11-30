package com.sf.lottery.common.dto;


import com.sf.lottery.common.utils.ConcurrentDateUtil;

import java.util.Date;

/**
 * Json结果封装，用于前端传递
 *
 * @author Hash Zhang
 * @version 1.0.0
 * @date 2016/11/4.
 */
public class JsonResult<T> {
	private String message;
	
	private String timestamp = ConcurrentDateUtil.format(new Date());
	
	private T data;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
