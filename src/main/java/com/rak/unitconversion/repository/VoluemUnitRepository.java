package com.rak.unitconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.unitconversion.model.VolumeUnit;

@Repository
public interface VoluemUnitRepository extends JpaRepository<VolumeUnit, Long> {
	 
}

