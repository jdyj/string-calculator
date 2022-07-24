package com.string.calculator.collection;

import com.string.calculator.Number;
import java.util.Stack;

final class NumberCollection {

  private Stack<Number> stack = new Stack<>();

  public Number getLastElement() {
    return stack.peek();
  }

  public Number getLastElementAndRemove() {
    return stack.pop();
  }

  public void add(Number number) {
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

}
