package com.string.calculator.output;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlainOutputTests {

  PlainFormat plainOutput;

  @Test
  @DisplayName("정수일 때")
  void plainTest1() {
    plainOutput = new PlainFormat("56465534");
    assertEquals("num : 56465534\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 분모가 분자보다 클 때")
  void plainTest2() {
    plainOutput = new PlainFormat("56465534/134546357656675645");
    assertEquals("     56465534\n------------------\n134546357656675645\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 분모가 분자보다 작을 때")
  void plainTest3() {
    plainOutput = new PlainFormat("134546357656675645/56465534");
    assertEquals("134546357656675645\n------------------\n     56465534\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 음수일 때")
  void plainTest4() {
    plainOutput = new PlainFormat("-56465534/134546357656675645");
    assertEquals("       56465534\n- ------------------\n  134546357656675645\n", getOutput());
  }

  private String getOutput() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);
    System.setOut(printStream);
    plainOutput.make();
    return out.toString();
  }

}