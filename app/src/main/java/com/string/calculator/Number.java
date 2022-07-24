package com.string.calculator;

import static java.lang.Math.multiplyExact;

public class Number {

  private final String value;

  public Number(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }

  public boolean hasDivide() {
    return value.contains("/");
  }

  public boolean isNegative() {
    return value.contains("-");
  }

  public String getValueWithoutMinusSign() {
    return value.substring(1);
  }

  public boolean isPrimitive(Number number) {
    try {
      multiplyExact(Long.parseLong(value), Long.parseLong(number.getValue()));
      return true;
    } catch (ArithmeticException | NumberFormatException e) {
      return false;
    }
  }

}
