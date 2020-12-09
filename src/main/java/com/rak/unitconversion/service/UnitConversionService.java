package com.rak.unitconversion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rak.unitconversion.repository.TemperatureConversionRepository;

@Component
public class UnitConversionService {

	@Autowired
	TemperatureConversionRepository tempConvRepository;

	
	public List<UnitConversionResult> convertUnits(List<UnitConversionRequest> requestList) {
		
		List<UnitConversionResult> resultList = new ArrayList<UnitConversionResult>();
		
		for(UnitConversionRequest request : requestList) {

			AbstractUnitConversionCmd conversionCmd;
			try {
				 conversionCmd =
						UnitConversionFactory.getConversionCommand(request.getInUnit(), request.getOutUnit());
			} catch (Exception e) {
				// TODO think of a better way maybe not used exceptions
				UnitConversionResult result = new UnitConversionResult();
				result.setRequest(request);
				result.setResultStatus(ResultStatus.INVALID);
				result.setMsg(e.getMessage());
				resultList.add(result);
				continue;
			} 

			UnitConversionResult result = conversionCmd.convert(request);
			
			resultList.add(result);
		}
		
		return resultList;
	}


}
