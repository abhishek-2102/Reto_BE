package com.niit.reto.model;

import javax.persistence.Transient;

import org.springframework.stereotype.Component;

@Component
public class Details {
	
	@Transient
	private String msg;
	
	@Transient
	private String code;
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	
	
}
