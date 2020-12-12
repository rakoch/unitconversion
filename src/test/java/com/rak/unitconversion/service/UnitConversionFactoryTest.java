package com.rak.unitconversion.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
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
		
		result = UnitConversionFactory.validateUnitPairs("Kelvin", "Fahrenheit");
		assert result == true;
		
		
		result = UnitConversionFactory.validateUnitPairs("cups", "liters");
		assert result == true;
		
		Assertions.assertThrows(InvalidUnitCombinationException.class, () -> {
			UnitConversionFactory.validateUnitPairs("gallons", "Kelvin");
		});
		
		Assertions.assertThrows(InvalidUnitCombinationException.class, () -> {
			UnitConversionFactory.validateUnitPairs("Fahrenheit", "Gallons");
		});

		Assertions.assertThrows(InvalidUnitTypeException.class, () -> {
			UnitConversionFactory.validateUnitPairs("dog", "Celsius");
		});
		


	}

	@Test
	void testGetConversionCommand() {
//		AbstractUnitConversionCmd tempConvFahrToRankCommand = UnitConversionFactory.getConversionCommand("Fahrenheit", "Rankine");
//		assert (tempConvFahrToRankCommand != null);
//		UnitConversionResult result = tempConvFahrToRankCommand.convert("84.2");
//		assert(result != null);
	}

}
