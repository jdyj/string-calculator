package com.string.calculator;

public class Fraction {

  private final String numerator;
  private final String denominator;

  public Fraction(String value) {
    if (hasDivide(value)) {
      String[] values = value.split("/");
      this.numerator = values[0];
      this.denominator = values[1];
    } else {
      this.numerator = value;
      this.denominator = "1";
    }
  }

  public String getNumerator() {
    return numerator;
  }

  public String getDenominator() {
    return denominator;
  }

  private boolean hasDivide(String value) {
    return value.contains("/");
  }
}
