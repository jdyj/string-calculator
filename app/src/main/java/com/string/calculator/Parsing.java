package com.string.calculator;

import java.util.List;

public class Parsing {

  private final NumberPiece numberPiece = new NumberPiece();
  private final MachineHandler machineHandler;

  public Parsing(MachineHandler machineHandler) {
    this.machineHandler = machineHandler;
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
    if (OperatorSign.isSupportedOperator(c)) {
      machineHandler.operatorParsed(OperatorSign.valueOf(c));
    } else if (canAddNumberToCollection(c)) {
      machineHandler.numberParsed(numberPiece.getNumber());
    } else if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          machineHandler.numberParsed(numberPiece.getNumber());
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
