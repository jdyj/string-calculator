package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine;
import com.string.calculator.collection.StateHandler;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.ExpressionFactory;
import com.string.calculator.parse.Parsing;

public class Calculator {

  private final ExpressionFactory expressionFactory;
  private final StateHandler xxx;

  public Calculator(ExpressionFactory expressionFactory, StateHandler xxx) {
    this.expressionFactory = expressionFactory;
    this.xxx = xxx;
  }

  public Expression calculate(String input) {
    OperationStateMachine machine = new OperationStateMachine(xxx, expressionFactory);
    Parsing parsing = new Parsing(machine, expressionFactory);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }


}
