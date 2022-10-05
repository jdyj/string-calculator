package com.string.calculator.collection;

import com.string.calculator.Number;
import com.string.calculator.Operator;
import com.string.calculator.operation.OperationFactory;

final class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public Number one(Number left, Number right, Operator operatorSign) {
    if (operatorSign.getSign() == '+') {

    }
    return operationFactory.create(left, right).calculateOne(operatorSign);
  }

}
