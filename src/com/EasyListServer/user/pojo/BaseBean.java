package com.EasyListServer.user.pojo;

public class BaseBean {

	private String result;// 请求相应结果 0失败，1成功

	private String msg; // 请求返回提示字符串

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
