package com.string.calculator.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.string.calculator.OperatorSign;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

class ParsingTests {

//  ParsingHandlerGetter parsingHandlerGetter = new ParsingHandlerGetter();

  @InjectMocks
  Parsing parsing;

  @Mock
  ParsingHandler parsingHandler;

  // 테스트에서 확인하지 않은 것, 확인 방법이 잘못된 것이 있다.
  // 검사하는 방법도 올바른 방법은 아니다.
  // 이 방식으로 테스트했을 때, 검사해야할게 더 있다.
  // 1. 빠진 출력이 뭔지,
  // ->
  // 2. 테스트에서 assert 문 빠진게 뭐가 있는지
  // 3. 올바른 방법으로 검사하는것이 무엇인지,
  // mocking 사전적 의미

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

}