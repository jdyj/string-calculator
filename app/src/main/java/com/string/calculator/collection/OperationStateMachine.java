package com.string.calculator.collection;

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
    if (existHighOperatorSign()) {
      numberCollection.add(number);
      addStack();
      return;
    }
    if (isNegative(number)) {
      operatorCollection.add(OperatorSign.plus);
    }
    numberCollection.add(number);
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
    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }


}
