package com.string.calculator.calculate;

import com.string.calculator.OperatorSign;
import java.math.BigInteger;

public class BigIntegerCalculate extends Calculate {

  private final BigInteger left;
  private final BigInteger right;

  @Override
  public String add() {
    return left.add(right).toString();
  }

  @Override
  public String subtract() {
    return left.subtract(right).toString();
  }

  @Override
  public String multiply() {
    return left.multiply(right).toString();
  }

  public BigIntegerCalculate(String leftValue, String rightValue) {
    left = new BigInteger(leftValue);
    right = new BigInteger(rightValue);
  }
}
