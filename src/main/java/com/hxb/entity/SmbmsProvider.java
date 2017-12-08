package com.hxb.entity;

import java.sql.Timestamp;
import java.util.List;

/**
 * SmbmsProvider entity. @author MyEclipse Persistence Tools
 */

public class SmbmsProvider implements java.io.Serializable {

	// Fields

	private Long id;
	private String proCode;
	private String proName;
	private String proDesc;
	private String proContact;
	private String proPhone;
	private String proAddress;
	private String proFax;
	private Long createdBy;
	private Timestamp creationDate;
	private Timestamp modifyDate;
	private Long modifyBy;
	private List<SmbmsBill> billList;
	// 企业营业执照
	private String enterPrise;
	// 组织机构代码证
	private String planner;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProCode() {
		return proCode;
	}
	public void setProCode(String proCode) {
		this.proCode = proCode;
	}
	public String getProName() {
		return proName;
	}
	public void setProName(String proName) {
		this.proName = proName;
	}
	public String getProDesc() {
		return proDesc;
	}
	public void setProDesc(String proDesc) {
		this.proDesc = proDesc;
	}
	public String getProContact() {
		return proContact;
	}
	public void setProContact(String proContact) {
		this.proContact = proContact;
	}
	public String getProPhone() {
		return proPhone;
	}
	public void setProPhone(String proPhone) {
		this.proPhone = proPhone;
	}
	public String getProAddress() {
		return proAddress;
	}
	public void setProAddress(String proAddress) {
		this.proAddress = proAddress;
	}
	public String getProFax() {
		return proFax;
	}
	public void setProFax(String proFax) {
		this.proFax = proFax;
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
	public Timestamp getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Timestamp modifyDate) {
		this.modifyDate = modifyDate;
	}
	public Long getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Long modifyBy) {
		this.modifyBy = modifyBy;
	}
	public List<SmbmsBill> getBillList() {
		return billList;
	}
	public void setBillList(List<SmbmsBill> billList) {
		this.billList = billList;
	}
	
	public String getEnterPrise() {
		return enterPrise;
	}
	public void setEnterPrise(String enterPrise) {
		this.enterPrise = enterPrise;
	}
	public String getPlanner() {
		return planner;
	}
	public void setPlanner(String planner) {
		this.planner = planner;
	}
	public SmbmsProvider() {
		super();
	}
	public SmbmsProvider(Long id, String proCode, String proName,
			String proDesc, String proContact, String proPhone,
			String proAddress, String proFax, Long createdBy,
			Timestamp creationDate, Timestamp modifyDate, Long modifyBy,
			List<SmbmsBill> billList, String enterPrise, String planner) {
		super();
		this.id = id;
		this.proCode = proCode;
		this.proName = proName;
		this.proDesc = proDesc;
		this.proContact = proContact;
		this.proPhone = proPhone;
		this.proAddress = proAddress;
		this.proFax = proFax;
		this.createdBy = createdBy;
		this.creationDate = creationDate;
		this.modifyDate = modifyDate;
		this.modifyBy = modifyBy;
		this.billList = billList;
		this.enterPrise = enterPrise;
		this.planner = planner;
	}
	@Override
	public String toString() {
		return "SmbmsProvider [id=" + id + ", proCode=" + proCode
				+ ", proName=" + proName + ", proDesc=" + proDesc
				+ ", proContact=" + proContact + ", proPhone=" + proPhone
				+ ", proAddress=" + proAddress + ", proFax=" + proFax
				+ ", createdBy=" + createdBy + ", creationDate=" + creationDate
				+ ", modifyDate=" + modifyDate + ", modifyBy=" + modifyBy
				+ ", billList=" + billList + ", enterPrise=" + enterPrise
				+ ", planner=" + planner + "]";
	}
	
}