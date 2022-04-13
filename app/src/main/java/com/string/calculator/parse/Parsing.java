package com.string.calculator.parse;

import com.string.calculator.NumberPiece;
import com.string.calculator.OperatorSign;

public class Parsing {

  private final NumberPiece numberPiece = new NumberPiece();
  private final ParsingHandler parsingHandler;
  private final InnerParsing innerParsing = new InnerParsing();

  public Parsing(ParsingHandler parsingHandler) {
    this.parsingHandler = parsingHandler;
  }

  public void parse(String input) {
    innerParsing.iterate(input, this::execute);
  }


  private void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
    } else if (canAddNumberToCollection(c)) {
      parsingHandler.numberParsed(numberPiece.getNumber());
    } else if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          parsingHandler.numberParsed(numberPiece.getNumber());
        }
      }
    }
  }

  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
  }

  private boolean canAddNumberToCollection(char c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
