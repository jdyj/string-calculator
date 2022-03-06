package com.string.calculator.calculate;

public class PrimitiveCalculate implements Calculate {

  private final long left;
  private final long right;

  @Override
  public String add() {
    return Long.toString(left + right);
  }

  @Override
  public String subtract() {
    return Long.toString(left - right);
  }

  @Override
  public String multiply() {
    return Long.toString(left * right);
  }

  public PrimitiveCalculate(String leftValue, String rightValue) {
    left = Long.parseLong(leftValue);
    right = Long.parseLong(rightValue);
  }
}
