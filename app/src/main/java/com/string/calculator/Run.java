package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine;
import com.string.calculator.parse.Parsing;

public class Run {

  public String calculate(String input) {
    // 스레드 safe
    OperationStateMachine machine = new OperationStateMachine();
    Parsing parsing = new Parsing(machine);
    parsing.parse(input);

    return machine.getCalculatedValue();
  }

}
