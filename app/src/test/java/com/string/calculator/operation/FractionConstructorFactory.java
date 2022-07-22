package com.string.calculator.operation;

import com.string.calculator.Number;

public class FractionConstructorFactory {

  public FractionOperation create(String left, String right) {
    return new FractionOperation(new Number(left), new Number(right));
  }


}
