package com.rak.unitconversion.service;

import java.math.BigDecimal;

import com.rak.unitconversion.model.TemperatureUnitEnum;

public class TemperatureConversionRequest {

	private TemperatureUnitEnum inputUnit;
	private TemperatureUnitEnum targetUnit;
	private BigDecimal value;
	
	public TemperatureUnitEnum getInputUnit() {
		return inputUnit;
	}
	public void setInputUnit(TemperatureUnitEnum inputUnit) {
		this.inputUnit = inputUnit;
	}
	public TemperatureUnitEnum getTargetUnit() {
		return targetUnit;
	}
	public void setTargetUnit(TemperatureUnitEnum targetUnit) {
		this.targetUnit = targetUnit;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "ConversionRequest [inputUnit=" + inputUnit + ", targetUnit=" + targetUnit + ", value=" + value + "]";
	}

	
	
}
