package com.string.calculator.collection;

import com.string.calculator.Number;
import com.string.calculator.OperatorSign;
import com.string.calculator.operation.OperationFactory;

final class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public Number one(Number left, Number right, OperatorSign operatorSign) {
    return operationFactory.create(left, right).calculateOne(operatorSign);
  }

}
