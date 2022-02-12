package string.calculator;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Calculate {

  private final Stack<Character> operatorStack = new Stack<>();
  private final Stack<String> numberStack = new Stack<>();
  private final StringBuilder sb = new StringBuilder();
  private final char[] operators = {'+', '-', '(', ')', '/', '*'};

  private void addNumber(char c) {
    if ((c >= '0' && c <= '9') || c == '-') {
      sb.append(c);
    }
  }

  public String run(String input) {

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
      numberStack.add(sb.toString());
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
      numberStack.add(sb.toString());
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
      if (!operatorStack.isEmpty()) {
        operatorStack.add('+');
      }
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

    String rightValue = numberStack.pop();
    String leftValue = numberStack.pop();

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

  public void operatorSelect(String leftValue,
      String rightValue,
      Character operator) {

    String result;
    if (operator.equals('+')) {
      result = add(leftValue, rightValue);
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

  public String getResult() {

    while (!numberStack.isEmpty()) {
      if (numberStack.size() == 1) {
        break;
      }
      String leftValue = numberStack.pop();
      String rightValue = numberStack.pop();

      Character operator = operatorStack.pop();
      operatorSelect(leftValue, rightValue, operator);

    }
    return numberStack.pop();
  }

  private String add(String leftValue, String rightValue) {

    int leftSize = leftValue.length();
    int rightSize = rightValue.length();

    int size = Math.max(leftSize, rightSize);

    int[] left = new int[size + 1];
    int[] right = new int[size + 1];

    // true 음수, false 양수
    boolean leftSign = leftValue.charAt(0) == '-';
    boolean rightSign = rightValue.charAt(0) == '-';

    reverseInput(leftValue, leftSize, left);
    reverseInput(rightValue, rightSize, right);

    boolean minus = false;

    // 부호가 같을 때
    if (leftSign == rightSign) {
      for (int i = 0; i < size; i++) {
        int temp = left[i] + right[i];
        left[i] = temp % 10;
        left[i + 1] += temp / 10;
      }

      if (leftSign) {
        minus = true;
      }
    }

    // 부호가 다를 때
    if (leftSign != rightSign) {
      //true left, false right
      boolean sign = signDecision(size, left, right);

      if (sign) {
        subtract(size, left, right);
        if (leftSign) {
          minus = true;
        }
      }

      if (!sign) {
        subtract(size, right, left);
        left = right;
        if (rightSign) {
          minus = true;
        }
      }

    }

    StringBuilder stringBuilder = new StringBuilder();

    if (left[size] != 0) {
      stringBuilder.append(left[size + 1]);
    }

    for (int i = size - 1; i >= 0; i--) {
      if (i == size - 1 && minus) {
        stringBuilder.append('-');
        continue;
      }
      stringBuilder.append(left[i]);
    }

    checkZero(stringBuilder);
    return stringBuilder.toString();
  }

  private void checkZero(StringBuilder stringBuilder) {

    boolean isValid = false;
    while (!isValid) {
      if (stringBuilder.charAt(0) == '0') {
        if (stringBuilder.length() == 1) {
          break;
        }
        stringBuilder.deleteCharAt(0);
      }
      if (stringBuilder.charAt(0) > '0' && stringBuilder.charAt(0) <= '9') {
        isValid = true;
      }
      if (stringBuilder.charAt(0) == '-') {
        isValid = true;
      }
    }

  }

  private void reverseInput(String value, int size, int[] array) {
    for (int i = size - 1; i >= 0; i--) {
      if (value.charAt(i) == '-') {
        continue;
      }
      array[size - i - 1] = value.charAt(i) - '0';
    }
  }

  private void subtract(int size, int[] left, int[] right) {
    for (int i = 0; i < size; i++) {
      int temp = left[i] - right[i];
      if (temp < 0) {
        temp += 10;
        left[i + 1]--;
      }
      left[i] = temp;
    }
  }

  private boolean signDecision(int size, int[] left, int[] right) {
    for (int i = size - 1; i >= 0; i--) {
      if (left[i] > right[i]) {
        return true;
      }
      if (left[i] < right[i]) {
        return false;
      }
    }
    return true;
  }

  private String multiply(String leftValue, String rightValue) {
//    return leftValue * rightValue;
    return null;
  }

  private String divide(String leftValue, String rightValue) {
//    if (rightValue == 0) {
//      throw new IllegalStateException("0으로 나눌 수 없습니다");
//    }
//    return leftValue / rightValue;
    return null;
  }

}
