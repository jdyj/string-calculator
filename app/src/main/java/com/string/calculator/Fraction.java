package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;

public class Fraction {

  private final OperationFactory operationFactory;

  public Fraction(OperationFactory operationFactory) {
    this.operationFactory = operationFactory;
  }

  public String calculate(String leftValue, String rightValue, OperatorSign operatorSign) {
    String commonDenominator = "1";
    String left = leftValue;
    String right = rightValue;
    if (hasDivide(leftValue) && hasDivide(rightValue)) {
      String[] leftValues = leftValue.split("/");
      String[] rightValues = rightValue.split("/");
      String leftNumerator = leftValues[0];
      String leftDenominator = leftValues[1];
      String rightNumerator = rightValues[0];
      String rightDenominator = rightValues[1];

      if (operatorSign == OperatorSign.plus) {
        String gcdDenominator = gcd(leftDenominator, rightDenominator);
        String multiplyResult = operationFactory.create(leftDenominator, rightDenominator)
            .calculateOne(OperatorSign.multiply);
        commonDenominator = divide(multiplyResult, gcdDenominator);

        if (isCoprime(gcdDenominator)) {

          left = multiply(leftNumerator, rightDenominator);
          right = multiply(rightNumerator, leftDenominator);
        } else {
          left = multiply(divide(commonDenominator, leftDenominator), leftNumerator);
          right = multiply(divide(commonDenominator, rightDenominator), rightNumerator);
        }
      } else {
        commonDenominator = multiply(leftDenominator, rightDenominator);
      }

    } else if (hasDivide(leftValue)) {
      String[] values = leftValue.split("/");
      commonDenominator = values[1];
      left = values[0];
      if (operatorSign == OperatorSign.plus) {
        right = multiply(rightValue, commonDenominator);
      }
    } else if (hasDivide(rightValue)) {
      String[] values = rightValue.split("/");
      commonDenominator = values[1];
      right = values[0];
      if (operatorSign == OperatorSign.plus) {
        left = multiply(leftValue, commonDenominator);
      }
    }

    String result = operationFactory.create(left, right).calculateOne(operatorSign);
    String gcd = gcd(result, commonDenominator);
    if (!isCoprime(gcd)) {
      result = divide(result, gcd);
      commonDenominator = divide(commonDenominator, gcd);
      if (result.contains("-") && commonDenominator.contains("-")) {
        commonDenominator = commonDenominator.substring(1);
        result = result.substring(1);
      } else if (commonDenominator.contains("-")) {
        commonDenominator = commonDenominator.substring(1);
        result = "-" + result;
      }
    }

    if (isNotFraction(commonDenominator)) {
      return result;
    }

    return result + "/" + commonDenominator;

  }

  public boolean isNotFraction(String leftValue, String rightValue) {
    if (leftValue.contains("-")) {
      leftValue = leftValue.substring(1);
    }
    if (rightValue.contains("-")) {
      rightValue = rightValue.substring(1);
    }
    return operationFactory.create(leftValue, rightValue).calculateOne(OperatorSign.modular)
        .equals("0");
  }

  private boolean isNotFraction(String denominator) {
    return denominator.equals("1");
  }

  private String divide(String leftValue, String rightValue) {
    return operationFactory.create(leftValue, rightValue)
        .calculateOne(OperatorSign.divide);
  }

  private String multiply(String leftValue, String rightValue) {
    return operationFactory.create(leftValue, rightValue)
        .calculateOne(OperatorSign.multiply);
  }

  private boolean isCoprime(String gcd) {
    return gcd.equals("1");
  }

  private boolean hasDivide(String value) {
    return value.contains("/");
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

}
