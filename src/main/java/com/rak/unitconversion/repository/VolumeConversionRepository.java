package com.rak.unitconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.unitconversion.model.VolumeConversion;
import com.rak.unitconversion.model.VolumeUnitEnum;

@Repository
public interface VolumeConversionRepository extends JpaRepository<VolumeConversion, Long> {
	 
	VolumeConversion findByUnitInAndUnitOut(VolumeUnitEnum in, VolumeUnitEnum out);

}

