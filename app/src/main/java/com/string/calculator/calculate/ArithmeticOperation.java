package com.string.calculator.calculate;

import com.string.calculator.OperatorSign;

public interface ArithmeticOperation {

  String plus();

  String subtract();

  String multiply();

  String divide();

  default String calculateOne(OperatorSign operator) {
    String result = null;
    switch (operator) {
      case plus -> result = plus();
      case subtract -> result = subtract();
      case multiply -> result = multiply();
      case divide -> result = divide();
    }
    return result;
  }

}
