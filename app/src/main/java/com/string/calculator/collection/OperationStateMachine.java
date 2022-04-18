package com.string.calculator.collection;

import com.string.calculator.Calculate;
import com.string.calculator.ParsingHandler;
import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.OperationFactory;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final Calculate calculate = new Calculate(new OperationFactory());

  public String getCalculatedValue() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.hasNext()) {
      addStack();
    }

    return numberCollection.getLastElement();
  }

  @Override
  public void operatorParsed(OperatorSign operatorSign) {
    add(operatorSign);
  }

  @Override
  public void numberParsed(String number) {
    add(number);
    if (existHighOperatorSign()) {
      addStack();
    }
  }

  private void add(OperatorSign operatorSign) {
    operatorCollection.add(operatorSign);
  }

  private void add(String number) {
    numberCollection.add(number);
  }

  private boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    if (operatorCollection.size() >= numberCollection.size()) {
      return false;
    }

    OperatorSign lastOperator = operatorCollection.getLastElement();
    return lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply;
  }


  private void addStack() {
    String leftValue = numberCollection.getLastElementAndRemove();
    String rightValue = numberCollection.getLastElementAndRemove();
    OperatorSign operatorSign = operatorCollection.getLastElement();
    operatorCollection.removeLast();
    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }


}
