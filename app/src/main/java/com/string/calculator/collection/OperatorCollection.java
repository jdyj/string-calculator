package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import java.util.Stack;

public class OperatorCollection {

  private Stack<OperatorSign> stack = new Stack<>();

  public OperatorSign getOne() {
    return stack.pop();
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

  public OperatorSign peek() {
    return stack.peek();
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
