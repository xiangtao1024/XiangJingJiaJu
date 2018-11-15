package com.xt.sentense.vo;

import java.io.Serializable;

import org.nutz.json.Json;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.User;


public class SentenseVo extends Sentense implements Serializable{
	private static final long serialVersionUID = -5508065289009503724L;
	
	private Category category;
	private User user;
	
	public static SentenseVo NEW(Sentense se){
		SentenseVo sv = new SentenseVo();
		sv = Json.fromJson(SentenseVo.class, Json.toJson(se));
		return sv;
	}
	
	public SentenseVo() {
		super();
	}
	public SentenseVo(Category category, User user) {
		super();
		this.category = category;
		this.user = user;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		if(category != null){
			//防止报奇怪的错误
			this.category = Json.fromJson(Category.class, Json.toJson(category));
		}
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		if(user != null){
			//防止报奇怪的错误
			this.user = Json.fromJson(User.class, Json.toJson(user));
		}
	}
	
	@Override
	public String toString() {
		super.toString();
		return "SentenseVo [category=" + category + ", user=" + user + "]";
	}
}
