package com.string.calculator.collection;

import com.string.calculator.Number;
import com.string.calculator.calculate.Calculate;
import com.string.calculator.parse.ParsingHandler;
import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.OperationFactory;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final OperationFactory operationFactory = new OperationFactory();
  private final Calculate calculate = new Calculate(operationFactory);

  public Number getCalculatedValue() {

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
  public void numberParsed(Number number) {
    if (existHighOperatorSign()) {
      numberCollection.add(number);
      addStack();
      return;
    }
    if (number.isNegative()) {
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
    Number right = numberCollection.getLastElementAndRemove();
    Number left = numberCollection.getLastElementAndRemove();
    OperatorSign operatorSign = operatorCollection.getLastElement();
    operatorCollection.removeLast();
    Number result = calculate.one(left, right, operatorSign);
    numberCollection.add(result);
  }


}
