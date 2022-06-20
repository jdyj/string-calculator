package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import java.util.ArrayList;
import java.util.List;

public class ParsingHandlerErrorGetter implements ParsingHandler {

  private int count;

  @Override
  public void operatorParsed(OperatorSign operatorSign) {

  }

  @Override
  public void numberParsed(String number) {
    count++;
    if (this.count == 1) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void closeBracketFound() {

  }

}
