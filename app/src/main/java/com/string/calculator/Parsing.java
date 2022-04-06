package com.string.calculator;

import java.util.List;

public class Parsing {

  private final NumberPiece numberPiece = new NumberPiece();
  private final Xxx xxx;

  public Parsing(Xxx xxx) {
    this.xxx = xxx;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      execute(c, last);
    }

  }


  private void execute(Character c, boolean last) {
    // 파싱 - 연산자
    if (OperatorSign.isSupportedOperator(c)) {
      xxx.operatorParsed(OperatorSign.valueOf(c));
    }

    // 파싱 - 피연산자
    if (canAddNumberToCollection(c)) {
      xxx.numberParsed(numberPiece.getNumber());
    }

    // 파싱 - 피연산자
    if (isNumberPiece(c)) {
      numberPiece.add(c);

      if (last) {
        if (numberPiece.hasNumber()) {
          xxx.numberParsed(numberPiece.getNumber());
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
