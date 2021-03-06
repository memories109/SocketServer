package com.realtime.model;

// 해당 매출의 회원 정보
public class MemberInfoModel {
	
	private String hpNo = "";
	private String mbrNo = "";
	private String cmpyNo = "";
	private String mbrNm = "";
	private String email = "";
	private String pointName = "";
	private String shopName = "";
	private String callCenterNumber = "";
	private String famRtnPsbYn = "N";
	
	public boolean EnableFamilyCard() {
		return "Y".equalsIgnoreCase(this.famRtnPsbYn);
	}
	
	public String getFamRtnPsbYn() {
		return famRtnPsbYn;
	}
	public void setFamRtnPsbYn(String famRtnPsbYn) {
		this.famRtnPsbYn = famRtnPsbYn;
	}
	public String getHpNo() {
		return hpNo;
	}
	public void setHpNo(String hpNo) {
		this.hpNo = hpNo;
	}
	public String getMbrNo() {
		return mbrNo;
	}
	public void setMbrNo(String mbrNo) {
		this.mbrNo = mbrNo;
	}
	public String getCmpyNo() {
		return cmpyNo;
	}
	public void setCmpyNo(String cmpyNo) {
		this.cmpyNo = cmpyNo;
	}
	public String getMbrNm() {
		return mbrNm;
	}
	public void setMbrNm(String mbrNm) {
		this.mbrNm = mbrNm;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPointName() {
		return pointName;
	}
	public void setPointName(String pointName) {
		this.pointName = pointName;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getCallCenterNumber() {
		return callCenterNumber;
	}
	public void setCallCenterNumber(String callCenterNumber) {
		this.callCenterNumber = callCenterNumber;
	}
}
