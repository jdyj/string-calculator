package com.string.calculator.operator.bi;

import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.FractionExpression;
import com.string.calculator.expression.LongExpression;
import com.string.calculator.expression.UnaryExpression;

final class DivideOperator implements BiOperator {

  @Override
  public Expression apply(UnaryExpression left, UnaryExpression right) {
    return null;
  }

  @Override
  public Expression apply(UnaryExpression left, BinaryExpression right) {
    return null;
  }

  @Override
  public Expression apply(BinaryExpression left, UnaryExpression right) {
    return null;
  }

  @Override
  public Expression apply(BinaryExpression left, BinaryExpression right) {
    return null;
  }

  @Override
  public Expression apply(LongExpression left, LongExpression right) {
    return new LongExpression(left.getNumber() / right.getNumber(), left.getIndex());
  }

  @Override
  public Expression apply(LongExpression left, FractionExpression right) {
    return null;
  }

  @Override
  public Expression apply(FractionExpression left, LongExpression right) {
    return null;
  }

  @Override
  public Expression apply(FractionExpression left, FractionExpression right) {
    return null;
  }
}
