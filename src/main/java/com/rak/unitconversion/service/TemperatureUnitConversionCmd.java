package com.rak.unitconversion.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rak.unitconversion.model.TemperatureUnitEnum;
import com.rak.unitconversion.repository.TemperatureConversionRepository;

public class TemperatureUnitConversionCmd extends AbstractUnitConversionCmd {
	
	@Autowired
	TemperatureConversionRepository tempConversionRepository;
	
	public TemperatureUnitConversionCmd(TemperatureUnitEnum unitInEnum, TemperatureUnitEnum unitOutEnum) {
		this.unitConversionModel = tempConversionRepository.findByUnitinAndUnitout(unitInEnum, unitOutEnum);

	}

}
