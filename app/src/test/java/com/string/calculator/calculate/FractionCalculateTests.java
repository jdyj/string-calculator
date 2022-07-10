package com.string.calculator.calculate;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.Fraction;
import com.string.calculator.OperatorSign;
import com.string.calculator.calculate.FractionOperation;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class FractionCalculateTests {

  @Test
  @DisplayName("곱셈 - 좌측 음수 분수, 우측 음수 정수")
  void fractionTest1() {
    FractionOperation fraction = new FractionOperation(new Fraction("234234/-1234123"),
        new Fraction("-12512451245"));
    String result = fraction.multiply();
    assertEquals("266440136811030/112193", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 분수, 우측 분수")
  void fractionTest2() {
    FractionOperation fraction = new FractionOperation(new Fraction("234234/1234123"),
        new Fraction("543655437/3254356743254"));
    String result = fraction.plus();
    assertEquals("69359266825294017/365116046095896022", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 정수, 우측 분수")
  void fractionTest3() {
    FractionOperation fraction = new FractionOperation(new Fraction("324325235"),
        new Fraction("235235235/2352355578867"));
    String result = fraction.plus();
    assertEquals("254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 분수, 우측 정수")
  void fractionTest4() {
    FractionOperation fraction = new FractionOperation(new Fraction("235235235/2352355578867"),
        new Fraction("324325235"));
    String result = fraction.plus();
    assertEquals("254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 음수 분수, 우측 정수")
  void fractionTest5() {
    FractionOperation fraction = new FractionOperation(new Fraction("-235235235/2352355578867"),
        new Fraction("324325235"));
    String result = fraction.plus();
    assertEquals("254309425306455191170/784118526289", result);
  }

  @Test
  @DisplayName("덧셈 - 좌측 음수 분수, 음수 우측 정수")
  void fractionTest6() {
    FractionOperation fraction = new FractionOperation(new Fraction("-235235235/2352355578867"),
        new Fraction("-324325235"));
    String result = fraction.plus();
    assertEquals("-254309425306612014660/784118526289", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 정수, 우측 음수 분수")
  void fractionTest7() {
    FractionOperation fraction = new FractionOperation(new Fraction("12512451245"),
        new Fraction("234234/-1234123"));
    String result = fraction.multiply();
    assertEquals("-266440136811030/112193", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 양수 분수, 우측 음수 정수")
  void fractionTest8() {
    FractionOperation fraction = new FractionOperation(new Fraction("-234234/-1234123"),
        new Fraction("-12512451245"));
    String result = fraction.multiply();
    assertEquals("-266440136811030/112193", result);
  }

  @Test
  @DisplayName("곱셈 - 좌측 양수 분수, 우측 음수 정수")
  void fractionTest9() {
    FractionOperation fraction = new FractionOperation(new Fraction("1675816609256/12341259849"),
        new Fraction("-546456834964386934869345684359683495846/23453409980394580439"));
    String result = fraction.plus();
    assertEquals("-75774896590534918931456404117459707566162630830/3252186819249208008416084199",
        result);
  }
}