package com.string.calculator.calculate;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;

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
    DecimalFormat decimalFormat = new DecimalFormat("0",
        DecimalFormatSymbols.getInstance(Locale.ENGLISH));
    decimalFormat.setMaximumFractionDigits(340);
    return decimalFormat.format(left / right);
  }

  public PrimitiveOperation(String leftValue, String rightValue) {
    numberFormat.setGroupingUsed(false);
    left = Double.parseDouble(leftValue);
    right = Double.parseDouble(rightValue);
  }
}
