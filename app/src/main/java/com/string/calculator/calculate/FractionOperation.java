package com.string.calculator.calculate;

import com.string.calculator.Fraction;
import com.string.calculator.OperatorSign;

public class FractionOperation implements ArithmeticOperation {

  private final OperationFactory operationFactory = new OperationFactory();
  private final Fraction left;
  private final Fraction right;

  @Override
  public String plus() {
    String commonNumerator = operationFactory.create(
        multiply(left.getDenominator(), right.getNumerator()),
        multiply(left.getNumerator(), right.getDenominator())).calculateOne(OperatorSign.plus);
    String commonDenominator = multiply(left.getDenominator(), right.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public String multiply() {
    String commonNumerator = multiply(left.getNumerator(), right.getNumerator());
    String commonDenominator = multiply(left.getDenominator(), right.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public String divide() {
    String commonNumerator = multiply(left.getNumerator(), right.getDenominator());
    String commonDenominator = multiply(left.getDenominator(), right.getNumerator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public String modular() {
    return null;
  }

  public FractionOperation(Fraction left, Fraction right) {
    this.left = left;
    this.right = right;
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
