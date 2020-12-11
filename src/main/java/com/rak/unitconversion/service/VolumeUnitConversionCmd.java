package com.rak.unitconversion.service;

import com.rak.unitconversion.model.VolumeUnit;
import com.rak.unitconversion.model.VolumeUnitEnum;
import com.rak.unitconversion.repository.VolumeConversionRepository;

public class VolumeUnitConversionCmd extends AbstractUnitConversionCmd {

//	@Autowired
//	VolumeConversionRepository volumeConversionRepository;

	VolumeConversionRepository volumeConversionRepository = SpringContext.getBean(VolumeConversionRepository.class);

	public VolumeUnitConversionCmd(VolumeUnitEnum unitInEnum, VolumeUnitEnum unitOutEnum) {
		super(unitInEnum.toString(), unitOutEnum.toString());
		VolumeUnit unitInModel = UnitConversionFactory.getVolumeUnit(unitInEnum);
		VolumeUnit uniOutnModel = UnitConversionFactory.getVolumeUnit(unitOutEnum);
		this.unitConversionModel = volumeConversionRepository.findByUnitinAndUnitout(unitInModel, uniOutnModel);
	}

}
