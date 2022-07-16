package com.string.calculator.calculate;

import com.string.calculator.Number;

public class NumberOperationFactory {

  public BigIntegerOperation createBigIntegerOperation(String left, String right) {
    return new BigIntegerOperation(new Number(left), new Number(right));
  }

  public PrimitiveOperation createPrimitiveOperation(String left, String right) {
    return new PrimitiveOperation(new Number(left), new Number(right));
  }

}
