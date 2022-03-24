package com.string.calculator.calculate;

import java.math.BigInteger;

public class BigIntegerOperation implements ArithmeticOperation {

  private final BigInteger left;
  private final BigInteger right;

  @Override
  public String add() {
    return left.add(right).toString();
  }

  @Override
  public String subtract() {
    return left.subtract(right).toString();
  }

  @Override
  public String multiply() {
    return left.multiply(right).toString();
  }

  public BigIntegerOperation(String leftValue, String rightValue) {
    left = new BigInteger(leftValue);
    right = new BigInteger(rightValue);
  }
}
