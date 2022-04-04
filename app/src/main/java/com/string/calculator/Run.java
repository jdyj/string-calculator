package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;
import com.string.calculator.collection.NumberCollection;
import com.string.calculator.collection.OperatorCollection;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class Run {

  /**
   * 객체한테 말을 걸고 부탁을하고 이런 활동 -> 오브젝트에 메세지를 보낸다.
   * 메세지를 보내면서
   */
  private final OperationStateMachine machine = new OperationStateMachine();
  private Parsing parsing;

  public String calculate(String input) {
    parsing = new Parsing(
        new Xxx() {
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
