package com.string.calculator.expression;

import com.string.calculator.Indexed;
import com.string.calculator.collection.Calculation;
import java.util.ArrayList;
import java.util.List;

public class HistoryAddingExpression implements Expression {

  private final Calculation calculation;
  private final Expression expression;

  public HistoryAddingExpression(Calculation calculation,
      Expression expression) {
    this.calculation = calculation;
    this.expression = expression;
  }

  public Expression getExpression() {
    return expression;
  }

  @Override
  public Integer value() {
    return expression.value();
  }

  @Override
  public Expression evaluate() {
    List<Indexed> arr = new ArrayList<>();
    arr.add(expression);
    calculation.피연산자계산중이야(arr);

    return expression.evaluate();
  }

  @Override
  public int compareTo(Indexed o) {
    return expression.compareTo(o);
  }

  @Override
  public String toString() {
    return expression.toString();
  }

}
