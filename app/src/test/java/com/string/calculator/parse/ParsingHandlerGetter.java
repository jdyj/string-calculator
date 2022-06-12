package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import java.util.ArrayList;
import java.util.List;

public class ParsingHandlerGetter implements ParsingHandler {

  private OperatorSign operatorSign;
  private final List<String> numbers = new ArrayList<>();

  @Override
  public void operatorParsed(OperatorSign operatorSign) {
    this.operatorSign = operatorSign;
  }

  @Override
  public void numberParsed(String number) {
    numbers.add(number);
  }

  public OperatorSign getOperatorSign() {
    return this.operatorSign;
  }

  public List<String> getNumbers() {
    return this.numbers;
  }

}
