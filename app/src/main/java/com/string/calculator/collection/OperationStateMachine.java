package com.string.calculator.collection;

import com.string.calculator.Number;
import com.string.calculator.parse.ParsingHandler;
import com.string.calculator.Operator;
import com.string.calculator.operation.OperationFactory;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final OperationFactory operationFactory = new OperationFactory();
  private final Calculate calculate = new Calculate(operationFactory);
  private final Calculation calculation;

  public OperationStateMachine(Calculation calculation) {
    this.calculation = calculation;
  }

  public Number getCalculatedValue() {

    while (numberCollection.hasNext()) {
      addStack();
    }

    return numberCollection.getLastElement();
  }

  @Override
  public void operatorParsed(Operator operatorSign) {
//    operatorCollection.add(operatorSign);
  }

  @Override
  public void numberParsed(Number number) {
    if (existHighOperatorSign()) {
      numberCollection.add(number);
      addStack();
      return;
    }
    if (number.isNegative()) {
//      operatorCollection.add(Operator.plus);
    }
    numberCollection.add(number);
  }

  @Override
  public void closeBracketFound() {
    while (!isOpenBracket()) {
      addStack();
    }
    operatorCollection.removeLast();

    if (existHighOperatorSign()) {
      addStack();
    }
  }

  private boolean isOpenBracket() {
    return (operatorCollection.getLastElement().getOperator() == Operator.openBracket);
  }

  private boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    Operator lastOperator = operatorCollection.getLastElement().getOperator();
    return lastOperator == Operator.divide || lastOperator == Operator.multiply;
  }


  private void addStack() {
    Number right = numberCollection.getLastElementAndRemove();
    Number left = numberCollection.getLastElementAndRemove();
    Operator operatorSign = operatorCollection.getLastElement().getOperator();
    operatorCollection.removeLast();
    Number result = calculate.one(left, right, operatorSign);

    numberCollection.add(result);
  }


}
