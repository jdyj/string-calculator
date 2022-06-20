package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine;
import com.string.calculator.parse.Parsing;

public class Calculator {

  public String calculate(String input) {
    OperationStateMachine machine = new OperationStateMachine();
    Parsing parsing = new Parsing(machine);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }

}
