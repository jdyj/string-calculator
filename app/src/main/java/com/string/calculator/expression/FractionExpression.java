package com.string.calculator.expression;

public class FractionExpression implements Expression {

  private final Long numerator;
  private final Long denominator;

  public FractionExpression(Long numerator, Long denominator) {
    this.numerator = numerator;
    this.denominator = denominator;
  }

  public Long getNumerator() {
    return numerator;
  }

  public Long getDenominator() {
    return denominator;
  }

  @Override
  public Expression evaluate() {
    return this;
  }
}
