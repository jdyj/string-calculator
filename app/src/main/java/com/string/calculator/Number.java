package com.string.calculator;

import com.string.calculator.calculate.CalculateFactory;
import java.util.List;
import java.util.Stack;

/**
 * 얘는 딱 인풋을 받으면 숫자들은 숫자 스택에, 연산자는 연산자스택에 넣어주는 역할만 하고싶은데 내가 설계한 계산기 특성상 높은 우선순위의 연산자가 있으면 연산을 해줘야 하는
 * 상황....
 */
public class Number {

  private Stack<String> numberStack = new Stack<>();
  private Stack<OperatorSign> operatorSignStack = new Stack<>();
  private final StringBuilder stringBuilder = new StringBuilder();
  private final Operator operator;

  public Number() {
    this.operator = new Operator(new CalculateFactory());
  }

  public String calculate(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (Character c : chars) {
      ifExistHighOperatorCalculate();
      addOperatorSignToStack(c);

      ifBlankCheckNumberInStringBuilder(c);
      addNumberToStringBuilder(c);
    }

    addNumberToStackWithStringBuilder();
    ifExistHighOperatorCalculate();

    return calculateLeftover();
  }

  public OperatorSign getOperatorSign() {
    return operatorSignStack.pop();
  }

  public void reverseNumberStack() {
    Stack<String> temp = new Stack<>();

    while (!numberStack.isEmpty()) {
      String pop = numberStack.pop();
      temp.add(pop);
    }
    numberStack = temp;
  }

  public void reverseOperatorStack() {
    Stack<OperatorSign> temp = new Stack<>();

    while (!operatorSignStack.isEmpty()) {
      OperatorSign pop = operatorSignStack.pop();
      temp.add(pop);
    }
    operatorSignStack = temp;
  }

  public String getNumber() {
    return numberStack.pop();
  }

  public int size() {
    return numberStack.size();
  }

  public void addOperatorSignToStack(Character c) {
    if (OperatorSign.findOperator(c)) {
      operatorSignStack.add(OperatorSign.valueOf(c));
    }
  }

  private void addNumberToStringBuilder(Character c) {
    if ((c >= '0' && c <= '9')) {
      stringBuilder.append(c);
    }
  }


  private void ifExistHighOperatorCalculate() {
    if (operatorSignStack.isEmpty()) {
      return;
    }

    if (operatorSignStack.size() >= numberStack.size()) {
      return;
    }

    OperatorSign lastOperator = operatorSignStack.peek();
    if (lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply) {
      String leftValue = getNumber();
      String rightValue = getNumber();
      OperatorSign operatorSign = getOperatorSign();
      String result = operator.calculateOne(leftValue, rightValue, operatorSign);
      numberStack.add(result);
    }

  }

  private void addNumberToStackWithStringBuilder() {
    if (!stringBuilder.isEmpty()) {
      numberStack.add(stringBuilder.toString());
      stringBuilder.setLength(0);
    }
  }

  private void ifBlankCheckNumberInStringBuilder(char c) {
    if (c != ' ') {
      return;
    }

    if (stringBuilder.length() > 0) {
      numberStack.add(stringBuilder.toString());
    }

    stringBuilder.setLength(0);
  }

  private String calculateLeftover() {

    reverseNumberStack();
    reverseOperatorStack();

    while (size() > 1) {
      String leftValue = getNumber();
      String rightValue = getNumber();

      OperatorSign operatorSign = getOperatorSign();

      String result = operator.calculateOne(leftValue, rightValue, operatorSign);
      numberStack.add(result);
    }

    return getNumber();
  }

}
