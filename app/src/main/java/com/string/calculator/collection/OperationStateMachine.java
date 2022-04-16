package com.string.calculator.collection;

import com.string.calculator.Calculate;
import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.OperationFactory;

public class OperationStateMachine {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final Calculate calculate = new Calculate(new OperationFactory());

  public void add(OperatorSign operatorSign) {
    operatorCollection.add(operatorSign);
  }

  public void add(String number) {
    numberCollection.add(number);
  }

  public boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    if (operatorCollection.size() >= numberCollection.size()) {
      return false;
    }

    OperatorSign lastOperator = operatorCollection.getLastElement();
    return lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply;
  }


  public void addStack() {
    String leftValue = numberCollection.getLastElementAndRemove();
    String rightValue = numberCollection.getLastElementAndRemove();
    OperatorSign operatorSign = operatorCollection.getLastElement();
    operatorCollection.removeLast();
    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }


  public String getFinalResult() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.hasNext()) {
      addStack();
    }

    return numberCollection.getLastElement();
  }

}
