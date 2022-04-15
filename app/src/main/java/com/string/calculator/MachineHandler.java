package com.string.calculator;

// 이름 짓는것
// 구현체
public interface MachineHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(String number);

}
