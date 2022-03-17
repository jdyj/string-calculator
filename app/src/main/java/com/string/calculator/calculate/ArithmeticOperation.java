package com.string.calculator.calculate;

import com.string.calculator.OperatorSign;

public interface ArithmeticOperation {

  String add();

  String subtract();

  String multiply();

  default String calculateOne(OperatorSign operator) {
    String result = null;
    switch (operator) {
      case plus -> result = add();
      case subtract -> result = subtract();
      case multiply -> result = multiply();
    }
    return result;
  }

}
