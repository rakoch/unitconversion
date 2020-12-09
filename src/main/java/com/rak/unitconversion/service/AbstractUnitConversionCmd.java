package com.rak.unitconversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.rak.unitconversion.model.UnitConversionModel;
import com.udojava.evalex.Expression;

public abstract class AbstractUnitConversionCmd {
	
	protected UnitConversionModel unitConversionModel;
	
	public UnitConversionResult convert(UnitConversionRequest request) {
		UnitConversionResult result = new UnitConversionResult();
		result.setRequest(request);
		
		
		if(unitConversionModel != null) {

			String formula = unitConversionModel.getFormula();
			String formulaExpression = String.format(formula, request.getValue());
			Expression expression = new Expression(formulaExpression);
			//expression.setPrecision(2);
			BigDecimal systemResult = expression.eval();
			systemResult = systemResult.setScale(2, RoundingMode.CEILING); 
			BigDecimal inputResult;
			try {
				inputResult = new BigDecimal(request.getValue());
			} catch (NumberFormatException e) {
				result.setResultStatus(ResultStatus.INCORRECT);
				result.setMsg(String.format("Inputted Answer: %s incorrect",request.getValue()));
				return result;
			}
			inputResult = inputResult.setScale(2, RoundingMode.CEILING);
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
		return result;
	}
}