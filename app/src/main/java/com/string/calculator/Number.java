package com.string.calculator;

import java.util.Stack;

public class Number {

  private final Stack<String> numberStack = new Stack<>();
  private final StringBuilder sb = new StringBuilder();

  public void add(char c) {
    if ((c >= '0' && c <= '9') || c == '-') {
      sb.append(c);
    }
  }

  public String getNumber() {
    return numberStack.pop();
  }

  public void setNumber(String number) {
    numberStack.push(number);
  }

  public void checkNumberStack() {
    if (sb.length() != 0) {
      numberStack.add(sb.toString());
    }
  }

  public void clear() {
    sb.setLength(0);
  }

  public int size() {
    return numberStack.size();
  }

}
