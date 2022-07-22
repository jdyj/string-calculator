package com.string.calculator.calculate;

import com.string.calculator.Number;
import java.math.BigInteger;

final class BigIntegerOperation implements ArithmeticOperation {

  private final BigInteger left;
  private final BigInteger right;

  @Override
  public Number plus() {
    return toNumber(left.add(right).toString());
  }

  @Override
  public Number multiply() {
    return toNumber(left.multiply(right).toString());
  }

  @Override
  public Number divide() {
    BigInteger gcd = left.gcd(right);
    if (gcd.equals(right.abs())) {
      return toNumber(left.divide(right).toString());
    } else if (gcd.equals(left.abs())) {
      return toNumber("1/" + right.divide(gcd));
    }

    BigInteger tempLeft = left.divide(gcd).abs();
    BigInteger tempRight = right.divide(gcd).abs();
    if (isNegative(left, right)) {
      return toNumber("-" + tempLeft + "/" + tempRight);
    }
    return toNumber(tempLeft + "/" + tempRight);
  }

  @Override
  public Number modular() {
    if (left.compareTo(right) < 0) {
      return toNumber(right.mod(left).toString());
    }
    return toNumber(left.mod(right).toString());
  }

  public BigIntegerOperation(Number left, Number right) {
    this.left = new BigInteger(left.getValue());
    this.right = new BigInteger(right.getValue());
  }

  private boolean isNegative(BigInteger left, BigInteger right) {
    return left.signum() != right.signum();
  }

  private Number toNumber(String value) {
    return new Number(value);
  }
}
