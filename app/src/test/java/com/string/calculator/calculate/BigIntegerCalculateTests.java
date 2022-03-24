package com.string.calculator.calculate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("큰 수 (long 타입 범위 밖)")
class BigIntegerCalculateTests {

  ArithmeticOperation calculate;

  @BeforeEach
  void init() {
    calculate = new BigIntegerOperation("489376583975638392234277384",
        "487394572398457329457489537129380123");
  }

  @Test
  @DisplayName("덧셈")
  void addTest() {
    assertEquals(calculate.add(), "487394572887833913433127929363657507");
  }

  @Test
  @DisplayName("뺄셈")
  void subtractTest() {
    assertEquals(calculate.subtract(), "-487394571909080745481851144895102739");
  }

  @Test
  @DisplayName("곱셈")
  void multiplyTest() {
    assertEquals(calculate.multiply(),
        "238519490888624019359755491028456186644519216126524260758038232");
  }

}