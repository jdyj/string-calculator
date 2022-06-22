package com.string.calculator.calculate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

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
    DecimalFormat decimalFormat = new DecimalFormat("0",
        DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    decimalFormat.setMaximumFractionDigits(340);
    return decimalFormat.format(left / right);
  }

  @Override
  public String modular() {
    return String.valueOf(left % right);
  }

  public PrimitiveOperation(String leftValue, String rightValue) {
    left = Long.parseLong(leftValue);
    right = Long.parseLong(rightValue);
  }
}
