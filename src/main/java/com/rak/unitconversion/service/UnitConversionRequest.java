package com.rak.unitconversion.service;

public class UnitConversionRequest {

	private String inUnit;
	private String outUnit;
	private String value;
	private String answer;
	

	public String getInUnit() {
		return inUnit;
	}
	public void setInUnit(String inUnit) {
		this.inUnit = inUnit;
	}

	public String getOutUnit() {
		return outUnit;
	}
	public void setOutUnit(String outUnit) {
		this.outUnit = outUnit;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getAnswer() {
		return answer;
	}
	public void setAnswer(String answer) {
		this.answer = answer;
	}
	@Override
	public String toString() {
		return "UnitConversionRequest [inUnit=" + inUnit + ", outUnit=" + outUnit + ", value=" + value + ", answer="
				+ answer + "]";
	}



	
	
}
