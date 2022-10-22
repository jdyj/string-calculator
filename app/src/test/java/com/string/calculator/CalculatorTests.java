package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.expression.BinaryExpressionFactory;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.HistoryAddingExpressionFactory;
import com.string.calculator.expression.LongExpression;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTests {

  CalculationGetter calculationHistory = new CalculationGetter();
  Calculator2 calculator2 = new Calculator2(new HistoryAddingExpressionFactory(calculationHistory),
      new BinaryExpressionFactory());

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
  void main2() {
    String input = "134 * ( 342 + 3259 * 1234234124 - 123234235 + 545675645 * 43 ) - 3412";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(542125117076960L, result.getNumber());
  }

  @Test
  @DisplayName("main 테스트 - 음수가 들어간 경우")
  void main3() {
    String input = "9 - 5 - (4 + 3)";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(-3L, result.getNumber());
  }


  @Test
  @DisplayName("main 테스트 - 괄호가 여러번 들어간 경우")
  void main4() {
    String input = "134 * ((342 + 3259)*(1234234124-123234235)+(545675645*43))-3412";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(539239403501804L, result.getNumber());
  }

  @Test
  void main5() {
    String input = "1";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    List<String> expected = new ArrayList<>();
    expected.add("1");
//    expected.add("2");

    assertEquals(1L, result.getNumber());
    assertEquals(expected, calculationHistory.getHistory());
  }

  @Test
  void main6() {
    String input = "1 + 1";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    List<String> expected = new ArrayList<>();
    expected.add("1 + 1");
    expected.add("2");

    assertEquals(2L, result.getNumber());
    assertEquals(expected, calculationHistory.getHistory());
  }

  @Test
  void main7() {
    String input = "1 + 1 + 1 * 6 + 5 * 1";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    List<String> expected = new ArrayList<>();
    expected.add("1 + 1");
    expected.add("2");

    assertEquals(13L, result.getNumber());
    assertEquals(expected, calculationHistory.getHistory());
  }


}