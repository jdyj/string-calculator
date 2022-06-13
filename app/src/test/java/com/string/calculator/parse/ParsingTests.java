package com.string.calculator.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.string.calculator.App;
import com.string.calculator.OperatorSign;
import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.List;
import java.util.function.DoubleUnaryOperator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ParsingTests {

//  ParsingHandlerGetter parsingHandlerGetter = new ParsingHandlerGetter();

  @InjectMocks
  Parsing parsing;

  @Mock
  ParsingHandlerErrorGetter parsingHandler;

  // 테스트에서 확인하지 않은 것, 확인 방법이 잘못된 것이 있다.
  // 검사하는 방법도 올바른 방법은 아니다.
  // 이 방식으로 테스트했을 때, 검사해야할게 더 있다.
  // 1. 빠진 출력이 뭔지,
  // 2. 테스트에서 assert 문 빠진게 뭐가 있는지
  // 3. 올바른 방법으로 검사하는것이 무엇인지,
  // mocking 사전적 의미
  // netty, blocking call

  @Test
  void parseTest() {
    MockitoAnnotations.openMocks(this);

    doNothing().when(parsingHandler).numberParsed(any());
    doNothing().when(parsingHandler).operatorParsed(any());

    parsing.parse("123 + 23492");

    // mockito 테스트 변경
    // 몇번 받았는지
//    assertEquals(OperatorSign.plus, parsingHandlerGetter.getOperatorSign());
//    assertEquals("123", parsingHandlerGetter.getNumbers().get(0));
//    assertEquals("23492", parsingHandlerGetter.getNumbers().get(1));

    verify(parsingHandler, times(1)).numberParsed("123");
    verify(parsingHandler, times(1)).numberParsed("23492");
    verify(parsingHandler, times(1)).operatorParsed(OperatorSign.plus);

  }

  // 1. 테스트 자동화
  // 2. 버그 고치기
  @Test
  void parseTest2() {
    ParsingHandlerErrorGetter parsingHandlerErrorGetter = new ParsingHandlerErrorGetter();
    Parsing parsing1 = new Parsing(parsingHandlerErrorGetter);

    try {
      parsing1.parse("1 + 2");
    } catch (IllegalStateException e) {
    }
    parsing1.parse("3 + 4");

    List<String> numbers = parsingHandlerErrorGetter.getNumbers();
    assertEquals(3, numbers.size());
    assertEquals("2", numbers.get(0));
    assertEquals("3", numbers.get(1));
    assertEquals("4", numbers.get(2));
  }

}