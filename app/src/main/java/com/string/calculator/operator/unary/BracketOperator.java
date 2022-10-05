package com.string.calculator.operator.unary;

import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.LongExpression;

public class BracketOperator implements UnaryOperator {

  @Override
  public Expression apply(Expression expression) {

    return expression.evaluate();
  }
}
