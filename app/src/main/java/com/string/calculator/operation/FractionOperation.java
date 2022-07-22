package com.string.calculator.operation;

import com.string.calculator.Number;
import com.string.calculator.OperatorSign;

final class FractionOperation implements ArithmeticOperation {

  private final OperationFactory operationFactory = new OperationFactory();
  private final Fraction left;
  private final Fraction right;

  @Override
  public Number plus() {
    Number commonNumerator = operationFactory.create(
        multiply(left.getDenominator(), right.getNumerator()),
        multiply(left.getNumerator(), right.getDenominator())).calculateOne(OperatorSign.plus);
    Number commonDenominator = multiply(left.getDenominator(), right.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public Number multiply() {
    Number commonNumerator = multiply(left.getNumerator(), right.getNumerator());
    Number commonDenominator = multiply(left.getDenominator(), right.getDenominator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public Number divide() {
    Number commonNumerator = multiply(left.getNumerator(), right.getDenominator());
    Number commonDenominator = multiply(left.getDenominator(), right.getNumerator());

    return reducedFraction(commonNumerator, commonDenominator);
  }

  @Override
  public Number modular() {
    return null;
  }

  public FractionOperation(Number left, Number right) {
    this.left = new Fraction(left);
    this.right = new Fraction(right);
  }

  private Number divide(Number left, Number right) {
    return operationFactory.create(left, right)
        .calculateOne(OperatorSign.divide);
  }

  private Number multiply(Number left, Number right) {
    return operationFactory.create(left, right)
        .calculateOne(OperatorSign.multiply);
  }

  private Number gcd(Number a, Number b) {
    Number tempA = a;
    Number tempB = b;
    if (a.isNegative()) {
      tempA = new Number(a.getValue().substring(1));
    }
    if (b.isNegative()) {
      tempB = new Number(b.getValue().substring(1));
    }
    Number result = operationFactory.create(tempA, tempB).calculateOne(OperatorSign.modular);
    if (result.getValue().equals("0")) {
      return b;
    }
    return gcd(b, result);
  }

  private Number reducedFraction(Number numerator, Number denominator) {
    Number gcd = gcd(numerator, denominator);
    if (gcd.getValue().equals("1")) {
      return toString(numerator, denominator);
    }
    Number gcdNumerator = divide(numerator, gcd);
    Number gcdDenominator = divide(denominator, gcd);

    if (gcdNumerator.isNegative() && gcdDenominator.isNegative()) {
      gcdDenominator = new Number(gcdDenominator.getValueWithoutMinusSign());
      gcdNumerator = new Number(gcdNumerator.getValueWithoutMinusSign());
    } else if (gcdDenominator.isNegative()) {
      gcdDenominator = new Number(gcdDenominator.getValueWithoutMinusSign());
      gcdNumerator = new Number("-" + gcdNumerator.getValue());
    }

    return toString(gcdNumerator, gcdDenominator);
  }

  private Number toString(Number numerator, Number denominator) {
    if (denominator.getValue().equals("1")) {
      return new Number(numerator.getValue());
    }
    return new Number(numerator.getValue() + "/" + denominator.getValue());
  }

  static final class Fraction {

    private final Number numerator;
    private final Number denominator;

    public Fraction(Number number) {
      if (number.hasDivide()) {
        String[] values = number.getValue().split("/");
        this.numerator = new Number(values[0]);
        this.denominator = new Number(values[1]);
      } else {
        this.numerator = new Number(number.getValue());
        this.denominator = new Number("1");
      }
    }

    public Number getNumerator() {
      return numerator;
    }

    public Number getDenominator() {
      return denominator;
    }
  }

}
