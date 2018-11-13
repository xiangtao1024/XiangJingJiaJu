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
 * 分类
 * @author XiangTao
 *
 */
@Entity(name="tb_category")
public class Category implements Serializable{
	private static final long serialVersionUID = -8115092291726826587L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column(nullable=false, unique=true, length=1000)
	private String icon;
	
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	public Category() {
		
	}
	
	public Category(Long id, String name, Date createTime, Date updateTime) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	
	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "Category [id=" + getId() + ", name=" + name + ", createTime=" + getCreateTime() + ", updateTime=" + getUpdateTime()
				+ "]";
	}
}
