package com.string.calculator;

public class Run {

  private final OperationStateMachine machine = new OperationStateMachine();
  private Parsing parsing;

  public String calculate(String input) {
    parsing = new Parsing(
        new MachineHandler() {
          @Override
          public void operatorParsed(OperatorSign operatorSign) {
            machine.addOperationSign(operatorSign);
          }

          @Override
          public void numberParsed(String number) {
            machine.addNumber(number);
            if (machine.existHighOperatorSign()) {
              machine.addStack();
            }
          }
        }
    );

    parsing.parse(input);

    return machine.getResult();
  }

}
