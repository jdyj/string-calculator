package com.string.calculator;

import java.math.BigInteger;

public class Calculate {

  private final String leftValue;
  private final String rightValue;

  public Calculate(String leftValue, String rightValue) {
    this.leftValue = leftValue;
    this.rightValue = rightValue;
  }

  public String add() {
    BigInteger left = new BigInteger(leftValue);
    BigInteger right = new BigInteger(rightValue);

    return left.add(right).toString();
  }

  public String subtract() {
    BigInteger left = new BigInteger(leftValue);
    BigInteger right = new BigInteger(rightValue);

    return left.subtract(right).toString();
  }

  public String multiply() {
    BigInteger left = new BigInteger(leftValue);
    BigInteger right = new BigInteger(rightValue);

    return left.multiply(right).toString();
  }

}
