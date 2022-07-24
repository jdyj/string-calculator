package com.string.calculator.operation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("long 타입 내의 정수")
class PrimitiveCalculateTests {

  NumberOperationFactory factory = new NumberOperationFactory();
  ArithmeticOperation calculate;

  @BeforeEach
  void init() {
    calculate = factory.createPrimitiveOperation("48937",
        "487394573");
  }

  @Test
  @DisplayName("덧셈")
  void addTest() {
    assertEquals("487443510", calculate.plus().getValue());
  }

  @Test
  @DisplayName("곱셈")
  void multiplyTest() {
    assertEquals("23851628218901", calculate.multiply().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분자가 1일때")
  void divideTest1() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("1", "353535");
    assertEquals("1/353535", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 작을 때")
  void divideTest2() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("35", "353535");
    assertEquals("1/10101", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 클 때")
  void divideTest3() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "4");
    assertEquals("5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 분모보다 클 때")
  void divideTest4() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "4");
    assertEquals("5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자가 음수 일 때")
  void divideTest5() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("-10", "4");
    assertEquals("-5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분모가 음수 일 때")
  void divideTest6() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "-4");
    assertEquals("-5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자, 분모가 음수 일 때")
  void divideTest7() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("-10", "-4");
    assertEquals("5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1이 아니고, 분자, 분모가 음수 일 때")
  void divideTest8() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("-10", "-4");
    assertEquals("5/2", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 최대 공약수가 1일 때")
  void divideTest9() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "3");
    assertEquals("10/3", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어질 때")
  void divideTest10() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "2");
    assertEquals("5", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분자가 음수 일 때")
  void divideTest11() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("-10", "2");
    assertEquals("-5", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분모가 음수 일 때")
  void divideTest12() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("10", "-2");
    assertEquals("-5", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분모가 나누어 떨어지고 분자, 분모가 음수 일 때")
  void divideTest13() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("-10", "-2");
    assertEquals("5", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - 분자 분모가 같을 때")
  void divideTest14() {
    PrimitiveOperation operation = factory.createPrimitiveOperation("2", "2");
    assertEquals("1", operation.divide().getValue());
  }

  @Test
  @DisplayName("나눗셈 - ")
  void divideTest15() {
    assertThrows(NumberFormatException.class, () -> factory.createPrimitiveOperation("--2", "2"));
  }

}