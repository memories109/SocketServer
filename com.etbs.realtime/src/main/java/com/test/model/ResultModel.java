package com.realtime.model;

import com.realtime.enums.AUTO_APPL_HDL_CD;

public class ResultModel {

	private String message = "지정되지 않음";
	private AUTO_APPL_HDL_CD code = AUTO_APPL_HDL_CD.R99;
	private boolean success = false;
	private HS10Model hs10Model = null;
	
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public AUTO_APPL_HDL_CD getCode() {
		return code;
	}
	public void setCode(AUTO_APPL_HDL_CD code) {
		this.code = code;
	}
	public boolean isSuccess() {
		return success;
	}	
	public void setSuccess(boolean success) {
		this.success = success;
	}	
	public HS10Model getHs10Model() {
		return hs10Model;
	}	
	public void setHs10Model(HS10Model hs10Model) {
		this.hs10Model = hs10Model;
	}
}
