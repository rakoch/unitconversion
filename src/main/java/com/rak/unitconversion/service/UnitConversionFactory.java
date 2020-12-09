package com.rak.unitconversion.service;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.rak.unitconversion.model.TemperatureUnitEnum;
import com.rak.unitconversion.model.VolumeUnitEnum;

public class UnitConversionFactory {

	private static final Map<String, TemperatureUnitEnum> TEMPERATURE_ENUM_MAP;
	private static final Map<String, VolumeUnitEnum> VOLUME_ENUM_MAP;

	// static maps of enums kept out of enums and in factory for quick conversion of
	// strings to enums
	// requirements were such to allow input of strings in one common method for any
	// unit and indicate problem is not correct
	// since desired to group the units into temperature and volume, made things a
	// little more complicated
	static {
		Map<String, TemperatureUnitEnum> tmap = new ConcurrentHashMap<String, TemperatureUnitEnum>();
		for (TemperatureUnitEnum instance : TemperatureUnitEnum.values()) {
			tmap.put(instance.name(), instance);
		}
		TEMPERATURE_ENUM_MAP = Collections.unmodifiableMap(tmap);

		Map<String, VolumeUnitEnum> vmap = new ConcurrentHashMap<String, VolumeUnitEnum>();
		for (VolumeUnitEnum instance : VolumeUnitEnum.values()) {
			vmap.put(instance.name(), instance);
		}
		VOLUME_ENUM_MAP = Collections.unmodifiableMap(vmap);
	}

	public static TemperatureUnitEnum getTemperatureUnitEnum(String name) {
		return TEMPERATURE_ENUM_MAP.get(name.trim().toUpperCase());
	}

	public static VolumeUnitEnum getVolumeUnitEnum(String name) {
		return VOLUME_ENUM_MAP.get(name.trim().toUpperCase());
	}

	public static boolean isTemperatureUnitEnum(String name) {
		return TEMPERATURE_ENUM_MAP.keySet().contains(name.trim().toUpperCase());
	}

	public static boolean isVolumeUnitEnum(String name) {
		return VOLUME_ENUM_MAP.keySet().contains(name.trim().toUpperCase());
	}

	public static boolean validateUnitPairs(String unitin, String unitout) {
		boolean valid = false;
		int tempUnitCount = 0;
		int volUnitCount = 0;

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
				throw new InvalidUnitTypeException(String.format("Invalid unit type %s to %s", unitin, unitout));
			}
		}
		else {
			valid = true;
		}

		return valid;
	}

	public static AbstractUnitConversionCmd getConversionCommand(String unitin, String unitout) {

		UnitConversionFactory.validateUnitPairs(unitin, unitout);

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
