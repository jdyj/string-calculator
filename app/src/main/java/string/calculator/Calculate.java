package string.calculator;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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

    for (Character c : chars) {
      addOperatorToStack(c);
      addNumber(c);
    }

    checkLastStack();

    return getResult();
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
      checkPriorityOperatorStack('+');
      operatorStack.add(sign);
    }
    if (sign.equals('-')) {
      checkPriorityOperatorStack('-');
      operatorStack.add('+');
    }

    if (sign.equals('*')) {
      checkPriorityOperatorStack('*');
      operatorStack.add(sign);
    }

    if (sign.equals('/')) {
      checkPriorityOperatorStack('/');
      operatorStack.add(sign);
    }

    if (sign.equals('(')) {
      operatorStack.add(sign);
    }

    if (sign.equals(')')) {
      checkPriorityOperatorStack(')');
    }

    sb.setLength(0);
  }

  private void checkPriorityOperatorStack(char sign) {

    if (!operatorStack.isEmpty()) {
      Character peek;
      switch (sign) {
        case '+', '-' -> {
          peek = operatorStack.peek();
          if (peek.equals('*') || peek.equals('/')) {
            calculateOne();
          }
        }
        case '*' -> {
          peek = operatorStack.peek();
          if (peek.equals('/')) {
            calculateOne();
          }
        }
        case '/' -> {
          peek = operatorStack.peek();
          if (peek.equals('*')) {
            calculateOne();
          }
        }
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

  }

  private void calculateOne() {

    String rightValue = numberStack.pop();
    String leftValue = numberStack.pop();

    Character operator = operatorStack.pop();
    operatorSelect(leftValue, rightValue, operator);

  }

  public void checkLastStack() {

    if (sb.length() != 0) {
      numberStack.add(sb.toString());
    }

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

  private String getResult() {

    while (!numberStack.isEmpty()) {
      if (numberStack.size() == 1) {
        break;
      }
      String rightValue = numberStack.pop();
      String leftValue = numberStack.pop();

      Character operator = operatorStack.pop();
      operatorSelect(leftValue, rightValue, operator);

    }
    return numberStack.pop();
  }

  private String add(String leftValue, String rightValue) {

    int leftSize = leftValue.length();
    int rightSize = rightValue.length();

    int size = Math.max(leftSize, rightSize);

    ArrayList<Integer> left = new ArrayList<>();
    ArrayList<Integer> right = new ArrayList<>();

    // true 음수, false 양수
    boolean leftSign = leftValue.charAt(0) == '-';
    boolean rightSign = rightValue.charAt(0) == '-';

    reverseInput(leftValue, left);
    reverseInput(rightValue, right);

    boolean minus = false;

    ArrayList<Integer> result = new ArrayList<>();
    // 부호가 같을 때
    if (leftSign == rightSign) {

      Iterator<Integer> leftIter = left.iterator();
      Iterator<Integer> rightIter = right.iterator();

      int prev = 0;
      while (leftIter.hasNext() && rightIter.hasNext()) {
        int temp = leftIter.next() + rightIter.next() + prev;
        result.add(temp % 10);
        prev = temp / 10;
      }

      if (leftSign) {
        minus = true;
      }
    }

    // 부호가 다를 때
    if (leftSign != rightSign) {
      //true left, false right
      boolean sign = signDecision(left, right);

      if (sign) {
        subtract(left, right, result);
        if (leftSign) {
          minus = true;
        }
      }

      if (!sign) {
        subtract(right, left, result);
        left = right;
        if (rightSign) {
          minus = true;
        }
      }

    }

    StringBuilder stringBuilder = getStringBuilder(result, minus);

    checkZero(stringBuilder);
    return stringBuilder.toString();
  }


  private StringBuilder getStringBuilder(ArrayList<Integer> result, boolean minus) {
    StringBuilder stringBuilder = new StringBuilder();

//    if (result.get(size) != 0) {
//      stringBuilder.append(result.get(size + 1));
//      result.remove(size + 1);
//    }

    ListIterator<Integer> resultIter = result.listIterator(result.size());

    boolean first = true;
    while (resultIter.hasPrevious()) {
      if (first && minus) {
        stringBuilder.append('-');
        first = false;
        continue;
      }
      stringBuilder.append(resultIter.previous());
    }

    return stringBuilder;
  }

  private void checkZero(StringBuilder stringBuilder) {

    boolean isValid = false;
    int index = 0;
    while (!isValid) {
      if (stringBuilder.charAt(index) == '0') {
        if (stringBuilder.length() == 1) {
          break;
        }
        stringBuilder.deleteCharAt(index);
      }
      if (stringBuilder.charAt(index) > '0' && stringBuilder.charAt(index) <= '9') {
        isValid = true;
      }
      if (stringBuilder.charAt(index) == '-') {
        index++;
      }
    }

  }

  private void reverseInput(String value, ArrayList<Integer> array) {

    List<Character> chars = value.chars()
        .mapToObj(c -> (char) c)
        .toList();

    ListIterator<Character> li = chars.listIterator(chars.size());

    while (li.hasPrevious()) {
      Character previous = li.previous();
      if (previous == '-') {
        continue;
      }
      array.add(previous - '0');
    }
  }

  private void subtract(ArrayList<Integer> left, ArrayList<Integer> right,
      ArrayList<Integer> result) {

    Iterator<Integer> leftIter = left.iterator();
    Iterator<Integer> rightIter = right.iterator();

    boolean minus = false;
    while (leftIter.hasNext()) {
      int temp = 0;
      if (!rightIter.hasNext()) {
        temp = leftIter.next();
      }
      if (rightIter.hasNext()) {
        temp = leftIter.next() - rightIter.next();
      }
      if (minus) {
        temp--;
        minus = false;
      }
      if (temp < 0) {
        temp += 10;
        minus = true;
      }
      result.add(temp);
    }
//
//    for (int i = 0; i < size; i++) {
//      int temp = left[i] - right[i];
//      if (temp < 0) {
//        temp += 10;
//        left[i + 1]--;
//      }
//      left[i] = temp;
//    }
  }

  private boolean signDecision(ArrayList<Integer> left, ArrayList<Integer> right) {

    ListIterator<Integer> leftIter = left.listIterator();
    ListIterator<Integer> rightIter = right.listIterator();

    while (leftIter.hasPrevious() && rightIter.hasPrevious()) {

      Integer leftValue = leftIter.previous();
      Integer rightValue = rightIter.previous();
      if (leftValue > rightValue) {
        return true;
      }
      if (leftValue < rightValue) {
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
