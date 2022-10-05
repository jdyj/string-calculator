package com.string.calculator.operator.bi;

import com.string.calculator.expression.Expression;
import com.string.calculator.expression.FractionExpression;
import com.string.calculator.expression.LongExpression;
import com.string.calculator.operator.bi.BiOperator;

public class PlusOperator implements BiOperator {

  @Override
  public Expression apply(LongExpression left, LongExpression right) {
    return new LongExpression(left.getNumber() + right.getNumber(), left.getIndex());
  }

  @Override
  public Expression apply(LongExpression left, FractionExpression right) {
    return new FractionExpression(left.getNumber() * right.getDenominator() + right.getNumerator(),
        right.getDenominator());
  }

  @Override
  public Expression apply(FractionExpression left, LongExpression right) {
    return this.apply(right, left);
  }

  @Override
  public Expression apply(FractionExpression left, FractionExpression right) {
    return null;
  }

  @Override
  public String toString() {
    return "+";
  }
}
