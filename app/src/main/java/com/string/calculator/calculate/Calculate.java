package com.string.calculator.calculate;

import com.string.calculator.App;
import com.string.calculator.OperatorSign;

public class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public String one(String leftValue, String rightValue, OperatorSign operatorSign) {
    return operationFactory.create(leftValue, rightValue).calculateOne(operatorSign);
  }

}
