package com.rak.unitconversion.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class UnitConversionService {

	public List<UnitConversionResult> convertUnits(List<UnitConversionRequest> requestList) {

		List<UnitConversionResult> resultList = new ArrayList<UnitConversionResult>();

		for (UnitConversionRequest request : requestList) {
			AbstractUnitConversionCmd conversionCmd;
			UnitConversionResult result = null;
			try {
				conversionCmd = UnitConversionFactory.getConversionCommand(request.getInUnit(), request.getOutUnit());
				result = conversionCmd.convertAndCheck(request.getValue(), request.getAnswer());

			} catch (Exception e) {
				result = new UnitConversionResult();
				result.setResultStatus(ResultStatus.INVALID);
				result.setMsg(e.getMessage() != null ? e.getMessage() : e.toString());
			}
			result.setRequest(request);
			resultList.add(result);
		}

		return resultList;
	}

}
