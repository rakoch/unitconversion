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
	 * given value and check it matches given answer (allowing .5 delta).
	 * 
	 * @param value conversion input number
	 * @param answer conversion input number to check
	 * @return partially completed UnitConversionResult minus the original request
	 *         which is left up to caller to populate. TODO make a new class with
	 *         just result status and message and just return that.
	 */
	public UnitConversionResult convertAndCheck(String value, String answer) {
		UnitConversionResult result = new UnitConversionResult();
		
		// guard validation & String to BD conversion
		// TODO compress/make more efficient
		if (value == null || value.isBlank()) {
			result.setResultStatus(ResultStatus.INCORRECT);
			result.setMsg(String.format("Inputted Answer: '%s' incorrect", value));
			return result;
		}
		BigDecimal valueBD = validateAndGetBigDecimalFromString(value, result);
		ResultStatus rs = result.getResultStatus();
		if((rs != null) && (rs != ResultStatus.CORRECT)) {
			return result;
		}
		BigDecimal answerBD = validateAndGetBigDecimalFromString(answer, result);
		rs = result.getResultStatus();
		if((rs != null) && (rs != ResultStatus.CORRECT)) {
			return result;
		}
		
		if (unitConversionModel != null) {

			String formula = unitConversionModel.getFormula();
			String formulaExpression = String.format(formula, valueBD);
			Expression expression = new Expression(formulaExpression);
			// expression.setPrecision(2);
			BigDecimal systemAnswer = expression.eval();
			systemAnswer = systemAnswer.setScale(2, RoundingMode.HALF_UP);
			answerBD = answerBD.setScale(2, RoundingMode.HALF_UP);
			BigDecimal delta = systemAnswer.subtract(answerBD);
		    // delta is larger than -.5 and less than .5
			if ( delta.compareTo(new BigDecimal(-.5)) > 0 && delta.compareTo(BigDecimal.valueOf(.5)) < 0) {
				result.setResultStatus(ResultStatus.CORRECT);
				result.setMsg(String.format("System and input answer equal: %s", systemAnswer.toString()));
			} else {
				result.setResultStatus(ResultStatus.INCORRECT);
				result.setMsg(String.format("System Answer : %s; Inputted Answer: %s", systemAnswer.toString(),
						answerBD.toString()));
			}

		} else {
			result.setResultStatus(ResultStatus.INVALID);
			result.setMsg(
					String.format("Conversion formula record missing for unit %s to %s conversion request of value: %s",
							this.unitInOrigRequest, this.unitOutOrigRequest, value));
		}
		return result;
	}

	private BigDecimal validateAndGetBigDecimalFromString(String value, UnitConversionResult result) {
		BigDecimal bdValue = null;
		try {
			// TODO implement or get NaN rather than use exception
			bdValue = new BigDecimal(value);
		} catch (NumberFormatException e) {
			result.setResultStatus(ResultStatus.INCORRECT);
			result.setMsg(String.format("Inputted Answer: '%s' incorrect", value));
		}
		return bdValue;
	}
}