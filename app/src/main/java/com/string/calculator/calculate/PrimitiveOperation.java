package com.string.calculator.calculate;

import java.text.NumberFormat;

public class PrimitiveOperation implements ArithmeticOperation {

  private final NumberFormat numberFormat = NumberFormat.getInstance();
  private final double left;
  private final double right;

  @Override
  public String plus() {
    return numberFormat.format(left + right);
  }

  @Override
  public String subtract() {
    return numberFormat.format(left - right);
  }

  @Override
  public String multiply() {
    return numberFormat.format(left * right);
  }

  @Override
  public String divide() {
    return numberFormat.format(left / right);
  }

  public PrimitiveOperation(String leftValue, String rightValue) {
    numberFormat.setGroupingUsed(false);
    left = Double.parseDouble(leftValue);
    right = Double.parseDouble(rightValue);
  }
}
