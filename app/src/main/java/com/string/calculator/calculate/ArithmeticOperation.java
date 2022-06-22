package com.string.calculator.calculate;

import com.string.calculator.OperatorSign;

public interface ArithmeticOperation {

  String plus();

  String multiply();

  String divide();

  String modular();

  default String calculateOne(OperatorSign operator) {
    String result = null;
    switch (operator) {
      case plus -> result = plus();
      case multiply -> result = multiply();
      case divide -> result = divide();
      case modular -> result = modular();
    }
    return result;
  }

}
