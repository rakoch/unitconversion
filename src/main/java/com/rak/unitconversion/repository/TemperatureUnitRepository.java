package com.rak.unitconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.unitconversion.model.TemperatureUnit;

@Repository
public interface TemperatureUnitRepository extends JpaRepository<TemperatureUnit, Long> {
	 
}

