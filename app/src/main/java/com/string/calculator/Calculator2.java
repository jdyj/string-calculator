package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine2;
import com.string.calculator.expression.Expression;
import com.string.calculator.parse.Parsing2;

public class Calculator2 {

  private final CalculationHistory calculationHistory = new CalculationHistory();
  private final OperationStateMachine2 machine = new OperationStateMachine2(calculationHistory);

  public Expression calculate(String input) {
    Parsing2 parsing = new Parsing2(machine);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }


}

