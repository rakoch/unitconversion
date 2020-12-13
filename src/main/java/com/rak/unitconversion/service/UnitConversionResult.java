package com.rak.unitconversion.service;

public class UnitConversionResult {
	
	private UnitConversionRequest request;
	private ResultStatus resultStatus;
	private String msg; 

	public UnitConversionRequest getRequest() {
		return request;
	}

	public void setRequest(UnitConversionRequest request) {
		this.request = request;
	}

	public ResultStatus getResultStatus() {
		return resultStatus;
	}
	public void setResultStatus(ResultStatus resultStatus) {
		this.resultStatus = resultStatus;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
