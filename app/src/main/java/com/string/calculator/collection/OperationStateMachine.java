package com.string.calculator.collection;

import com.string.calculator.App;
import com.string.calculator.Fraction;
import com.string.calculator.calculate.Calculate;
import com.string.calculator.parse.ParsingHandler;
import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.OperationFactory;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final OperationFactory operationFactory = new OperationFactory();
  private final Calculate calculate = new Calculate(operationFactory);

  public String getCalculatedValue() {

    while (numberCollection.hasNext()) {
      addStack();
    }

    return numberCollection.getLastElement();
  }

  @Override
  public void operatorParsed(OperatorSign operatorSign) {
    operatorCollection.add(operatorSign);
  }

  @Override
  public void numberParsed(String number) {
    if (isNegative(number)) {
      if (existHighOperatorSign()) {
        numberCollection.add(number);
        addStack();
        return;
      }
      operatorCollection.add(OperatorSign.plus);
    }
    numberCollection.add(number);
    if (existHighOperatorSign()) {
      addStack();
    }
  }

  @Override
  public void closeBracketFound() {
    while (isNotOpenBracket()) {
      addStack();
    }
    operatorCollection.removeLast();

    if (existHighOperatorSign()) {
      addStack();
    }
  }

  private String makeNegative(String number) {
    String tempNumber;
    tempNumber = '-' + number;
    operatorCollection.removeLast();
    operatorCollection.add(OperatorSign.plus);
    return tempNumber;
  }

  private boolean isNegative(String number) {
    return number.contains("-");
  }

  private boolean isNotOpenBracket() {
    return !(operatorCollection.getLastElement() == OperatorSign.openBracket);
  }

  private boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    OperatorSign lastOperator = operatorCollection.getLastElement();
    return lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply;
  }


  private void addStack() {
    String rightValue = numberCollection.getLastElementAndRemove();
    String leftValue = numberCollection.getLastElementAndRemove();
    OperatorSign operatorSign = operatorCollection.getLastElement();
    operatorCollection.removeLast();
    if (operatorSign == OperatorSign.divide && App.fraction) {
      if (hasDivide(leftValue)) {
        String[] leftValues = leftValue.split("/");
        numberCollection.add(
            leftValues[0] + "/" + calculate.one(leftValues[1], rightValue, OperatorSign.multiply));
        return;
      }

      if (isNotFraction(leftValue, rightValue)) {
        numberCollection.add(calculate.one(leftValue, rightValue, OperatorSign.divide));
        return;
      }

      numberCollection.add(leftValue + "/" + rightValue);
      return;
    }

    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }

  private boolean hasDivide(String value) {
    return value.contains("/");
  }

  private boolean isNotFraction(String leftValue, String rightValue) {
    if (leftValue.contains("-")) {
      leftValue = leftValue.substring(1);
    }
    if (rightValue.contains("-")) {
      rightValue = rightValue.substring(1);
    }
    return operationFactory.create(leftValue, rightValue).calculateOne(OperatorSign.modular)
        .equals("0");
  }

}
