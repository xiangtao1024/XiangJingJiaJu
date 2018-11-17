package com.xt.sentense.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import com.fasterxml.jackson.annotation.JsonFormat;
/**
 * 用户
 * @author XiangTao
 *
 */
@Entity(name="tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 5706857412102961084L;
	@Id
	@GeneratedValue
	private Long id;
	@Column
	private String name;
	@Column
	private String nickname;
	@Column
	private String icon;
	@Column(nullable=false, unique=true)
	private String phone;
	@Column(nullable=false)
	private String password;
	@Column(nullable=false)
	private String salt;
	@Column(nullable=false)
	private int phoneState;
	@Column
	private int grade; //积分
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getSalt() {
		return salt;
	}
	public void setSalt(String salt) {
		this.salt = salt;
	}
	public int getPhoneState() {
		return phoneState;
	}
	public void setPhoneState(int phoneState) {
		this.phoneState = phoneState;
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
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", nickname=" + nickname + ", icon=" + icon + ", phone=" + phone
				+ ", password=" + password + ", salt=" + salt + ", phoneState=" + phoneState + ", grade=" + grade
				+ ", createTime=" + createTime + ", updateTime=" + updateTime + "]";
	}
}
