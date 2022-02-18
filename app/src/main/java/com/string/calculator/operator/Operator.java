package com.string.calculator.operator;

import com.string.calculator.Number;
import com.string.calculator.Oper;
import java.util.Stack;

public class Operator {

  private final Stack<Character> operatorStack = new Stack<>();
  private final Number number;

  public Operator(Number number) {
    this.number = number;
  }

  public void addOperatorToStack(char c) {
    if (Oper.findOperator(c)) {
      stackAdd(c);
    }
  }

  public void stackAdd(Character sign) {
    number.checkNumberStack();
    checkPriority(sign);
    number.clear();
  }

  public void checkPriority(char sign) {
    if (!operatorStack.isEmpty()) {
      Character peek;
      switch (sign) {
        case '+', '-', '*', '/' -> popLastOperator();
        case ')' -> {
          while (!operatorStack.isEmpty()) {
            peek = operatorStack.peek();
            if (peek.equals('(')) {
              operatorStack.pop();
              break;
            }
            calculateOne();
          }
        }
      }
    }
    add(sign);
  }

  public void add(char sign) {
    operatorStack.add(sign);
    if (sign == '-') {
      operatorStack.pop();
      operatorStack.add('+');
    }
    if (sign == ')') {
      operatorStack.pop();
    }
  }

  public void calculateOne() {
    String leftValue = this.number.getNumber();
    String rightValue = this.number.getNumber();

    Character operator = operatorStack.pop();
    select(leftValue, rightValue, operator);
  }

  public void select(String leftValue,
      String rightValue,
      Character operator) {

    String result = null;
    if (operator.equals('+')) {
      Plus plus = new Plus(leftValue, rightValue);
      result = plus.add();
    }
    if (operator.equals('*')) {
//      result = multiply(leftValue, rightValue);
    }
    if (operator.equals('/')) {
//      result = divide(leftValue, rightValue);
    }
    number.setNumber(result);
  }

  public void checkLastStack() {
    number.checkNumberStack();
    popLastOperator();
  }

  private void popLastOperator() {
    if (!operatorStack.isEmpty()) {
      Character peek = operatorStack.peek();
      if (peek.equals('*') || peek.equals('/')) {
        calculateOne();
      }
    }
  }

}
