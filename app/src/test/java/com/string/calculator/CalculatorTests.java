package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.expression.Expression;
import com.string.calculator.expression.LongExpression;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTests {

  Calculator2 calculator2 = new Calculator2();

  @Test
  @DisplayName("main 테스트 - 여러가지 연산자")
  void main1() {
    String input =
        "32948295 + 45094094 - 459933 / 3 + 230592035 * 4";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(1000257218L, result.getNumber());
    assertEquals(0, result.getIndex());

  }

  @Test
  @DisplayName("main 테스트 - 괄호가 한번 들어간 경우")
  void main16() {
    String input = "134 * ( 342 + 3259 * 1234234124 - 123234235 + 545675645 * 43 ) - 3412";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(542125117076960L, result.getNumber());
  }

  @Test
  @DisplayName("main 테스트 - 음수가 들어간 경우")
  void main15() {
    String input = "9 - 5 - 4 + 3";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(3L, result.getNumber());
  }


  @Test
  @DisplayName("main 테스트 - 괄호가 여러번 들어간 경우")
  void main17() {
    String input = "134 * ((342 + 3259)*(1234234124-123234235)+(545675645*43))-3412";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(539239403501804L, result.getNumber());
  }


}