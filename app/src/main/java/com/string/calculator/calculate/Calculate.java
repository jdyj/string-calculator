package com.string.calculator.calculate;

import java.math.BigInteger;

public abstract class Calculate {

  private final String leftValue;
  private final String rightValue;

  public abstract String add();

  public abstract String subtract();

  public abstract String multiply();

  public Calculate(String leftValue, String rightValue) {
    this.leftValue = leftValue;
    this.rightValue = rightValue;
  }

  public static boolean checkValueInLongType(String value) {
    try {
      Long.valueOf(value);
    } catch (NumberFormatException e) {
      return false;
    }
    return true;
  }

}
