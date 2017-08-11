package com.zhanghe.Fast.util;

import java.util.List;

import com.google.gson.Gson;

public class ReturnValue<T> {
	
	
	public ReturnValue(){
		super();
	}
	public ReturnValue(Integer ret, String message) {
		super();
		this.ret = ret;
		this.message = message;
	}
	
	public String toJson(){
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	public Integer ret = 1;
	public String message;
	public T obj;
	public List<T> result;
	public Integer getRet() {
		return ret;
	}
	public void setRet(Integer ret) {
		this.ret = ret;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public T getObj() {
		return obj;
	}
	public void setObj(T obj) {
		this.obj = obj;
	}
	public List<T> getResult() {
		return result;
	}
	public void setResult(List<T> result) {
		this.result = result;
	}
	
	
}
