package com.string.calculator;

import java.util.Stack;

public class Operator {

  private Stack<OperatorSign> operatorSignStack = new Stack<>();
  private final Number number;

  public Operator(Number number) {
    this.number = number;
  }

  public void addOperatorSignToStack(Character c) {
    if (OperatorSign.findOperator(c)) {
      operatorSignStack.add(OperatorSign.valueOf(c));
    }
  }

  public void ifExistHighOperatorCalculate() {
    if (operatorSignStack.isEmpty()) {
      return;
    }

    if (operatorSignStack.size() >= number.size()) {
      return;
    }

    OperatorSign lastOperator = operatorSignStack.peek();
    if (lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply) {
      calculateOneAndAddNumberToStack();
    }

  }

  public String calculateLeftover() {

    number.reverseNumberStack();
    reverseOperatorStack();

    while (number.size() > 1) {
      calculateOneAndAddNumberToStack();
    }
    return number.getNumber();
  }

  private void calculateOneAndAddNumberToStack() {
    String leftValue = this.number.getNumber();
    String rightValue = this.number.getNumber();

    OperatorSign operator = operatorSignStack.pop();

    Calculate calculate = new Calculate(leftValue, rightValue);

    String result = null;
    switch (operator) {
      case plus -> result = calculate.add();
      case subtract -> result = calculate.subtract();
      case multiply -> result = calculate.multiply();
    }

    number.addNumberToStack(result);
  }

  private void reverseOperatorStack() {
    Stack<OperatorSign> temp = new Stack<>();

    while (!operatorSignStack.isEmpty()) {
      OperatorSign pop = operatorSignStack.pop();
      temp.add(pop);
    }
    operatorSignStack = temp;
  }

}
