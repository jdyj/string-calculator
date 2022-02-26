package com.string.calculator.calculate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.string.calculator.calculate.Calculate;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CalculateTests {

  Calculate calculate;

  @Test
  @DisplayName("input 값이 long 범위 안일때")
  void checkValueInLongTypeTest1() {
    assertTrue(Calculate.checkValueInLongType("2346599894378566"));
  }

  @Test
  @DisplayName("input 값이 long 범위 밖일때")
  void checkValueInLongTypeTest2() {
    assertFalse(Calculate.checkValueInLongType("-23465998945675434564325646754643565544378566"));
  }

}