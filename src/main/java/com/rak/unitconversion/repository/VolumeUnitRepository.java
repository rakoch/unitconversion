package com.rak.unitconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.unitconversion.model.VolumeUnit;
import com.rak.unitconversion.model.VolumeUnitEnum;

@Repository
public interface VolumeUnitRepository extends JpaRepository<VolumeUnit, Long> {

	VolumeUnit findByVolumeUnitEnum(VolumeUnitEnum volumeUnitEnum);

}
