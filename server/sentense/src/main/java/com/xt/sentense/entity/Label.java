package com.xt.sentense.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 标签
 * @author XiangTao
 *
 */
@Entity(name="tb_label")
public class Label implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4351795325461670717L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column
	private Long sentenseNum;
	@Column
	private Long hotNum;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	public Label() {
	}
	
	public Label(Long id, String name, Date createTime, Date updateTime) {
		this.id = id;
		this.name = name;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	
	public Long getSentenseNum() {
		return sentenseNum;
	}

	public void setSentenseNum(Long sentenseNum) {
		this.sentenseNum = sentenseNum;
	}

	public Long getHotNum() {
		return hotNum;
	}

	public void setHotNum(Long hotNum) {
		this.hotNum = hotNum;
	}
	
	@Override
	public String toString() {
		return "Label [id=" + id + ", name=" + name + ", sentenseNum=" + sentenseNum + ", hotNum=" + hotNum
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}