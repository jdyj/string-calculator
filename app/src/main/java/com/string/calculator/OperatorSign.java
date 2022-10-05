package com.string.calculator;

public class OperatorSign {

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
}
