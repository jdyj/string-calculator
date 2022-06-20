package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import java.util.Stack;

final class OperatorCollection {

  private Stack<OperatorSign> stack = new Stack<>();

  public OperatorSign getLastElement() {
    if (isEmpty()) {
      return null;
    }
    return stack.peek();
  }

  public boolean isEmpty() {
    return stack.isEmpty();
  }

  public int size() {
    return stack.size();
  }

  public void add(OperatorSign operatorSign) {
    stack.add(operatorSign);
  }

  public void removeLast() {
    stack.pop();
  }

  public void reverse() {
    Stack<OperatorSign> temp = new Stack<>();

    while (!stack.isEmpty()) {
      OperatorSign pop = stack.pop();
      temp.add(pop);
    }
    stack = temp;
  }

}
