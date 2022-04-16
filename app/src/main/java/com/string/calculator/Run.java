package com.string.calculator;

import com.string.calculator.collection.OperationStateMachine;
import com.string.calculator.parse.Parsing;

public class Run {

  private final OperationStateMachine machine = new OperationStateMachine();
  private Parsing parsing;

  public String calculate(String input) {
    parsing = new Parsing(
        new MachineHandler() {
          @Override
          public void operatorParsed(OperatorSign operatorSign) {
            machine.add(operatorSign);
          }

          @Override
          public void numberParsed(String number) {
            machine.add(number);
            if (machine.existHighOperatorSign()) {
              machine.addStack();
            }
          }
        }
    );

    parsing.parse(input);

    return machine.getFinalResult();
  }

}
