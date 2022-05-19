package com.string.calculator.collection;

import java.util.Stack;

final class NumberCollection {

  private Stack<String> stack = new Stack<>();

  public String getLastElement() {
    return stack.peek();
  }

  public String getLastElementAndRemove() {
    return stack.pop();
  }

  public void add(String number) {
    stack.add(number);
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public boolean hasNext() {
    return stack.size() > 1;
  }

  public int size() {
    return stack.size();
  }

  public void reverse() {
    Stack<String> temp = new Stack<>();

    while (!isEmpty()) {
      String pop = stack.pop();
      temp.add(pop);
    }
    stack = temp;
  }

}
