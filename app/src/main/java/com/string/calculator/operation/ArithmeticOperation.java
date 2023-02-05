package com.string.calculator.operation;

import com.string.calculator.Number;
import com.string.calculator.Operator;

public interface ArithmeticOperation {

  Number plus();

  Number multiply();

  Number divide();

  Number modular();

  default Number calculateOne(Operator operator) {
    Number result = null;
    switch (operator) {
      case plus -> result = plus();
      case multiply -> result = multiply();
      case divide -> result = divide();
      case modular -> result = modular();
    }
    return result;
  }

}
