package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.calculate.OperationFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FractionTests {

  OperationFactory operationFactory = new OperationFactory();
  Fraction fraction = new Fraction(operationFactory);

  @Test
  @DisplayName("곱셈 - 좌측 음수 분수, 우측 음수 정수")
  void fractionTest1() {
    String result = fraction.calculate("234234/-1234123", "-12512451245", OperatorSign.multiply);
    assertEquals("266440136811030/112193", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 분수, 우측 분수")
  void fractionTest2() {
    String result = fraction.calculate("234234/1234123", "543655437/3254356743254",
        OperatorSign.plus);
    assertEquals("69359266825294017/365116046095896022", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 정수, 우측 분수")
  void fractionTest3() {
    String result = fraction.calculate("324325235", "235235235/2352355578867", OperatorSign.plus);
    assertEquals("254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 분수, 우측 정수")
  void fractionTest4() {
    String result = fraction.calculate("235235235/2352355578867", "324325235", OperatorSign.plus);
    assertEquals("254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 음수 분수, 우측 정수")
  void fractionTest5() {
    String result = fraction.calculate("-235235235/2352355578867", "324325235", OperatorSign.plus);
    assertEquals("254309425306455191170/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 음수 분수, 음수 우측 정수")
  void fractionTest6() {
    String result = fraction.calculate("-235235235/2352355578867", "-324325235", OperatorSign.plus);
    assertEquals("-254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 정수, 우측 음수 분수")
  void fractionTest7() {
    String result = fraction.calculate("12512451245", "234234/-1234123", OperatorSign.multiply);
    assertEquals("-266440136811030/112193", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 양수 분수, 우측 음수 정수")
  void fractionTest8() {
    String result = fraction.calculate("-234234/-1234123", "-12512451245", OperatorSign.multiply);
    assertEquals("-266440136811030/112193", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 양수 분수, 우측 음수 정수")
  void fractionTest9() {
    String result = fraction.calculate("1675816609256/12341259849", "-546456834964386934869345684359683495846/23453409980394580439", OperatorSign.plus);
    assertEquals("-75774896590534918931456404117459707566162630830/3252186819249208008416084199", result);
  }
}