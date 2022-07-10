package com.string.calculator.output;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.App;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PlainOutputTests {

  PlainOutput plainOutput;

  @Test
  @DisplayName("정수일 때")
  void plainTest1() {
    plainOutput = new PlainOutput("56465534");
    assertEquals("num : 56465534\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 분모가 분자보다 클 때")
  void plainTest2() {
    plainOutput = new PlainOutput("56465534/134546357656675645");
    assertEquals("     56465534\n------------------\n134546357656675645\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 분모가 분자보다 작을 때")
  void plainTest3() {
    plainOutput = new PlainOutput("134546357656675645/56465534");
    assertEquals("134546357656675645\n------------------\n     56465534\n", getOutput());
  }

  @Test
  @DisplayName("분수일 때 - 음수일 때")
  void plainTest4() {
    plainOutput = new PlainOutput("-56465534/134546357656675645");
    assertEquals("       56465534\n- ------------------\n  134546357656675645\n", getOutput());
  }

  private String getOutput() {
    OutputStream out = new ByteArrayOutputStream();
    PrintStream printStream = new PrintStream(out);
    System.setOut(printStream);
    plainOutput.print();
    return out.toString();
  }

}