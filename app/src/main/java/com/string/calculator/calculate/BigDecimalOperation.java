package com.string.calculator.calculate;

import java.math.BigDecimal;

public class BigDecimalOperation implements ArithmeticOperation {

  private final BigDecimal left;
  private final BigDecimal right;

  @Override
  public String plus() {
    return left.add(right).toPlainString();
  }

  @Override
  public String subtract() {
    return left.subtract(right).toPlainString();
  }

  @Override
  public String multiply() {
    return left.multiply(right).toPlainString();
  }

  @Override
  public String divide() {
    return left.divide(right).toPlainString();
  }

  public BigDecimalOperation(String leftValue, String rightValue) {
    left = new BigDecimal(leftValue).stripTrailingZeros();
    right = new BigDecimal(rightValue).stripTrailingZeros();
  }
}
