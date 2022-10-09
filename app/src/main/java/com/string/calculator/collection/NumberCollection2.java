package com.string.calculator.collection;

import com.string.calculator.Indexed;
import com.string.calculator.Operator;
import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;
import java.util.LinkedList;
import java.util.Queue;
import java.util.function.Consumer;

final class NumberCollection2 {

  private final Queue<Indexed> queue = new LinkedList<>();

  public Indexed getLastElement() {
    return queue.peek();
  }

  public Indexed getLastElementAndRemove() {
    return queue.poll();
  }

  public void add(Expression number) {
    queue.add(number);
  }

  public void add(OperatorSign operatorSign) {
    queue.add(operatorSign);
  }

  public Queue<Indexed> getQueue() {
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

  public void forEach(Consumer<Indexed> elementCalculate) {
    while (!isEmpty()) {
      Indexed element = this.getLastElementAndRemove();
      elementCalculate.accept(element);
    }
  }

}

