package com.string.calculator.parse;

import com.string.calculator.Number;
import com.string.calculator.Operator;
import java.util.List;

/**
 * 1. 동작 중심으로 바뀐 인터페이스랑 파싱과 머신과 연결하기.
 * 2. 인덱스 끼워넣기...
 * 관심사에만 집중할수 있도록
 *
 *
 */
public class Parsing {

  private final ParsingHandler parsingHandler;
  private final NumberPiece numberPiece = new NumberPiece();
  private int index = 0;


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
    if (Operator.isSupportedOperator(c)) {
      if (c == Operator.closeBracket.getSign()) {
        parsingHandler.closeBracketFound();
        return;
      }

      parsingHandler.operatorParsed(Operator.valueOf(c));
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
      parsingHandler.numberParsed(new Number(numberPiece.getNumber(), index));
      index++;
    } catch (Exception e) {

    } finally {
      numberPiece.makeEmpty();
    }
  }

  private boolean isNumberPiece(Character c) {
    boolean isNumeric = (c >= '0' && c <= '9');
    boolean isMinusSign = c == '-';

    return isNumeric || isMinusSign;
  }

  private boolean canNumberParsed(Character c) {
    if (numberPiece.getNumber().equals("-")) {
      return false;
    }
    return c == ' ' && numberPiece.hasNumber();
  }


}
