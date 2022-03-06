package com.string.calculator.calculate;

import static java.lang.Math.multiplyExact;

public class CalculateFactory {

  public Calculate create(String leftValue, String rightValue) {
    if (isPrimitive(leftValue, rightValue)) {
      return new PrimitiveCalculate(leftValue, rightValue);
    }
    return new BigIntegerCalculate(leftValue, rightValue);
  }

  private boolean isPrimitive(String leftValue, String rightValue) {
    try {
      multiplyExact(Long.parseLong(leftValue), Long.parseLong(rightValue));
      return true;
    } catch (ArithmeticException | NumberFormatException e) {
      return false;
    }
  }
}
