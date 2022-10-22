package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine2;
import com.string.calculator.collection.Xxx;
import com.string.calculator.expression.BinaryExpressionFactory;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.ExpressionFactory;
import com.string.calculator.parse.Parsing2;

public class Calculator2 {

  private final ExpressionFactory expressionFactory;
  private final Xxx xxx;

  public Calculator2(ExpressionFactory expressionFactory, Xxx xxx) {
    this.expressionFactory = expressionFactory;
    this.xxx = xxx;
  }

  public Expression calculate(String input) {
    OperationStateMachine2 machine = new OperationStateMachine2(xxx, expressionFactory);
    Parsing2 parsing = new Parsing2(machine, expressionFactory);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }


}

