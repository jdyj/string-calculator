package com.string.calculator.parse;

import com.string.calculator.Number;
import com.string.calculator.Operator;

public class ParsingHandlerErrorGetter implements ParsingHandler {

  private int count;

  @Override
  public void operatorParsed(Operator operatorSign) {

  }

  @Override
  public void numberParsed(Number number) {
    count++;
    if (this.count == 1) {
      throw new IllegalStateException();
    }
  }

  @Override
  public void closeBracketFound() {

  }

}
