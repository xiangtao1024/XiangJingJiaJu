package com.xt.sentense.vo;

import java.io.Serializable;

import org.nutz.json.Json;

import com.xt.sentense.entity.Category;
import com.xt.sentense.entity.User;

public class UserVo extends User implements Serializable{
	private static final long serialVersionUID = -5171045084458612230L;
	
	private int collectionNum; //收藏数
	private int jukuNum; //我的句库
	
	public static UserVo NEW(User user){
		UserVo uv = Json.fromJson(UserVo.class, Json.toJson(user));
		return uv;
	}
	
	public int getCollectionNum() {
		return collectionNum;
	}
	public void setCollectionNum(int collectionNum) {
		this.collectionNum = collectionNum;
	}
	public int getJukuNum() {
		return jukuNum;
	}
	public void setJukuNum(int jukuNum) {
		this.jukuNum = jukuNum;
	}
	@Override
	public String toString() {
		super.toString();
		return "UserVo [collectionNum=" + collectionNum + ", jukuNum=" + jukuNum + "]";
	}
	
	
}
