package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import java.util.List;

public class Parsing {

  private final NumberPiece numberPiece = new NumberPiece();
  private final ParsingHandler parsingHandler;

  public Parsing(ParsingHandler parsingHandler) {
    this.parsingHandler = parsingHandler;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    CharacterStream characterStream = new CharacterStream(chars);

    characterStream.forEachLastAware(this::execute);
  }


  private void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
      return;
    }

    if (canNumberParsed(c)) {
      parsingHandler.numberParsed(numberPiece.getNumber());
      numberPiece.makeEmpty();
      return;
    }

    if (isNumberPiece(c)) {
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
