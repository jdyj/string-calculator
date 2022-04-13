package com.string.calculator.collection;

import java.util.Stack;

public class NumberCollection {

  private Stack<String> stack = new Stack<>();

  public String getOne() {
    return stack.pop();
  }

  public void add(String number) {
    stack.add(number);
  }

  public int size() {
    return stack.size();
  }

  public void reverse() {
    Stack<String> temp = new Stack<>();

    while (!stack.isEmpty()) {
      String pop = stack.pop();
      temp.add(pop);
    }
    stack = temp;
  }

}
