package string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculateTests {

  private final Calculate calculate = new Calculate();

  @Test
  @DisplayName("우선순위(괄호)가 들어간 계산")
  void runTest() {
    String input = "5 * (1+3/3*4+(5-6)*7) - 5";
    assertEquals(calculate.run(input), -15);
  }

  @Test
  @DisplayName("divide 0")
  void divide0Test() {
    String input = "5 / 0";
    assertThrows(IllegalStateException.class, () -> calculate.run(input));
  }

  @Test
  @DisplayName("중첩 괄호 4개")
  void runTest2() {
    String input = "((((1+3))))";
    assertEquals(calculate.run(input), 4);
  }

}