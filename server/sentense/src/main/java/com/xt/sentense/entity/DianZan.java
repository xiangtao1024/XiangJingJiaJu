package com.xt.sentense.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name="tb_dian_zan")
public class DianZan implements Serializable{
	private static final long serialVersionUID = -8757415480181588623L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private Long userId;
	@Column
	private Long sentenseId;
	
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getSentenseId() {
		return sentenseId;
	}
	public void setSentenseId(Long sentenseId) {
		this.sentenseId = sentenseId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Collection [id=" + id + ", userId=" + userId + ", sentenseId=" + sentenseId + ", createTime="
				+ createTime + ", updateTime=" + updateTime + "]";
	}
}
