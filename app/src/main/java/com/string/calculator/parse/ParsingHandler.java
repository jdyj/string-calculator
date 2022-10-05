package com.string.calculator.parse;

import com.string.calculator.Number;
import com.string.calculator.Operator;

public interface ParsingHandler {

  void operatorParsed(Operator operatorSign);

  void numberParsed(Number number);

  void closeBracketFound();

}
