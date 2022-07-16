package com.string.calculator;

public class Fraction {

  private final Number numerator;
  private final Number denominator;

  public Fraction(Number number) {
    if (number.hasDivide()) {
      String[] values = number.getValue().split("/");
      this.numerator = new Number(values[0]);
      this.denominator = new Number(values[1]);
    } else {
      this.numerator = new Number(number.getValue());
      this.denominator = new Number("1");
    }
  }

  public Number getNumerator() {
    return numerator;
  }

  public Number getDenominator() {
    return denominator;
  }
}
