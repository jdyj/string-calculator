package com.string.calculator;

// 이름 짓는것 Caller 쪽을 따라라!! Caller는 Parsing!
// 구현체
public interface ParsingHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(String number);

}
