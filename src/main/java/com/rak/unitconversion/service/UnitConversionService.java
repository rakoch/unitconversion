package com.rak.unitconversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rak.unitconversion.model.TemperatureConversion;
import com.rak.unitconversion.repository.TemperatureConversionRepository;
import com.udojava.evalex.Expression;

@Component
public class UnitConversionService {

	@Autowired
	TemperatureConversionRepository tempConvRepository;

	
	public List<TemperatureConversionResult> convertTemperature(List<TemperatureConversionRequest> requestList) {
		
		List<TemperatureConversionResult> resultList = new ArrayList<TemperatureConversionResult>();
		
		for(TemperatureConversionRequest request : requestList) {
			TemperatureConversion tempConversion = tempConvRepository.findByUnitInAndUnitOut(request.getInputUnit(), request.getTargetUnit());

			TemperatureConversionResult result = new TemperatureConversionResult();
			result.setRequest(request);
			if(tempConversion != null) {
				String formula = tempConversion.getFormula();
				String formulaExpression = String.format(formula, request.getValue());
				Expression expression = new Expression(formulaExpression);
				//expression.setPrecision(2);
				BigDecimal systemResult = expression.eval();
				systemResult = systemResult.setScale(2, RoundingMode.CEILING); 
				BigDecimal inputResult = request.getValue().setScale(2, RoundingMode.CEILING);
				if(systemResult.equals(inputResult)) {
					result.setResultStatus(ResultStatus.CORRECT);
					result.setMsg(String.format("System and input answer equal: %s",systemResult.toString()));
				}
				else {
					result.setResultStatus(ResultStatus.INCORRECT);
					result.setMsg(String.format("System Answer : %s; Inputted Answer: %s",systemResult.toString(), inputResult.toString()));
				}
			
			}
			else {
				result.setResultStatus(ResultStatus.INVALID);
				result.setMsg(String.format("Conversion not found for request: %s",request));
			}
			
			resultList.add(result);
		}
		
		return resultList;
	}


}
