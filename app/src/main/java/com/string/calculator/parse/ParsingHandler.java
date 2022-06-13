package com.string.calculator.parse;

import com.string.calculator.OperatorSign;

public interface ParsingHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(String number);

  void closeBracketFound();

}
