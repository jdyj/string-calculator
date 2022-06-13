package com.string.calculator.parse;

import com.string.calculator.OperatorSign;
import java.util.ArrayList;
import java.util.List;

public class ParsingHandlerErrorGetter implements ParsingHandler {

  private List<String> numbers = new ArrayList<>();
  private int count;

  @Override
  public void operatorParsed(OperatorSign operatorSign) {

  }

  @Override
  public void numberParsed(String number) {
    System.out.println("before : " + number);
    count++;
    if (this.count == 1) {
      throw new IllegalStateException();
    }
//    if (this.number != null) {
//      throw new IllegalStateException();
//    }
    numbers.add(number);
    System.out.println("after : " + number);
  }

  @Override
  public void closeBracketFound() {

  }

  public List<String> getNumbers() {
    return numbers;
  }
}
