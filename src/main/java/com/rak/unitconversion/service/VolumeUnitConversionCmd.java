package com.rak.unitconversion.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.rak.unitconversion.model.VolumeUnitEnum;
import com.rak.unitconversion.repository.VolumeConversionRepository;

public class VolumeUnitConversionCmd extends AbstractUnitConversionCmd {
	
	@Autowired
	VolumeConversionRepository volumeConversionRepository;
	
	public VolumeUnitConversionCmd(VolumeUnitEnum unitInEnum, VolumeUnitEnum unitOutEnum) {
		this.unitConversionModel = volumeConversionRepository.findByUnitinAndUnitout(unitInEnum, unitOutEnum);
	
	}

}
