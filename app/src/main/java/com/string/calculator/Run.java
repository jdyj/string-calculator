package com.string.calculator;


import static java.util.stream.Collectors.toList;

import java.util.List;

public class Run {

  private final Number number = new Number();

  public String run(String input) {

    Operator operator = new Operator(number);

    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (Character c : chars) {

      number.ifBlankCheckNumberInStringBuilder(c);
      operator.ifExistHighOperatorCalculate();

      number.addNumberToStringBuilder(c);
      operator.addOperatorSignToStack(c);

    }

    number.addNumberToStackWithStringBuilder();
    operator.ifExistHighOperatorCalculate();


    return operator.calculateLeftover();
  }

}
