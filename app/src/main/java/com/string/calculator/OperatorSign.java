package com.string.calculator;

public class OperatorSign implements Indexed {

  private final Operator operator;
  private final Integer index;

  public OperatorSign(Operator operator, Integer index) {
    this.operator = operator;
    this.index = index;
  }

  public Operator getOperator() {
    return operator;
  }

  public Integer getIndex() {
    return index;
  }

  @Override
  public Integer value() {
    return index;
  }

  @Override
  public int compareTo(Indexed o) {
    return this.value().compareTo(o.value());
  }

  @Override
  public String toString() {
    return String.valueOf(operator.getSign());
  }
}
