package com.string.calculator.calculate;

import com.string.calculator.App;
import com.string.calculator.Fraction;
import com.string.calculator.OperatorSign;

public class Calculate {

  private final OperationFactory operationFactory;
  private final Fraction fraction;

  public Calculate(OperationFactory operationFactory, Fraction fraction) {
    this.operationFactory = operationFactory;
    this.fraction = fraction;
  }

  public String one(String leftValue, String rightValue, OperatorSign operatorSign) {
    if (App.fraction && hasDivide(leftValue, rightValue)) {
      return fraction.calculate(leftValue, rightValue, operatorSign);
    }

    return operationFactory.create(leftValue, rightValue).calculateOne(operatorSign);
  }

  private boolean hasDivide(String left, String right) {
    return left.contains("/") || right.contains("/");
  }

}
