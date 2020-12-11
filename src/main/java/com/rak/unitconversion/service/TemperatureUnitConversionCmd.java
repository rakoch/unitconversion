package com.rak.unitconversion.service;

import com.rak.unitconversion.model.TemperatureUnit;
import com.rak.unitconversion.model.TemperatureUnitEnum;
import com.rak.unitconversion.repository.TemperatureConversionRepository;

public class TemperatureUnitConversionCmd extends AbstractUnitConversionCmd {

//	@Autowired
//	TemperatureConversionRepository tempConversionRepository;

	TemperatureConversionRepository tempConversionRepository = SpringContext
			.getBean(TemperatureConversionRepository.class);

	public TemperatureUnitConversionCmd(TemperatureUnitEnum unitInEnum, TemperatureUnitEnum unitOutEnum) {
		super(unitInEnum.toString(), unitOutEnum.toString());
		TemperatureUnit unitInModel = UnitConversionFactory.getTemperatureUnit(unitInEnum);
		TemperatureUnit uniOutnModel = UnitConversionFactory.getTemperatureUnit(unitOutEnum);
		this.unitConversionModel = tempConversionRepository.findByUnitinAndUnitout(unitInModel, uniOutnModel);
	}

}
