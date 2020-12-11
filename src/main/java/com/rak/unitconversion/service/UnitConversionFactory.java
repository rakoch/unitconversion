package com.rak.unitconversion.service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rak.unitconversion.model.TemperatureUnit;
import com.rak.unitconversion.model.TemperatureUnitEnum;
import com.rak.unitconversion.model.VolumeUnit;
import com.rak.unitconversion.model.VolumeUnitEnum;
import com.rak.unitconversion.repository.TemperatureUnitRepository;
import com.rak.unitconversion.repository.VolumeUnitRepository;

public class UnitConversionFactory {

	private static final Map<String, TemperatureUnitEnum> TEMPERATURE_ENUM_MAP;
	private static final Map<String, VolumeUnitEnum> VOLUME_ENUM_MAP;

	private static final Map<TemperatureUnitEnum, TemperatureUnit> TEMPERATURE_MODEL_MAP;
	private static final Map<VolumeUnitEnum, VolumeUnit> VOLUME_MODEL_MAP;
	
	private static TemperatureUnitRepository tempUnitRepository = SpringContext
			.getBean(TemperatureUnitRepository.class);
	
	private static VolumeUnitRepository volUnitRepository = SpringContext
			.getBean(VolumeUnitRepository.class);
	
	// static maps of enums kept out of enums and in factory for quick conversion of
	// strings to enums
	// requirements were such to allow input of strings in one common method for any
	// unit and indicate problem is not correct
	// since desired to group the units into temperature and volume, made things a
	// little more complicated
	static {
		Map<String, TemperatureUnitEnum> tmap = new ConcurrentHashMap<String, TemperatureUnitEnum>();
		Map<TemperatureUnitEnum, TemperatureUnit> tModelMap = new ConcurrentHashMap<TemperatureUnitEnum, TemperatureUnit>();
		for (TemperatureUnitEnum instance : TemperatureUnitEnum.values()) {
			tmap.put(instance.name(), instance);
			tModelMap.put(instance, tempUnitRepository.findByTemperatureUnitEnum(instance));
		}
		TEMPERATURE_ENUM_MAP = Collections.unmodifiableMap(tmap);
		TEMPERATURE_MODEL_MAP = Collections.unmodifiableMap(tModelMap);
		
		Map<String, VolumeUnitEnum> vmap = new ConcurrentHashMap<String, VolumeUnitEnum>();
		Map<VolumeUnitEnum, VolumeUnit> vModelMap = new ConcurrentHashMap<VolumeUnitEnum, VolumeUnit>();
		for (VolumeUnitEnum instance : VolumeUnitEnum.values()) {
			vmap.put(instance.name(), instance);
			vModelMap.put(instance, volUnitRepository.findByVolumeUnitEnum(instance));
		}
		VOLUME_ENUM_MAP = Collections.unmodifiableMap(vmap);
		VOLUME_MODEL_MAP = Collections.unmodifiableMap(vModelMap);
	}

	public static TemperatureUnitEnum getTemperatureUnitEnum(String name) {
		return TEMPERATURE_ENUM_MAP.get(name.trim().toUpperCase());
	}

	public static VolumeUnitEnum getVolumeUnitEnum(String name) {
		return VOLUME_ENUM_MAP.get(name.trim().toUpperCase());
	}
	
	public static TemperatureUnit getTemperatureUnit(TemperatureUnitEnum temperatureUnitEnum) {
		return TEMPERATURE_MODEL_MAP.get(temperatureUnitEnum);
	}

	public static VolumeUnit getVolumeUnit(VolumeUnitEnum volumeUnitEnum) {
		return VOLUME_MODEL_MAP.get(volumeUnitEnum);
	}

	public static boolean isTemperatureUnitEnum(String name) {
		return TEMPERATURE_ENUM_MAP.keySet().contains(name.trim().toUpperCase());
	}

	public static boolean isVolumeUnitEnum(String name) {
		return VOLUME_ENUM_MAP.keySet().contains(name.trim().toUpperCase());
	}

	/**
	 * Validate unit string pairs and throw exceptions or return true if all is OK.
	 * @param unitin
	 * @param unitout
	 * @return true if valid
	 * @throws InvalidUnitTypeException or InvalidUnitCombinationException if invalid unit or pair passed in.
	 */
	public static boolean validateUnitPairs(String unitin, String unitout) {
		boolean valid = false;
		int tempUnitCount = 0;
		int volUnitCount = 0;

		if((unitin == null) || unitin.isBlank() || (unitout == null) || unitout.isBlank()) {
			throw new InvalidUnitTypeException(String.format("Invalid unit type(s) '%s' to '%s'", unitin, unitout));
		}
		
		TemperatureUnitEnum tempUnitIn = UnitConversionFactory.getTemperatureUnitEnum(unitin);
		if (tempUnitIn != null) {
			tempUnitCount++;
		}
		TemperatureUnitEnum tempUnitOut = UnitConversionFactory.getTemperatureUnitEnum(unitout);
		if (tempUnitOut != null) {
			tempUnitCount++;
		}
		VolumeUnitEnum volUnitIn = UnitConversionFactory.getVolumeUnitEnum(unitin);
		if (volUnitIn != null) {
			volUnitCount++;
		}
		VolumeUnitEnum volUnitOut = UnitConversionFactory.getVolumeUnitEnum(unitout);
		if (volUnitOut != null) {
			volUnitCount++;
		}

		// if not both temp or volume units, we have a problem--figure out exact problem
		if ((tempUnitCount < 2) && (volUnitCount < 2)) {
			if ((tempUnitCount == 1) && (volUnitCount == 1)) {
				throw new InvalidUnitCombinationException(
						String.format("Invalid unit combo %s to %s", unitin, unitout));
			} else {
				throw new InvalidUnitTypeException(String.format("Invalid unit type(s) '%s' to '%s'", unitin, unitout));
			}
		}
		else {
			valid = true;
		}

		return valid;
	}

	/**
	 * Retrieve conversion command based on given string unit in and out parameters.
	 * 
	 * @param unitin
	 * @param unitout
	 * @return AbstractUnitConversionCmd implementation which  be temperature or volume -specific.
 	 * @throws InvalidUnitTypeException or InvalidUnitCombinationException if invalid unit or pair passed in.
	 */
	public static AbstractUnitConversionCmd getConversionCommand(String unitin, String unitout) {

		UnitConversionFactory.validateUnitPairs(unitin, unitout);

		unitin = unitin.trim();
		unitout = unitout.trim();
		if (UnitConversionFactory.isTemperatureUnitEnum(unitin)
				&& UnitConversionFactory.isTemperatureUnitEnum(unitout)) {
			return new TemperatureUnitConversionCmd(UnitConversionFactory.getTemperatureUnitEnum(unitin),
					UnitConversionFactory.getTemperatureUnitEnum(unitout));
		} else {
			return new VolumeUnitConversionCmd(UnitConversionFactory.getVolumeUnitEnum(unitin),
					UnitConversionFactory.getVolumeUnitEnum(unitout));
		}

	}

}
