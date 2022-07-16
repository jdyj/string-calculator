package com.string.calculator.calculate;

import com.string.calculator.Fraction;
import com.string.calculator.Number;

public class FractionConstructorFactory {


  public FractionOperation create(String left, String right) {
    return new FractionOperation(new Fraction(new Number(left)),
        new Fraction(new Number(right)));
  }


}
