package com.string.calculator.expression;

import com.string.calculator.Indexed;

public class FractionExpression implements Expression {

  private final Long numerator;
  private final Long denominator;
  private final Integer index;

  public FractionExpression(Long numerator, Long denominator, Integer index) {
    this.numerator = numerator;
    this.denominator = denominator;
    this.index = index;
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

  @Override
  public Integer value() {
    return index;
  }

  @Override
  public int compareTo(Indexed o) {
    return this.value().compareTo(o.value());
  }
}
