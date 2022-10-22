package com.string.calculator.expression;

import com.string.calculator.collection.Xxx;
import com.string.calculator.operator.bi.BiOperator;
import java.util.Stack;

public class BinaryExpressionFactory implements Xxx {

  private final Stack<Expression> stack = new Stack<>();

  @Override
  public void calculate(Expression expression) {
    stack.add(expression);
  }

  @Override
  public Expression createExpression(BiOperator biOperator) {
    Expression right = stack.pop();
    Expression left = stack.pop();
    return new BinaryExpression(left, biOperator, right).evaluate();
  }

  @Override
  public Expression getResult() {
    return stack.pop().evaluate();
  }
}
