package com.string.calculator.collection;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.OperatorSign;
import org.junit.jupiter.api.Test;

class OperationStateMachineTests {

  OperationStateMachine machine = new OperationStateMachine();

  @Test
  void operatorParsedTest() {
    machine.operatorParsed(OperatorSign.plus);
  }

  @Test
  void numberParsedTest() {
    machine.numberParsed("123");
  }

}