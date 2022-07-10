package com.string.calculator.calculate;

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
    BigInteger gcd = left.gcd(right);
    if (gcd.equals(left.abs())) {
      return "1/" + right.divide(gcd);
    } else if (gcd.equals(right.abs())) {
      return left.divide(right).toString();
    }

    BigInteger tempLeft = left.divide(gcd).abs();
    BigInteger tempRight = right.divide(gcd).abs();
    if (isNegative(left, right)) {
      return "-" + tempLeft + "/" + tempRight;
    }
    return tempLeft + "/" + tempRight;
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

  private boolean isNegative(BigInteger left, BigInteger right) {
    return left.signum() != right.signum();
  }
}
