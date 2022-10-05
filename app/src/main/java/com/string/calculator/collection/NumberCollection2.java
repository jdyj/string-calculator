package com.string.calculator.collection;

import com.string.calculator.Operator;
import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;
import java.util.LinkedList;
import java.util.Queue;

final class NumberCollection2 {

  private final Queue<Object> queue = new LinkedList<>();

  public Object getLastElement() {
    return queue.peek();
  }

  public Object getLastElementAndRemove() {
    return queue.poll();
  }

  public void add(Expression number) {
    queue.add(number);
  }

  public void add(OperatorSign operatorSign) {
    queue.add(operatorSign);
  }

  public Queue<Object> getQueue() {
    return queue;
  }

  public boolean isEmpty() {
    return queue.isEmpty();
  }

  public boolean hasNext() {
    return queue.size() > 1;
  }

  public Object removeLast() {
    return queue.poll();
  }

  public int size() {
    return queue.size();
  }

}

