package com.rak.unitconversion.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rak.unitconversion.model.TemperatureConversion;
import com.rak.unitconversion.model.TemperatureUnit;

@Repository
public interface TemperatureConversionRepository extends JpaRepository<TemperatureConversion, Long> {

	TemperatureConversion findByUnitinAndUnitout(TemperatureUnit unitIn, TemperatureUnit unitOut);

}
