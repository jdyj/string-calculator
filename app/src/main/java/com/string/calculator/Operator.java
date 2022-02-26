package com.string.calculator;

import com.string.calculator.calculate.BigIntegerCalculate;
import com.string.calculator.calculate.Calculate;
import com.string.calculator.calculate.PrimitiveCalculate;
import java.util.Stack;

public class Operator {

  private Stack<OperatorSign> operatorSignStack = new Stack<>();
  private final Number number;
  private Calculate calculate;

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

    if (Calculate.checkValueInLongType(leftValue) && Calculate.checkValueInLongType(rightValue)) {
      calculate = new PrimitiveCalculate(leftValue, rightValue);
    }

    if (!Calculate.checkValueInLongType(leftValue) || !Calculate.checkValueInLongType(rightValue)) {
      calculate = new BigIntegerCalculate(leftValue, rightValue);
    }

    String result = calculateOne(operator);
    number.addNumberToStack(result);
  }

  private String calculateOne(OperatorSign operator) {
    String result = null;
    switch (operator) {
      case plus -> result = calculate.add();
      case subtract -> result = calculate.subtract();
      case multiply -> result = calculate.multiply();
    }
    return result;
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
