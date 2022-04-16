package com.string.calculator.parse;

import com.string.calculator.MachineHandler;
import com.string.calculator.OperatorSign;
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
    InternalParsing internalParsing = new InternalParsing(chars);
    internalParsing.iterate(this::execute);
  }


  private void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      machineHandler.operatorParsed(OperatorSign.valueOf(c));
    } else if (canNumberParsed(c)) {
      machineHandler.numberParsed(numberPiece.getNumber());
      numberPiece.makeEmpty();
    } else if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          machineHandler.numberParsed(numberPiece.getNumber());
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
