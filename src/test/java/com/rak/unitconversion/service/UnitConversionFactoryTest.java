package com.rak.unitconversion.service;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UnitConversionFactoryTest {

	@Test
	void testGetTemperatureUnitEnum() {
		//fail("Not yet implemented");
	}

	@Test
	void testGetVolumeUnitEnum() {
		//fail("Not yet implemented");
	}

	@Test
	void testIsTemperatureUnitEnum() {
		//fail("Not yet implemented");
	}

	@Test
	void testIsVolumeUnitEnum() {
		//fail("Not yet implemented");
	}

	@Test
	void testValidateUnitPairs() {
		boolean result = UnitConversionFactory.validateUnitPairs("Fahrenheit", "Rankine");
		assert result == true;
	}

	@Test
	void testGetConversionCommand() {
		//fail("Not yet implemented");
	}

}