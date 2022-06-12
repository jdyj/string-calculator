package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import com.string.calculator.PriorityOperatorSign;
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

//    // 어떻게 해야 올바르게 인터페이스를 뽑아냈다고 할 수 있을까? 고민
//    ParsingCharacterWithLast parsingCharacterWithLast = new ParsingCharacterWithLast(
//        parsingHandler);
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

  private boolean canNumberParsed(Character c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
