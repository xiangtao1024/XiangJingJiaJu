package com.xt.sentense.vo;

import java.io.Serializable;

import org.nutz.json.Json;

import com.xt.sentense.entity.Comment;
import com.xt.sentense.entity.Sentense;
import com.xt.sentense.entity.User;

public class CommentVo extends Comment implements Serializable{
	private static final long serialVersionUID = 1L;
	private Sentense sentense;
	private User user;
	
	public static CommentVo NEW(Comment comment){
		return Json.fromJson(CommentVo.class, Json.toJson(comment));
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
		return "CommentVo [sentense=" + sentense + ", user=" + user + "]";
	}
}
