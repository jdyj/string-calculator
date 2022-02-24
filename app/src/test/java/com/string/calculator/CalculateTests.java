package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculateTests {

  Calculate calculate;

  @BeforeEach
  void init() {
    calculate = new Calculate("1254364657657", "5768679765432134567890");
  }

  @Test
  @DisplayName("덧셈")
  void addTest() {
    assertEquals(calculate.add(), "5768679766686499225547");
  }

  @Test
  @DisplayName("뺄셈")
  void subtractTest() {
    assertEquals(calculate.subtract(), "-5768679764177769910233");
  }

  @Test
  @DisplayName("곱셈")
  void multiplyTest() {
    assertEquals(calculate.multiply(), "7236028019099142539918095474833730");
  }

}