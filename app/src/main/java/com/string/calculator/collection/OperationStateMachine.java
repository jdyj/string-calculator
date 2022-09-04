package com.string.calculator.collection;

import com.string.calculator.CalculationProcess;
import com.string.calculator.Number;
import com.string.calculator.Setting;
import com.string.calculator.parse.ParsingHandler;
import com.string.calculator.OperatorSign;
import com.string.calculator.operation.OperationFactory;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final OperationFactory operationFactory = new OperationFactory();
  private final Calculate calculate = new Calculate(operationFactory);
//  private final StringBuilder calculationProcess = new StringBuilder();
  private final CalculationProcess calculationProcess = new CalculationProcess();

  public Number getCalculatedValue() {

    while (numberCollection.hasNext()) {
      addStack();
    }

    return numberCollection.getLastElement();
  }

  public CalculationProcess getCalculationProcess() {
    return calculationProcess;
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
    Number result = calculate.one(left, right, operatorSign, calculationProcess);
    numberCollection.add(result);
  }


}
