package com.string.calculator.calculate;

import com.string.calculator.App;
import com.string.calculator.Fraction;
import com.string.calculator.OperatorSign;

public class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public String one(String leftValue, String rightValue, OperatorSign operatorSign) {
    if (App.fraction && hasDivide(leftValue, rightValue)) {
      Fraction fraction = new Fraction();
      return fraction.calculate(leftValue, rightValue, operatorSign);
    }

    return operationFactory.create(leftValue, rightValue).calculateOne(operatorSign);
  }

  private boolean hasDivide(String left, String right) {
    return left.contains("/") || right.contains("/");
  }

}
