package com.rak.unitconversion.service;

public class TemperatureConversionResult {
	
	private TemperatureConversionRequest request;
	private ResultStatus resultStatus;
	private String msg; 
	public TemperatureConversionRequest getRequest() {
		return request;
	}
	public void setRequest(TemperatureConversionRequest request) {
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
