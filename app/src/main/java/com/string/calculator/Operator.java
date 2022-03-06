package com.string.calculator;

import com.string.calculator.calculate.Calculate;
import com.string.calculator.calculate.CalculateFactory;


public class Operator {

  private final CalculateFactory calculateFactory;

  public Operator(CalculateFactory calculateFactory) {
    this.calculateFactory = calculateFactory;
  }

  public String calculateOne(String leftValue, String rightValue, OperatorSign operatorSign) {
    // 객체 생성을 동적으로 해야하는 경우
    Calculate calculate = calculateFactory.create(leftValue, rightValue);
    return calculate.calculateOne(operatorSign);
  }

}
