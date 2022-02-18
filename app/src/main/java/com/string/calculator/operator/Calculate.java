package com.string.calculator.operator;

import static java.util.stream.Collectors.toList;

import com.string.calculator.Sign;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class Calculate {

  private final Sign sign = new Sign();

  public String add(String leftValue, String rightValue) {

    // true 음수, false 양수
    boolean leftSign = leftValue.charAt(0) == '-';
    boolean rightSign = rightValue.charAt(0) == '-';

    List<Integer> left = reverseInput(leftValue);
    List<Integer> right = reverseInput(rightValue);

    boolean minus = false;

    ArrayList<Integer> result = new ArrayList<>();

    // 부호가 같을 때
    if (leftSign == rightSign) {
      addWithSameSign(left, right, result);
      if (leftSign) {
        minus = true;
      }
    }

    // 부호가 다를 때
    if (leftSign != rightSign) {
      //true left, false right
      boolean checkSign = sign.decision(left, right);

      if (checkSign) {
        addWithDifferentSign(left, right, result);
        if (leftSign) {
          minus = true;
        }
      }

      if (!checkSign) {
        addWithDifferentSign(right, left, result);
        if (rightSign) {
          minus = true;
        }
      }

    }

    return getStringBuilder(result, minus);
  }

  public String multiply(String leftValue, String rightValue) {

    boolean leftSign = leftValue.charAt(0) == '-';
    boolean rightSign = rightValue.charAt(0) == '-';

    List<Integer> left = reverseInput(leftValue);
    List<Integer> right = reverseInput(rightValue);

    int[] array = multiplyCalculate(left, right);

    List<Integer> result
        = Arrays.stream(array)
        .boxed()
        .collect(toList());

    boolean minus = leftSign != rightSign;

    return getStringBuilder(result, minus);

  }

  public String pow(String leftValue, String rightValue) {

    int right = Integer.parseInt(rightValue);

    String value = "1";
    for (int i = 0; i < right; i++) {
      value = multiply(value, leftValue);
    }

    return value;
  }

  private int[] multiplyCalculate(final List<Integer> left, final List<Integer> right) {

    int index = 0;
    int[] array = new int[100];
    for (Integer leftNum : left) {
      List<Integer> temp = new ArrayList<>();
      for (Integer rightNum : right) {
        temp.add(leftNum * rightNum);
      }
      for (int i = 0, j = index; i < temp.size(); i++) {
        array[i + j] += temp.get(i) % 10;
        array[i + j + 1] += temp.get(i) / 10;
      }
      index++;
    }

    for (int i = 0; i < 99; i++) {
      array[i + 1] += array[i] / 10;
      array[i] = array[i] % 10;
    }

    return array;
  }

  private List<Integer> reverseInput(String value) {

    List<Character> chars = value.chars()
        .mapToObj(c -> (char) c)
        .toList();

    List<Integer> collect = chars.stream()
        .filter(character -> character != '-')
        .map(character -> character - '0')
        .collect(toList());

    Collections.reverse(collect);

    return collect;
  }


  private String getStringBuilder(List<Integer> result, boolean minus) {
    StringBuilder stringBuilder = new StringBuilder();

    ListIterator<Integer> resultIter = result.listIterator(result.size());

    while (resultIter.hasPrevious()) {
      stringBuilder.append(resultIter.previous());
    }
    checkZero(stringBuilder);

    if (!stringBuilder.toString().equals("0") && minus) {
      stringBuilder.insert(0, '-');
    }

    return stringBuilder.toString();
  }

  private void checkZero(StringBuilder stringBuilder) {

    boolean isValid = false;
    while (!isValid && stringBuilder.length() > 1) {
      if (stringBuilder.charAt(0) == '0') {
        stringBuilder.deleteCharAt(0);
      }
      if (stringBuilder.charAt(0) > '0' && stringBuilder.charAt(0) <= '9') {
        isValid = true;
      }
    }

  }


  private void addWithSameSign(List<Integer> left, List<Integer> right,
      List<Integer> result) {

    Iterator<Integer> leftIter = left.iterator();
    Iterator<Integer> rightIter = right.iterator();

    int prev = 0;

    while (leftIter.hasNext() || rightIter.hasNext()) {
      int temp = 0;
      boolean l = leftIter.hasNext();
      boolean r = rightIter.hasNext();
      if (l && r) {
        temp = leftIter.next() + rightIter.next() + prev;
      }
      if (!l && r) {
        temp = rightIter.next() + prev;
      }
      if (l && !r) {
        temp = leftIter.next() + prev;
      }
      result.add(temp % 10);
      prev = temp / 10;
    }
  }

  private void addWithDifferentSign(List<Integer> left, List<Integer> right,
      List<Integer> result) {

    Iterator<Integer> leftIter = left.iterator();
    Iterator<Integer> rightIter = right.iterator();

    boolean minus = false;
    while (leftIter.hasNext() || rightIter.hasNext()) {
      int temp = 0;
      boolean l = leftIter.hasNext();
      boolean r = rightIter.hasNext();
      if (l && !r) {
        temp = leftIter.next();
      }
      if (l && r) {
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

  }
}
