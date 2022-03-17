package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import java.util.Stack;

public class NumberCollection {

  private Stack<String> numberStack = new Stack<>();

  public String getOne() {
    return numberStack.pop();
  }

  public void add(String number) {
    numberStack.add(number);
  }

  public int size() {
    return numberStack.size();
  }

  public void reverse() {
    Stack<String> temp = new Stack<>();

    while (!numberStack.isEmpty()) {
      String pop = numberStack.pop();
      temp.add(pop);
    }
    numberStack = temp;
  }

}
