package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;

public class FractionCalculation {

  private final OperationFactory operationFactory = new OperationFactory();

  public String calculate(String leftValue, String rightValue, OperatorSign operatorSign) {
    Fraction left = new Fraction(leftValue);
    Fraction right = new Fraction(rightValue);
    String result = null;
    switch (operatorSign) {
      case plus -> result = plus(left, right);
      case multiply -> result = multiply(left, right);
    }

    return result;
  }

  private String plus(Fraction a, Fraction b) {
    String commonNumerator = operationFactory.create(multiply(a.getDenominator(), b.getNumerator()),
        multiply(a.getNumerator(), b.getDenominator())).calculateOne(OperatorSign.plus);
    String commonDenominator = multiply(a.getDenominator(), b.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  private String multiply(Fraction a, Fraction b) {
    String commonNumerator = multiply(a.getNumerator(), b.getNumerator());
    String commonDenominator = multiply(a.getDenominator(), b.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  private String divide(String leftValue, String rightValue) {
    return operationFactory.create(leftValue, rightValue)
        .calculateOne(OperatorSign.divide);
  }

  private String multiply(String leftValue, String rightValue) {
    return operationFactory.create(leftValue, rightValue)
        .calculateOne(OperatorSign.multiply);
  }

  private String gcd(String a, String b) {
    if (a.contains("-")) {
      a = a.substring(1);
    }
    if (b.contains("-")) {
      b = b.substring(1);
    }
    String result = operationFactory.create(a, b).calculateOne(OperatorSign.modular);
    if (result.equals("0")) {
      return b;
    }
    return gcd(b, result);
  }

  private String reducedFraction(String numerator, String denominator) {
    String gcd = gcd(numerator, denominator);
    if (gcd.equals("1")) {
      return toString(numerator, denominator);
    }
    numerator = divide(numerator, gcd);
    denominator = divide(denominator, gcd);

    if (numerator.contains("-") && denominator.contains("-")) {
      denominator = denominator.substring(1);
      numerator = numerator.substring(1);
    } else if (denominator.contains("-")) {
      denominator = denominator.substring(1);
      numerator = "-" + numerator;
    }

    return toString(numerator, denominator);
  }

  private String toString(String numerator, String denominator) {
    if (denominator.equals("1")) {
      return numerator;
    }
    return numerator + "/" + denominator;
  }


}
