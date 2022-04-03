package com.string.calculator;

import java.util.List;
import java.util.function.Consumer;

public class Parsing {

  private final Consumer<Character> operationCollectionAdd;
  private final Consumer<String> numberCollectionAdd;
  private final NumberPiece numberPiece = new NumberPiece();

  public Parsing(Consumer<Character> operationCollectionAdd, Consumer<String> numberCollectionAdd) {
    this.operationCollectionAdd = operationCollectionAdd;
    this.numberCollectionAdd = numberCollectionAdd;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      execute(c, last);
      // 우선순위 연산자 탐색
      if (machine.existHighOperatorSign()) {
        machine.addStack();
      }
    }

  }


  private void execute(Character c, boolean last) {
    // 파싱 - 연산자
    if (OperatorSign.isSupportedOperator(c)) {
      operationCollectionAdd.accept(c);
    }

    // 파싱 - 피연산자
    if (canAddNumberToCollection(c)) {
      numberCollectionAdd.accept(numberPiece.getNumber());
    }

    // 파싱 - 피연산자
    if (isNumberPiece(c)) {
      numberPiece.add(c);

      if (last) {
        if (numberPiece.hasNumber()) {
          numberCollectionAdd.accept(numberPiece.getNumber());
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
