package com.string.calculator.parse;

import com.string.calculator.OperatorSign;

public class Parsing {

  private final NumberPiece numberPiece = new NumberPiece();
  private final ParsingHandler parsingHandler;
  private final InternalParsing internalParsing = new InternalParsing();

  public Parsing(ParsingHandler parsingHandler) {
    this.parsingHandler = parsingHandler;
  }

  public void parse(String input) {
    internalParsing.iterate(input, this::execute);
  }


  private void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
    } else if (canNumberParsed(c)) {
      parsingHandler.numberParsed(numberPiece.getNumber());
      numberPiece.makeEmpty();
    } else if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          parsingHandler.numberParsed(numberPiece.getNumber());
          numberPiece.makeEmpty();
        }
      }
    }
  }

  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
  }

  private boolean canNumberParsed(char c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
