package com.string.calculator.expression;

import com.string.calculator.Indexed;
import com.string.calculator.operator.unary.BracketOperator;
import com.string.calculator.operator.unary.SquareOperator;
import com.string.calculator.operator.unary.UnaryOperator;

public class UnaryExpression implements Expression {

  private final Expression expression;
  private final UnaryOperator unaryOperator;

  public UnaryExpression(Expression expression,
      UnaryOperator unaryOperator) {
    this.expression = expression;
    this.unaryOperator = unaryOperator;
  }

  @Override
  public Expression evaluate() {
    return unaryOperator.apply(expression).evaluate();
  }

  @Override
  public String toString() {
    if (unaryOperator instanceof BracketOperator) {
      return "(" + expression.toString() + ")";
    } else if (unaryOperator instanceof SquareOperator) {
      return expression.toString() + "^2";
    }

    throw new IllegalStateException();
  }

  @Override
  public Integer value() {
    return expression.value();
  }

  @Override
  public int compareTo(Indexed o) {
    return 0;
  }
}

