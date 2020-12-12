package com.rak.unitconversion.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Test;

import com.udojava.evalex.Expression;

class MathExpressionTest {

	Map<String, String> answerFormulaMap = Map.of("543.94","84.2 + 459.67", "6.1", "25.6 / 4.167");

	@Test
	void test() {
		Set<String> keySet = answerFormulaMap.keySet();
		for(String key : keySet) {
			String formulaExpression = answerFormulaMap.get(key);
			Expression expression = new Expression(formulaExpression);
			BigDecimal systemResult = expression.eval();
			systemResult = systemResult.setScale(2, RoundingMode.HALF_UP);
			BigDecimal answer = new BigDecimal(key);
			System.out.println(String.format("system answer = %s; given answer = %s", systemResult.toString(), answer.toString()));
			answer = answer.setScale(2, RoundingMode.HALF_UP);
			BigDecimal delta = systemResult.subtract(answer);
		    // delta is larger than -.5 and less than .5
			if ( delta.compareTo(new BigDecimal(-.5)) > 0 && delta.compareTo(BigDecimal.valueOf(.5)) < 0) {
				System.out.println("Woot - equal!");
			}
			
		}
	}

}
