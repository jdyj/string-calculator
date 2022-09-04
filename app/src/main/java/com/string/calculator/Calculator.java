package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine;
import com.string.calculator.parse.Parsing;

public class Calculator {

  private final OperationStateMachine machine = new OperationStateMachine();

  public Number calculate(String input) {
    Parsing parsing = new Parsing(machine);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }

  public CalculationProcess getCalculationProcess() {
    return machine.getCalculationProcess();
  }

}
