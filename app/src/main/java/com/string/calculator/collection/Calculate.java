package com.string.calculator.collection;

import com.string.calculator.CalculationProcess;
import com.string.calculator.Number;
import com.string.calculator.OperatorSign;
import com.string.calculator.operation.OperationFactory;

final class Calculate {

  private final OperationFactory operationFactory;

  public Calculate(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public Number one(Number left, Number right, OperatorSign operatorSign,
      CalculationProcess calculationProcess) {
//    if (onCalculationProcess) {
//        calculationProcess.append(left.getValue()).append(" ").append(operatorSign.getSign())
//            .append(" ").append(right.getValue()).append(System.getProperty("line.separator"));
//    }
    calculationProcess.appendAndSpacing(left.getValue()).appendAndSpacing(operatorSign.getSign())
        .append(right.getValue()).appendln();

    return operationFactory.create(left, right).calculateOne(operatorSign);
  }

}
