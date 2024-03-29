/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.string.calculator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.text.NumberFormat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AppTest {

  String[] args = null;

  @Test
  @DisplayName("main 테스트 - 여러가지 연산자")
  void main1() {
    String input =
        "32435456436754325674356756762221212798 - 342283024803781287013 * 235465789800876543223456543454 + 12341251246 - 3453476564534 + 13476857565743 * 1234567";

    assertEquals("num : -80595942770822941182435736008003954206997027825111\n",
        runApplication(input, args));

  }

  @Test
  @DisplayName("main 테스트 - 곱셈만 있는 경우")
  void main3() {
    String input =
        "32435456436754325674356756762221212798 * 23452342345 * 233263464646 * 46565656565623423412312312134 * 2111235235";
    assertEquals(
        "num : 17444371097925106196697244640519041351612312748277072066098638108923528793486072764437728559127400\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 덧셈만 있는 경우")
  void main4() {
    String input =
        "324354564367543256 + 2134134 + 23635465786564 + 2364543654748654566787 + 876863213223123 + 437";
    assertEquals("num : 2364868909811703254301\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 뺄셈만 있는 경우")
  void main5() {
    String input =
        "3243545643675438 - 34228302480";
    assertEquals("num : 3243511415372958\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 숫자만 있는 경우")
  void main8() {
    String input = "1234";
    assertEquals("num : 1234\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 숫자와 공백만 있는 경우")
  void main9() {
    String input = "    1234   ";
    assertEquals("num : 1234\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 괄호가 한번 들어간 경우")
  void main16() {
    String input = "134 * ( 342 + 3259 * 1234234124 - 123234235 + 545675645 * 43 ) - 3412";
    assertEquals("num : 542125117076960\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 괄호가 여러번 들어간 경우")
  void main17() {
    String input = "( ( ( ( ( ( ( ( 5 ) ) ) ) ) ) ) ) + 1234 * ( 552335 - 556 ) + 254637537 ";
    assertEquals("num : 935532828\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 나눗셈이 들어간 경우")
  void main18() {
    String input = "1 / 10 / 10 / 10 / 10";
    assertEquals("num : 1/10000\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 나눗셈이 들어간 경우 분수 표현")
  void main20() {
    String input = "1 / 1000000 * 12394128034912093412 + 3249583493459 - 2348101125 / 5 * 23 + 23491084 - 2349";
    assertEquals("num : 3908233437982773353/250000\n", runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 나눗셈이 들어간 경우 분수 표현")
  void main21() {
    String input = "1675816609256 / 12341259849 + 54968679658075 * 45984569 + 546456834964386934869345684359683495846 / 23453409980394580439 + 093490954034990345059409854 - 4305898943089849";
    assertEquals(
        "num : 304058344781057697392000136650086691053099057512053462/3252186819249208008416084199\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 나눗셈이 들어간 경우 분수 표현")
  void main22() {
    String input = "1675816609256 / 12341259849 - 546456834964386934869345684359683495846 / 23453409980394580439";
    assertEquals(
        "num : -75774896590534918931456404117459707566162630830/3252186819249208008416084199\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 음수 곱셈")
  void main23() {
    String input = "8 * -8";
    assertEquals(
        "num : -64\n",
        runApplication(input, args));
  }

  @Test
  @DisplayName("main 테스트 - 음수 나눗셈")
  void main24() {
    String input = "-8 / -8";
    assertEquals(
        "num : 1\n",
        runApplication(input, args));
  }

  private String runApplication(String input, String[] args) {
    input = input + "\n3\n1";
    InputStream in = new ByteArrayInputStream(input.getBytes());
    OutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);
    System.setOut(printStream);
    System.setIn(in);
    App.main(args);
    return out.toString().substring(95);
  }


}
