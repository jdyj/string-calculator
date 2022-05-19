package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.ArithmeticOperation;
import com.string.calculator.calculate.OperationFactory;


public class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory calculateFactory) {
    this.operationFactory = calculateFactory;
  }

  public String one(String leftValue, String rightValue, OperatorSign operatorSign) {
    // 객체 생성을 동적으로 해야하는 경우
    ArithmeticOperation operation = operationFactory.create(leftValue, rightValue);
    return operation.calculateOne(operatorSign);
  }

}
