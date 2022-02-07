package string.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculate {

  private final Stack<Character> operatorStack = new Stack<>();
  private final Stack<Double> numberStack = new Stack<>();
  private final StringBuilder sb = new StringBuilder();
  private final char[] operators = {'+', '-', '(', ')', '/', '*'};

  public void addNumber(char c) {
    if (c >= '0' && c <= '9') {
      sb.append(c);
    }
  }

  public double run(String input) {

    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    int index = 0;

    for (Character c : chars) {
      if (index == chars.size() - 1) {
        ifLastCharNumber(c);
        addOperatorToStack(c);
        break;
      }
      addOperatorToStack(c);
      addNumber(c);
      index++;
    }

    return getResult();
  }

  public void ifLastCharNumber(char c) {
    if (c >= '0' && c <= '9') {
      sb.append(c);
      numberStack.add(Double.parseDouble(sb.toString()));
      checkLastStack();
    }
  }

  private void addOperatorToStack(char c) {
    for (char operator : operators) {
      if (c == operator) {
        stackAdd(c);
      }
    }
  }

  public void stackAdd(Character sign) {
    if (!sb.isEmpty()) {
      numberStack.add(Double.parseDouble(sb.toString()));
    }
    if (sign.equals('+')) {
      if (!operatorStack.isEmpty()) {
        Character peek = operatorStack.peek();
        if (peek.equals('*') || peek.equals('/')) {
          calculateOne();
        }
      }
      operatorStack.add(sign);
    }
    if (sign.equals('-')) {
      if (!operatorStack.isEmpty()) {
        Character peek = operatorStack.peek();
        if (peek.equals('*') || peek.equals('/')) {
          calculateOne();
        }
      }
      operatorStack.add(sign);
    }

    if (sign.equals('*')) {
      if (!operatorStack.isEmpty()) {
        Character peek = operatorStack.peek();
        if (peek.equals('/')) {
          calculateOne();
        }
      }
      operatorStack.add(sign);
    }

    if (sign.equals('/')) {
      if (!operatorStack.isEmpty()) {
        Character peek = operatorStack.peek();
        if (peek.equals('*')) {
          calculateOne();
        }
      }
      operatorStack.add(sign);
    }

    if (sign.equals('(')) {
      operatorStack.add(sign);
    }

    if (sign.equals(')')) {
      while (!operatorStack.isEmpty()) {
        Character peek = operatorStack.peek();
        if (peek.equals('(')) {
          operatorStack.pop();
          break;
        }
        calculateOne();
      }
    }

    sb.setLength(0);
  }

  public void calculateOne() {

    Double rightValue = numberStack.pop();
    Double leftValue = numberStack.pop();

    Character operator = operatorStack.pop();
    operatorSelect(leftValue, rightValue, operator);

  }

  public void checkLastStack() {

    if (!operatorStack.isEmpty()) {
      Character peek = operatorStack.peek();
      if (peek.equals('*') || peek.equals('/')) {
        calculateOne();
      }
    }

  }

  public void operatorSelect(Double leftValue,
      Double rightValue,
      Character operator) {

    double result;
    if (operator.equals('+')) {
      result = add(leftValue, rightValue);
      numberStack.push(result);
    }
    if (operator.equals('-')) {
      result = subtract(leftValue, rightValue);
      numberStack.push(result);
    }
    if (operator.equals('*')) {
      result = multiply(leftValue, rightValue);
      numberStack.push(result);
    }
    if (operator.equals('/')) {
      result = divide(leftValue, rightValue);
      numberStack.push(result);
    }
  }

  public double getResult() {

    reverseStack(operatorStack);
    reverseStack(numberStack);

    while (!numberStack.isEmpty()) {
      if (numberStack.size() == 1) {
        break;
      }
      Double leftValue = numberStack.pop();
      Double rightValue = numberStack.pop();

      Character operator = operatorStack.pop();
      operatorSelect(leftValue, rightValue, operator);

    }
    return numberStack.pop();
  }

  private <T> void reverseStack(Stack<T> stack) {

    List<T> tempList = new ArrayList<>(stack);
    stack.clear();

    for (int i = tempList.size() - 1; i >= 0; i--) {
      stack.push(tempList.get(i));
    }

  }

  private double add(double leftValue, double rightValue) {
    return leftValue + rightValue;
  }

  private double subtract(double leftValue, double rightValue) {
    return leftValue - rightValue;
  }

  private double multiply(double leftValue, double rightValue) {
    return leftValue * rightValue;
  }

  private double divide(double leftValue, double rightValue) {
    if (rightValue == 0) {
      throw new IllegalStateException("0으로 나눌 수 없습니다");
    }
    return leftValue / rightValue;
  }

}
