package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class RunTests {

  Run run = new Run();

  @Test
  @DisplayName("계산 테스트")
  void runTest3() {
    assertEquals(run.run(
            "32435456436754325674356756762221212798 - 342283024803781287013 * 235465789800876543223456543454 + 12341251246 - 3453476564534 + 13476857565743 * 1234567"),
        "-80595942770822941182435736008003954206997027825111");
  }

}