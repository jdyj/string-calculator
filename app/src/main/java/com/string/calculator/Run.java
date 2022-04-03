package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;
import com.string.calculator.collection.NumberCollection;
import com.string.calculator.collection.OperatorCollection;
import java.math.BigInteger;
import java.util.List;
import java.util.function.Consumer;

public class Run {

  private final OperationStateMachine machine = new OperationStateMachine();
  private Parsing parsing;

  public String calculate(String input) {
    parsing = new Parsing(
        machine::addOperationSign
        , machine::addNumber
    );
    
    parsing.parse(input);

    return machine.getResult();
  }

}
