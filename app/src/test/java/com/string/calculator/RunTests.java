package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunTests {

  private final Run run = new Run();

  @Test
  @DisplayName("우선순위(괄호)가 들어간 계산")
  void runTest() {
    String input = "(((((((((((((5*(1*4+(5-6)*7*10000)*(((10564657465342324)))-5*456534456746 *564543356)))))))))))))";
    assertEquals(run.run(input), "-4986086291425624951400");
  }

//  @Test
//  @DisplayName("divide 0")
//  void divide0Test() {
//    String input = "5 / 0";
//    assertThrows(IllegalStateException.class, () -> calculate.run(input));
//  }

  @Test
  @DisplayName("중첩 괄호 4개")
  void runTest2() {
    String input = "((((1+3))))";
    assertEquals(run.run(input), "4");
  }

  @Test
  @DisplayName("큰수 계산")
  void runTest3() {
    String input = "-123121231+1231+1231-1231";
    assertEquals(run.run(input),
        "-123120000");
  }

}