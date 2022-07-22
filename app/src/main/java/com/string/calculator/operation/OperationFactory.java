package com.string.calculator.operation;

import com.string.calculator.Number;

public class OperationFactory {

  public ArithmeticOperation create(Number leftValue, Number rightValue) {
    if (isFraction(leftValue, rightValue)) {
      return new FractionOperation(leftValue, rightValue);
    }

    if (leftValue.isPrimitive(rightValue)) {
      return new PrimitiveOperation(leftValue, rightValue);
    }
    return new BigIntegerOperation(leftValue, rightValue);
  }

  private boolean isFraction(Number leftValue, Number rightValue) {
    boolean isLeftFraction = leftValue.hasDivide();
    boolean isRightFraction = rightValue.hasDivide();
    return isLeftFraction || isRightFraction;
  }

}
