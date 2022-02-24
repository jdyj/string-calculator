package com.string.calculator;

import java.util.Stack;

public class Number {

  private Stack<String> numberStack = new Stack<>();
  private final StringBuilder stringBuilder = new StringBuilder();

  public void addNumberToStringBuilder(Character c) {
    if ((c >= '0' && c <= '9')) {
      stringBuilder.append(c);
    }
  }

  public void ifBlankCheckNumberInStringBuilder(char c) {
    if (c != ' ') {
      return;
    }

    if (stringBuilder.length() > 0) {
      numberStack.add(stringBuilder.toString());
    }

    stringBuilder.setLength(0);
  }

  public String getNumber() {
    return numberStack.pop();
  }

  public int size() {
    return numberStack.size();
  }

  public void addNumberToStackWithStringBuilder() {
    if (!stringBuilder.isEmpty()) {
      numberStack.add(stringBuilder.toString());
      stringBuilder.setLength(0);
    }
  }

  public void addNumberToStack(String number) {
    numberStack.add(number);
  }

  public void reverseNumberStack() {
    Stack<String> temp = new Stack<>();

    while (!numberStack.isEmpty()) {
      String pop = numberStack.pop();
      temp.add(pop);
    }
    numberStack = temp;
  }

}
