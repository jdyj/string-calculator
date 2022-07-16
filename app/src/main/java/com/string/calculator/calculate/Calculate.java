package com.string.calculator.calculate;

import com.string.calculator.App;
import com.string.calculator.Number;
import com.string.calculator.OperatorSign;

public class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public Number one(Number left, Number right, OperatorSign operatorSign) {
    return operationFactory.create(left, right).calculateOne(operatorSign);
  }

}
