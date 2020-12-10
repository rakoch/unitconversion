package com.rak.unitconversion.service;

import org.junit.jupiter.api.Assertions;
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
		
		Assertions.assertThrows(InvalidUnitCombinationException.class, () -> {
			UnitConversionFactory.validateUnitPairs("Fahrenheit", "Gallons");
		});

		Assertions.assertThrows(InvalidUnitTypeException.class, () -> {
			UnitConversionFactory.validateUnitPairs("Fahrenheit", "Dog");
		});
	}

	@Test
	void testGetConversionCommand() {
		//fail("Not yet implemented");
	}

}
