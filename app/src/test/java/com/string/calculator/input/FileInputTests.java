package com.string.calculator.input;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FileInputTests {

  FileInput fileInput = new FileInput();

  @Test
  void fileInputTest() {

    assertEquals("1234 + 4312", fileInput.enter());


  }

}