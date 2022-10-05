package com.string.calculator;

public class History {

  private final Number left;
  private final Operator operatorSign;
  private final Number right;
  private final Number result;

  public History(Number left, Operator operatorSign, Number right,
      Number result) {
    this.left = left;
    this.operatorSign = operatorSign;
    this.right = right;
    this.result = result;
  }

  public Number getLeft() {
    return left;
  }

  public Operator getOperatorSign() {
    return operatorSign;
  }

  public Number getRight() {
    return right;
  }

  public Number getResult() {
    return result;
  }
}
