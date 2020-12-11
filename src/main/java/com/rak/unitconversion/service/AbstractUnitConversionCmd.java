package com.rak.unitconversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

import com.rak.unitconversion.model.UnitConversionModel;
import com.udojava.evalex.Expression;

public abstract class AbstractUnitConversionCmd {

	protected UnitConversionModel unitConversionModel;

	public String unitInOrigRequest;
	public String unitOutOrigRequest;

	protected AbstractUnitConversionCmd(String unitInOrigRequest, String unitOutOrigRequest) {
		super();
		this.unitInOrigRequest = unitInOrigRequest;
		this.unitOutOrigRequest = unitOutOrigRequest;
	}

	/**
	 * For the units for which this command class was instantiated, convert the
	 * given value.
	 * 
	 * @param value
	 * @return partially completed UnitConversionResult minus the original request
	 *         which is left up to caller to populate. TODO make a new class with
	 *         just result status and message and just return that.
	 */
	public UnitConversionResult convert(String value) {
		UnitConversionResult result = new UnitConversionResult();

		if (value == null || value.isBlank()) {
			result.setResultStatus(ResultStatus.INCORRECT);
			result.setMsg(String.format("Inputted Answer: '%s' incorrect", value));
			return result;
		}
		BigDecimal inputResult;
		try {
			inputResult = new BigDecimal(value);
		} catch (NumberFormatException e) {
			result.setResultStatus(ResultStatus.INCORRECT);
			result.setMsg(String.format("Inputted Answer: '%s' incorrect", value));
			return result;
		}

		if (unitConversionModel != null) {

			String formula = unitConversionModel.getFormula();
			String formulaExpression = String.format(formula, inputResult);
			Expression expression = new Expression(formulaExpression);
			// expression.setPrecision(2);
			BigDecimal systemResult = expression.eval();
			systemResult = systemResult.setScale(2, RoundingMode.CEILING);

			inputResult = inputResult.setScale(2, RoundingMode.CEILING);
			if (systemResult.equals(inputResult)) {
				result.setResultStatus(ResultStatus.CORRECT);
				result.setMsg(String.format("System and input answer equal: %s", systemResult.toString()));
			} else {
				result.setResultStatus(ResultStatus.INCORRECT);
				result.setMsg(String.format("System Answer : %s; Inputted Answer: %s", systemResult.toString(),
						inputResult.toString()));
			}

		} else {
			result.setResultStatus(ResultStatus.INVALID);
			result.setMsg(
					String.format("Conversion formula record missing for unit %s to %s conversion request of value: %s",
							this.unitInOrigRequest, this.unitOutOrigRequest, value));
		}
		return result;
	}
}