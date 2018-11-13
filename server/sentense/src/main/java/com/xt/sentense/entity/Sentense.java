package com.xt.sentense.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 句子
 * @author XiangTao
 *
 */
@Entity(name="tb_sentense")
public class Sentense implements Serializable{
	private static final long serialVersionUID = -6149110439121469875L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false)
	private int categoryId;
	@Column(nullable=false)
	private int userId;
	@Column
	private String scenes; //多个用逗号相隔
	@Column
	private String labels; //多个用逗号相隔
	@Column
	private String formSource;
	@Column
	private int zanNum;
	@Column
	private int commentNum;
	@Column(nullable=false, unique=true, length=800)
	private String content;
	
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
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getScenes() {
		return scenes;
	}
	public void setScenes(String scenes) {
		this.scenes = scenes;
	}
	public String getLabels() {
		return labels;
	}
	public void setLabels(String labels) {
		this.labels = labels;
	}
	public String getFormSource() {
		return formSource;
	}
	public void setFormSource(String formSource) {
		this.formSource = formSource;
	}
	public int getZanNum() {
		return zanNum;
	}
	public void setZanNum(int zanNum) {
		this.zanNum = zanNum;
	}
	public int getCommentNum() {
		return commentNum;
	}
	public void setCommentNum(int commentNum) {
		this.commentNum = commentNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
		return "Sentense [id=" + id + ", categoryId=" + categoryId + ", userId=" + userId + ", scenes=" + scenes
				+ ", labels=" + labels + ", formSource=" + formSource + ", zanNum=" + zanNum + ", commentNum="
				+ commentNum + ", content=" + content + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}
