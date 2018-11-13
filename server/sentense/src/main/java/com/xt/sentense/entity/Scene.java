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
 * 场景
 * @author XiangTao
 *
 */
@Entity(name="tb_scene")
public class Scene implements Serializable{
	private static final long serialVersionUID = -8116092391726826587L;
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable=false, unique=true)
	private String name;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date createTime;
	@Column(nullable=false)
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date updateTime;
	
	public Scene() {
	}
	
	public Scene(Long id, String name, Date createTime, Date updateTime) {
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
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", createTime=" + createTime + ", updateTime=" + updateTime
				+ "]";
	}
}