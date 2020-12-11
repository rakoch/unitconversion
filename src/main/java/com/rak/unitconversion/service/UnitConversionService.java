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
				result = conversionCmd.convert(request.getValue());

			} catch (Exception e) {
				// TODO think of a better way maybe not used exceptions
				result = new UnitConversionResult();
				result.setRequest(request);
				result.setResultStatus(ResultStatus.INVALID);
				result.setMsg(e.getMessage() != null ? e.getMessage() : e.toString());
			}
			resultList.add(result);
		}

		return resultList;
	}

}
