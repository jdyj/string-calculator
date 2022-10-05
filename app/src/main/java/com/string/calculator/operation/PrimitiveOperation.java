package com.string.calculator.operation;

import com.string.calculator.Number;

final class PrimitiveOperation implements ArithmeticOperation {

  private final long left;
  private final long right;
  private final int index;

  @Override
  public Number plus() {
    return toNumber(String.valueOf(left + right));
  }

  @Override
  public Number multiply() {
    return toNumber(String.valueOf(left * right));
  }

  @Override
  public Number divide() {
    long gcd = gcd(left, right);

    if (gcd == Math.abs(right)) {
      return toNumber(String.valueOf(left / right));
    } else if (gcd == Math.abs(left)) {
      return toNumber("1/" + (right / gcd));
    }

    long tempLeft = Math.abs(left / gcd);
    long tempRight = Math.abs(right / gcd);
    if (isNegative(left, right)) {
      return toNumber("-" + tempLeft + "/" + tempRight);
    }
    return toNumber(tempLeft + "/" + tempRight);
  }

  @Override
  public Number modular() {
    return toNumber(String.valueOf(left % right));
  }

  public PrimitiveOperation(Number left, Number right) {
    this.left = Long.parseLong(left.getValue());
    this.right = Long.parseLong(right.getValue());
    this.index = left.getIndex();
  }

  private long gcd(long a, long b) {
    long tempA = a;
    long tempB = b;

    if (tempA < 0) {
      tempA = Math.abs(tempA);
    }
    if (tempB < 0) {
      tempB = Math.abs(tempB);
    }

    long result = tempA % tempB;
    if (result == 0L) {
      return tempB;
    }

    return gcd(tempB, result);
  }

  private boolean isNegative(long left, long right) {

    boolean leftSign = left < 0;
    boolean rightSign = right < 0;

    return leftSign != rightSign;
  }

  private Number toNumber(String value) {
    return new Number(value, index);
  }

}
