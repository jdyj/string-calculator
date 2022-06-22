package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import java.util.List;

public class Parsing {

  private final ParsingHandler parsingHandler;
  private final NumberPiece numberPiece = new NumberPiece();

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
      if (c == OperatorSign.closeBracket.getSign()) {
        parsingHandler.closeBracketFound();
        return;
      }

      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
      return;
    }

    if (canNumberParsed(c)) {
      numberParsed(parsingHandler, numberPiece);
      return;
    }

    if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          numberParsed(parsingHandler, numberPiece);
        }
      }
    }
  }

  private void numberParsed(ParsingHandler parsingHandler, NumberPiece numberPiece) {
    try {
      parsingHandler.numberParsed(numberPiece.getNumber());
    } catch (Exception e) {

    } finally {
      numberPiece.makeEmpty();
    }
  }

  private boolean isNumberPiece(Character c) {
    return (c >= '0' && c <= '9');
  }

  private boolean canNumberParsed(Character c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
