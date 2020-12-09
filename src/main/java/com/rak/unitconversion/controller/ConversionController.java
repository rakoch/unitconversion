package com.rak.unitconversion.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rak.unitconversion.service.UnitConversionRequest;
import com.rak.unitconversion.service.UnitConversionResult;
import com.rak.unitconversion.service.UnitConversionService;

@RestController
@RequestMapping("/")
public class ConversionController {
	@Autowired
	UnitConversionService service;


	// TODO pass in strings and create factory that delivers command pattern that is an interface or abstract class for results...
	
	@GetMapping("/{inUnit}/{outUnit}/{value}")
	public List<UnitConversionResult> getConversion(@PathVariable(required = true) String inUnit, 
							   @PathVariable(required = true) String outUnit, @PathVariable(required = true) String value) {
		List<UnitConversionRequest> requestList = new ArrayList<UnitConversionRequest>();
		UnitConversionRequest request = new UnitConversionRequest();
		request.setInUnit(inUnit);
		request.setOutUnit(outUnit);
		List<UnitConversionResult> resultList = service.convertUnits(requestList);
		return resultList;

	}



}
