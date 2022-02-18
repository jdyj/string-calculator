package com.string.calculator.operator;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlusTests {

  @Test
  @DisplayName("부호가 두개다 양수이고 왼 수가 클 때")
  void addTest1() {

    String leftValue = "55555";
    String rightValue = "5555";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "61110");

  }

  @Test
  @DisplayName("부호가 두개다 양수이고 오른 수가 클 때")
  void addTest2() {

    String leftValue = "5555";
    String rightValue = "55555";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "61110");

  }


  @Test
  @DisplayName("왼 수가 음수, 오른 수가 양수, 결과는 음수일 때")
  void addTest3() {

    String leftValue = "-4285376843592342543564544546345244563452424353444";
    String rightValue = "894328748920483984234498848359984";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "-4285376843592341649235795625861260328953575993460");

  }

  @Test
  @DisplayName("왼 수가 음수, 오른 수가 양수, 결과는 양수일 때")
  void addTest4() {

    String leftValue = "-4285376843592342543564544546345244563452424353444";
    String rightValue = "89432874892048398423443124435678765434345676865453498848359984";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "89432874892044113046599532093135200889799331620890046424006540");

  }

  @Test
  @DisplayName("왼 수가 양수, 오른 수가 음수, 결과는 양수일 때")
  void addTest5() {

    String leftValue = "4123413223432634261102193285376843592342543564544546345244563452424353444";
    String rightValue = "-8943287489204839842344312000004435678765434345676865453498848359984";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "4123404280145145056262350941064843587906864799110200668379109953575993460");

  }

  @Test
  @DisplayName("왼 수가 양수, 오른 수가 음수, 결과는 음수일 때")
  void addTest6() {

    String leftValue = "4123413223432634261102193285376843592342543564544546345244563452424353444";
    String rightValue = "-894328748920483984234431200000443567870033346346347357546846865434345676865453498848359984";

    Plus plus = new Plus(leftValue, rightValue);
    String result = plus.add();

    assertEquals(result, "-894328748920483980111017976567809306767840060969503765204303300889799331620890046424006540");

  }

}