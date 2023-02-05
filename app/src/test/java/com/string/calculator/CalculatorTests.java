package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.calculate.Calculate;
import com.string.calculator.expression.HistoryAddingExpressionFactory;
import com.string.calculator.expression.LongExpression;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculatorTests {

  CalculationGetter calculationHistory = new CalculationGetter();
  Calculator calculator2 = new Calculator(new HistoryAddingExpressionFactory(calculationHistory),
      new Calculate());

  @Test
  @DisplayName("main 테스트 - 여러가지 연산자")
  void main1() {
    String input =
        "1 + 2 - 3 / 3 + 4 * 5";

    LongExpression result = (LongExpression) calculator2.calculate(input);

    assertEquals(22, result.getNumber());
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
    String input = "1 + 1 + 1 + 6 * 5 + 1";

    // parser : 파싱하는 것
    // ㄴ parsingHandler
    //   ㄴ String? Expression?
    // stateMachine : 후위 연산자로 상태를 저장하는 것
    //                1. 상태 저장 4. 상태 가공
    // ㄴ expression : 괄호 처리 및 계산
    // ㄴ numberCollection : 후위 연산자로 상태 저장
    // ㄴ calculationHistory : 계산 과정 시각화
    // 1. 역할 분리가 잘못 됨 2. 의사소통이 잘 안됐다
    // 계산이 주가 되어야하는데 계산 과정시각화, 상태 이상이 주가 된 느낌이다.

    LongExpression result = (LongExpression) calculator2.calculate(input);

    List<String> expected = new ArrayList<>();
    expected.add("1 + 1 + 1 + 6 * 5 + 1");
    expected.add("1 + 1 + 1 + 30 + 1");
    expected.add("1 + 1 + 1 + 31");
    expected.add("1 + 1 + 32");
    expected.add("1 + 33");
    expected.add("34");

    assertEquals(34L, result.getNumber());
    assertEquals(expected, calculationHistory.getHistory());
  }


}