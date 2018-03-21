package com.qiuhui.util;

public class AjaxResult {

	private String state ;
	private Object data;
	private String message;
	
	public static final String STATE_SUCCESS = "0";
	public static final String STATE_ERROR = "40";
	
	public static AjaxResult success(){
		AjaxResult res = new AjaxResult();
		res.setState(STATE_SUCCESS);
		return res;
	}
	
	public static AjaxResult success(Object data){
		AjaxResult res = new AjaxResult();
		res.setState(STATE_SUCCESS);
		res.setData(data);
		return res;
	}
	
	public static AjaxResult error(Object data){
		AjaxResult res = new AjaxResult();
		res.setState(STATE_ERROR);
		res.setData(data);
		return res;
	}
	
	public static AjaxResult error(){
		AjaxResult res = new AjaxResult();
		res.setState(STATE_ERROR);
		return res;
		
	}
	
	public static AjaxResult error(String message){
		AjaxResult res = new AjaxResult();
		res.setState(STATE_ERROR);
		res.setMessage(message);
		return res;
		
	}
	
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
