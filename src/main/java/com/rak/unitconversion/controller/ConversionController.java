package com.rak.unitconversion.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.rak.unitconversion.model.TemperatureUnitEnum;
import com.rak.unitconversion.service.UnitConversionService;
import com.rak.unitconversion.service.TemperatureConversionRequest;
import com.rak.unitconversion.service.TemperatureConversionResult;

@RestController
@RequestMapping("/")
public class ConversionController {
	@Autowired
	UnitConversionService service;


	// TODO pass in strings and create factory that delivers command pattern that is an interface or abstract class for results...
	
	@GetMapping("/{inUnit}/{outUnit}/{value}")
	public List<TemperatureConversionResult> getConversion(@PathVariable(required = true) TemperatureUnitEnum inUnit, 
							   @PathVariable(required = true) TemperatureUnitEnum outUnit, @PathVariable(required = true) BigDecimal foo) {
		List<TemperatureConversionRequest> requestList = new ArrayList<TemperatureConversionRequest>();
		List<TemperatureConversionResult> resultList = service.convertTemperature(requestList);
		return resultList;

	}



}
