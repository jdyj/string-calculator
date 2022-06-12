package com.string.calculator.parse;

import static org.junit.jupiter.api.Assertions.*;

import com.string.calculator.OperatorSign;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ParsingCharacterWithLastTests {

  ParsingHandlerGetter parsingHandler = new ParsingHandlerGetter();
  ParsingCharacterWithLast caller = new ParsingCharacterWithLast(parsingHandler);

  @Test
  @DisplayName("피연산자 파싱 - 맨 뒤에 공백이 존재하지 않을 때")
  void executeTest1() {
    boolean last = false;
    for (int i = 1; i < 10; i++) {
      if (i == 9) {
        last = true;
      }
      caller.execute((char) (i + '0'), last);
    }

    assertEquals("123456789", parsingHandler.getNumbers().get(0));
  }

  @Test
  @DisplayName("피연산자 파싱 - 맨 뒤에 공백이 존재할 때")
  void executeTest2() {
    for (int i = 1; i < 10; i++) {
      caller.execute((char) (i + '0'), false);
    }
    caller.execute(' ', false);

    assertEquals("123456789", parsingHandler.getNumbers().get(0));
  }

  @Test
  @DisplayName("연산자 파싱")
  void executeTest3() {
    caller.execute('*', false);
    assertEquals(OperatorSign.multiply, parsingHandler.getOperatorSign());
  }

}