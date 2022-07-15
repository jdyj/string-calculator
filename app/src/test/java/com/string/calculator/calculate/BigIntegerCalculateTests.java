package com.string.calculator.calculate;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("큰 수")
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
    assertEquals("487394572887833913433127929363657507", calculate.plus());
  }

  @Test
  @DisplayName("곱셈")
  void multiplyTest() {
    assertEquals("238519490888624019359755491028456186644519216126524260758038232",
        calculate.multiply());
  }

  @Test
  @DisplayName("나눗셈")
  void divideTest() {
    calculate = new BigIntegerOperation("1000000", "10");
    assertEquals("100000", calculate.divide());
  }

  @Test
  @DisplayName("나머지 연산")
  void modularTest() {
    calculate = new BigIntegerOperation("53435224", "45457");
    assertEquals("23249", calculate.modular());
  }

  @Test
  @DisplayName("나눗셈 - 분자가 1일때")
  void divideTest1() {
    calculate = new BigIntegerOperation("1", "353535");
    assertEquals("1/353535", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 작을 때")
  void divideTest2() {
    calculate = new BigIntegerOperation("35", "353535");
    assertEquals("1/10101", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 클 때")
  void divideTest3() {
    calculate = new BigIntegerOperation("10", "4");
    assertEquals("5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 클 때")
  void divideTest4() {
    calculate = new BigIntegerOperation("10", "4");
    assertEquals("5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 음수 일 때")
  void divideTest5() {
    calculate = new BigIntegerOperation("-10", "4");
    assertEquals("-5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분모가 음수 일 때")
  void divideTest6() {
    calculate = new BigIntegerOperation("10", "-4");
    assertEquals("-5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자, 분모가 음수 일 때")
  void divideTest7() {
    calculate = new BigIntegerOperation("-10", "-4");
    assertEquals("5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자, 분모가 음수 일 때")
  void divideTest8() {
    calculate = new BigIntegerOperation("-10", "-4");
    assertEquals("5/2", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1일 때")
  void divideTest9() {
    calculate = new BigIntegerOperation("10", "3");
    assertEquals("10/3", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어질 때")
  void divideTest10() {
    calculate = new BigIntegerOperation("10", "2");
    assertEquals("5", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분자가 음수 일 때")
  void divideTest11() {
    calculate = new BigIntegerOperation("-10", "2");
    assertEquals("-5", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분모가 음수 일 때")
  void divideTest12() {
    calculate = new BigIntegerOperation("10", "-2");
    assertEquals("-5", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분자, 분모가 음수 일 때")
  void divideTest13() {
    calculate = new BigIntegerOperation("-10", "-2");
    assertEquals("5", calculate.divide());
  }

  @Test
  @DisplayName("나눗셈 - 분자 분모가 같을 때")
  void divideTest14() {
    calculate = new BigIntegerOperation("10", "10");
    assertEquals("1", calculate.divide());
  }

}