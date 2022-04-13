package com.string.calculator.parse;

import com.string.calculator.OperatorSign;

// 이름 짓는것
// 구현체
public interface ParsingHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(String number);

}
