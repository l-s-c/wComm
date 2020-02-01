package com.lsc.utils;
/**
 * 返回实体类
 * @author ABC
 *
 */
public class JsonResult {
	private int code = 0;
	private String msg;
	private boolean succ = false;
	private Object data;
	
	public static JsonResult ok() {
		JsonResult jr = new JsonResult();
		jr.setCode(1);
		jr.setSucc(true);
		return jr;
	}
	public static JsonResult ok(String msg) {
		JsonResult jr = new JsonResult();
		jr.setCode(1);
		jr.setMsg(msg);
		jr.setSucc(true);
		return jr;
	}
	public static JsonResult ok(Object data) {
		JsonResult jr = new JsonResult();
		jr.setCode(1);
		jr.setSucc(true);
		jr.setData(data);
		return jr;
	}
	public static JsonResult ok(String msg,Object data) {
		JsonResult jr = new JsonResult();
		jr.setCode(1);
		jr.setSucc(true);
		jr.setData(data);
		jr.setMsg(msg);
		return jr;
	}
	public static JsonResult fail() {
		JsonResult jr = new JsonResult();
		return jr;
	}
	public static JsonResult fail(String msg) {
		JsonResult jr = new JsonResult();
		jr.setCode(0);
		jr.setSucc(false);
		jr.setMsg(msg);
		return jr;
	}
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public boolean isSucc() {
		return succ;
	}
	public void setSucc(boolean succ) {
		this.succ = succ;
	}
}
