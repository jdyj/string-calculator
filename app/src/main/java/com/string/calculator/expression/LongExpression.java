package com.string.calculator.expression;

import com.string.calculator.Indexed;

public class LongExpression implements Expression, Indexed {

  private final Long number;

  @Override
  public Integer value() {
    return null;
  }

  private final Integer index;

  public LongExpression(Long number, Integer index) {
    this.number = number;
    this.index = index;
  }

  public Long getNumber() {
    return number;
  }

  public Integer getIndex() {
    return index;
  }

  @Override
  public Expression evaluate() {
    return this;
  }

  @Override
  public String toString() {
    return number.toString();
  }

  @Override
  public int compareTo(Indexed o) {
    return this.value().compareTo(o.value());
  }
}
