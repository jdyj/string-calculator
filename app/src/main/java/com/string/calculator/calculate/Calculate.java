package com.string.calculator.calculate;

import com.string.calculator.collection.StateHandler;
import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.operator.bi.BiOperator;
import java.util.Stack;

public class Calculate implements StateHandler {

  private final Stack<Expression> stack = new Stack<>();

  @Override
  public void hereExpression(Expression expression) {
    stack.add(expression);
  }

  @Override
  public Expression hereBiOperator(BiOperator biOperator) {
    Expression right = stack.pop();
    Expression left = stack.pop();
    return new BinaryExpression(left, biOperator, right, 0);
  }

  @Override
  public Expression getResult() {
    return stack.pop();
  }
}
