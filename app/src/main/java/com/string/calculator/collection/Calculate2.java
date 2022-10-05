package com.string.calculator.collection;

import com.string.calculator.Operator;
import com.string.calculator.OperatorSign;
import com.string.calculator.operation.OperationFactory2;
import com.string.calculator.expression.Expression;

final class Calculate2 {

  private final OperationFactory2 operationFactory = new OperationFactory2();

  public Expression one(Expression left, Expression right, OperatorSign operatorSign) {
    return operationFactory.create(left, right, operatorSign);
  }

}
