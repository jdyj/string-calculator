package com.string.calculator.parse;

import com.string.calculator.Number;
import com.string.calculator.OperatorSign;

public interface ParsingHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(Number number);

  void closeBracketFound();

}
