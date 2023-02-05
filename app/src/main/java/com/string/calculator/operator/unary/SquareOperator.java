package com.string.calculator.operator.unary;

import com.string.calculator.expression.Expression;
import com.string.calculator.expression.LongExpression;

public class SquareOperator implements UnaryOperator {

  @Override
  public Expression apply(Expression expression) {

    if (expression instanceof LongExpression) {
      Long number = ((LongExpression) expression).getNumber();
      Integer index = ((LongExpression) expression).getIndex();
      return new LongExpression(number * number, index);
    }

    return null;
  }
}
