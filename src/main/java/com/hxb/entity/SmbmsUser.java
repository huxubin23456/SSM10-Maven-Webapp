package com.hxb.entity;

import java.sql.Timestamp;
import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;


/**
 * SmbmsUser entity. @author MyEclipse Persistence Tools
 */

public class SmbmsUser implements java.io.Serializable {

	// Fields

	private Long id;
	private String userCode;
	private String userName;
	private String userPassword;
	private Integer gender;
	/*@DateTimeFormat(pattern = "yyyy-MM-dd")*/
	private Date birthday;
	private String phone;
	private String address;
	private Integer userRole;
	private Long createdBy;
	private Timestamp creationDate;
	private Long modifyBy;
	private Timestamp modifyDate;
	// 图片路径
	private String photoPath;
	private Integer age;
	private String roleName;

	public Integer getAge() {
		Date date = new Date();
		age=date.getYear()-birthday.getYear();
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserCode() {
		return userCode;
	}

	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getUserRole() {
		return userRole;
	}

	public void setUserRole(Integer userRole) {
		this.userRole = userRole;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Timestamp getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Timestamp creationDate) {
		this.creationDate = creationDate;
	}

	public Long getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Timestamp getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public SmbmsUser() {
		super();
	}

	public SmbmsUser(Long id, String userCode, String userName,
			String userPassword, Integer gender, Date birthday, String phone,
			String address, Integer userRole, Long createdBy,
			Timestamp creationDate, Long modifyBy, Timestamp modifyDate,
			String photoPath, Integer age, String roleName) {
		super();
		this.id = id;
		this.userCode = userCode;
		this.userName = userName;
		this.userPassword = userPassword;
		this.gender = gender;
		this.birthday = birthday;
		this.phone = phone;
		this.address = address;
		this.userRole = userRole;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyBy = modifyBy;
		this.modifyDate = modifyDate;
		this.photoPath = photoPath;
		this.age = age;
		this.roleName = roleName;
	}

	@Override
	public String toString() {
		return "SmbmsUser [id=" + id + ", userCode=" + userCode + ", userName="
				+ userName + ", userPassword=" + userPassword + ", gender="
				+ gender + ", birthday=" + birthday + ", phone=" + phone
				+ ", address=" + address + ", userRole=" + userRole
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyBy=" + modifyBy + ", modifyDate=" + modifyDate
				+ ", photoPath=" + photoPath + ", age=" + age + ", roleName="
				+ roleName + "]";
	}

	public SmbmsUser(String userCode) {
		super();
		this.userCode = userCode;
	}

}