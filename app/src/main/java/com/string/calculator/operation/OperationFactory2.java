package com.string.calculator.operation;

import com.string.calculator.OperatorSign;
import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.operator.bi.BiOperator;
import com.string.calculator.operator.bi.OperatorFactory;

public class OperationFactory2 {

  private final OperatorFactory operatorFactory = new OperatorFactory();

  public Expression create(Expression left, Expression right, OperatorSign operatorSign) {
    BiOperator biOperator = operatorFactory.create(operatorSign);
    return new BinaryExpression(left, biOperator, right).evaluate();
  }

}

