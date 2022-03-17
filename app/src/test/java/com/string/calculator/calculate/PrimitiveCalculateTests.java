package com.string.calculator.calculate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("long 타입 내의 정수")
class PrimitiveCalculateTests {

  ArithmeticOperation calculate;

  @BeforeEach
  void init() {
    calculate = new PrimitiveOperation("48937",
        "487394573");
  }

  @Test
  @DisplayName("덧셈")
  void addTest() {
    assertEquals(calculate.add(), "487443510");
  }

  @Test
  @DisplayName("뺄셈")
  void subtractTest() {
    assertEquals(calculate.subtract(), "-487345636");
  }

  @Test
  @DisplayName("곱셈")
  void multiplyTest() {
    assertEquals(calculate.multiply(),
        "23851628218901");
  }


}