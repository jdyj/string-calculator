package com.string.calculator.expression;

public class ExpressionFactory {

  public Expression create(String value, Integer index) {
    return new LongExpression(Long.parseLong(value), index);
  }

}
