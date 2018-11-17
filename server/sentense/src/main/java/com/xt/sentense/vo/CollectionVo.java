package com.xt.sentense.vo;

import org.nutz.json.Json;

import com.xt.sentense.entity.Collection;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.User;

public class CollectionVo extends Collection{
	private User user;
	private Sentense sentense;
	
	public static CollectionVo NEW(Collection co){
		return Json.fromJson(CollectionVo.class, Json.toJson(co));
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
	public Sentense getSentense() {
		return sentense;
	}
	public void setSentense(Sentense sentense) {
		if(sentense != null){
			//防止报奇怪的错误
			this.sentense = Json.fromJson(Sentense.class, Json.toJson(sentense));
		}
	}
	
	
}
