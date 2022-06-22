package com.string.calculator.calculate;

import java.math.BigDecimal;
import java.math.BigInteger;

public class BigIntegerOperation implements ArithmeticOperation {

  private final BigInteger left;
  private final BigInteger right;

  @Override
  public String plus() {
    return left.add(right).toString();
  }

  @Override
  public String multiply() {
    return left.multiply(right).toString();
  }

  @Override
  public String divide() {
    return left.divide(right).toString();
  }

  @Override
  public String modular() {
    if (left.compareTo(right) < 0) {
      return right.mod(left).toString();
    }
    return left.mod(right).toString();
  }

  public BigIntegerOperation(String leftValue, String rightValue) {
    left = new BigInteger(leftValue);
    right = new BigInteger(rightValue);
  }
}
