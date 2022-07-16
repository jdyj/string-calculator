package com.string.calculator.calculate;

import static java.lang.Math.multiplyExact;

import com.string.calculator.Fraction;
import com.string.calculator.Number;

public class OperationFactory {

  public ArithmeticOperation create(Number leftValue, Number rightValue) {
    if (leftValue.hasDivide() || rightValue.hasDivide()) {
      Fraction left = new Fraction(leftValue);
      Fraction right = new Fraction(rightValue);
      return new FractionOperation(left, right);
    }

    if (leftValue.isPrimitive(rightValue)) {
      return new PrimitiveOperation(leftValue, rightValue);
    }
    return new BigIntegerOperation(leftValue, rightValue);
  }

}
