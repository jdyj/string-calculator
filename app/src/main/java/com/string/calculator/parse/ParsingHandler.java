package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;

public interface ParsingHandler {

  void operatorParsed(OperatorSign operatorSign);

  void numberParsed(Expression number);

  void closeBracketFound(OperatorSign operatorSign);


}
