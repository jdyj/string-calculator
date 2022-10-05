package com.string.calculator;

import com.string.calculator.expression.Expression;
import com.string.calculator.expression.FractionExpression;
import com.string.calculator.expression.LongExpression;

public class ExpressionToString {

  private final Expression expression;

  public ExpressionToString(Expression expression) {
    this.expression = expression;
  }

  @Override
  public String toString() {

    if (expression instanceof LongExpression) {
      Long number = ((LongExpression) expression).getNumber();
      return String.valueOf(number);
    } else if (expression instanceof FractionExpression) {
      Long numerator = ((FractionExpression) expression).getNumerator();
      Long denominator = ((FractionExpression) expression).getDenominator();
    }

    throw new IllegalStateException();
  }


}
