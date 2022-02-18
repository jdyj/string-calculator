package com.string.calculator;

import com.string.calculator.operator.Operator;
import java.util.List;

public class Calculate {

  private final Operator operator;
  private final Number number = new Number();

  public Calculate() {
    operator = new Operator(number);
  }

  public String run(String input) {

    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (Character c : chars) {
      operator.addOperatorToStack(c);
      number.add(c);
    }

    operator.checkLastStack();

    return getResult();
  }

  public String getResult() {
    while (number.size() > 1) {
      operator.calculateOne();
    }
    return number.getNumber();
  }

}
