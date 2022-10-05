package com.string.calculator.operator.bi;

import com.string.calculator.OperatorSign;

public class OperatorFactory {


  public BiOperator create(OperatorSign operatorSign) {

    if (operatorSign.getOperator().getSign() == '+') {
      return new PlusOperator();
    } else if (operatorSign.getOperator().getSign() == '-') {
      return new MinusOperator();
    } else if (operatorSign.getOperator().getSign() == '*') {
      return new MultiplyOperator();
    } else if (operatorSign.getOperator().getSign() == '/') {
      return new DivideOperator();
    }

    throw new IllegalStateException();


  }

}
