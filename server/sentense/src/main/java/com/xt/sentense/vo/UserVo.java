package com.xt.sentense.vo;

import java.io.Serializable;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.User;

public class UserVo extends User implements Serializable{
	private static final long serialVersionUID = -5171045084458612230L;
	private String test01;
	private String test02;
	private Category c1;
	
	public String getTest01() {
		return test01;
	}
	public void setTest01(String test01) {
		this.test01 = test01;
	}
	public String getTest02() {
		return test02;
	}
	public void setTest02(String test02) {
		this.test02 = test02;
	}
	
	public Category getC1() {
		return c1;
	}
	public void setC1(Category c1) {
		this.c1 = c1;
	}
	@Override
	public String toString() {
		return "UserVo [test01=" + test01 + ", test02=" + test02 + "]";
	}
}
