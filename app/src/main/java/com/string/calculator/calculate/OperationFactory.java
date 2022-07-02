package com.string.calculator.calculate;

import static java.lang.Math.multiplyExact;

import com.string.calculator.Fraction;

public class OperationFactory {

  public ArithmeticOperation create(String leftValue, String rightValue) {
    if (hasDivide(leftValue, rightValue)) {
      Fraction left = new Fraction(leftValue);
      Fraction right = new Fraction(rightValue);
      return new FractionOperation(left, right);
    }

    if (isPrimitive(leftValue, rightValue)) {
      return new PrimitiveOperation(leftValue, rightValue);
    }
    return new BigIntegerOperation(leftValue, rightValue);
  }

  private boolean isPrimitive(String leftValue, String rightValue) {
    try {
      multiplyExact(Long.parseLong(leftValue), Long.parseLong(rightValue));
      return true;
    } catch (ArithmeticException | NumberFormatException e) {
      return false;
    }
  }

  private boolean hasDivide(String leftValue, String rightValue) {
    return leftValue.contains("/") || rightValue.contains("/");
  }

}
