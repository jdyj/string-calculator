package com.string.calculator.calculate;


public class PrimitiveOperation implements ArithmeticOperation {

  private final long left;
  private final long right;

  @Override
  public String plus() {
    return String.valueOf(left + right);
  }

  @Override
  public String multiply() {
    return String.valueOf(left * right);
  }

  @Override
  public String divide() {
    long gcd = gcd(left, right);

    if (gcd == Math.abs(right)) {
      return String.valueOf(left / right);
    } else if (gcd == Math.abs(left)) {
      return "1/" + (right / gcd);
    }

    long tempLeft = Math.abs(left / gcd);
    long tempRight = Math.abs(right / gcd);
    if (isNegative(left, right)) {
      return "-" + tempLeft + "/" + tempRight;
    }
    return tempLeft + "/" + tempRight;
  }

  @Override
  public String modular() {
    return String.valueOf(left % right);
  }

  public PrimitiveOperation(String leftValue, String rightValue) {
    left = Long.parseLong(leftValue);
    right = Long.parseLong(rightValue);
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

}
