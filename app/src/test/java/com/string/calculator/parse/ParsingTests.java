package com.string.calculator.parse;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doCallRealMethod;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

import com.string.calculator.App;
import com.string.calculator.Number;
import com.string.calculator.OperatorSign;
import org.junit.jupiter.api.BeforeEach;
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

  @BeforeEach
  void init() {
    MockitoAnnotations.openMocks(this);
  }

  @Test
  void parseTest() {

    doNothing().when(parsingHandler).numberParsed(any());
    doNothing().when(parsingHandler).operatorParsed(any());

    parsing.parse("123 + 23492");

    verify(parsingHandler, times(1)).numberParsed(new Number("123"));
    verify(parsingHandler, times(1)).numberParsed(new Number("23492"));
    verify(parsingHandler, times(1)).operatorParsed(OperatorSign.plus);

  }

  // 1. 테스트 자동화
  // 2. 버그 고치기

  /**
   * 파싱 객체와 파싱핸들러 인터페이스가 서로 메시지를 주고 받을 때 어떤 약속 위에서 서로 이야기하고 있는지? 약속
   * <p>
   * mockito 명세 기능 구현 예 : 메소드를 두 번 던졌을 때 예외를 던져라
   * <p>
   * Matcher 구현
   */
  @Test
  void parseTest2() {
    doCallRealMethod().when(parsingHandler).numberParsed(any());
    doCallRealMethod().when(parsingHandler).operatorParsed(any());

    parsing.parse("1 + 2");
    parsing.parse("3 + 4");

    verify(parsingHandler, times(1)).numberParsed(new Number("1"));
    verify(parsingHandler, times(1)).numberParsed(new Number("2"));
    verify(parsingHandler, times(1)).numberParsed(new Number("3"));
    verify(parsingHandler, times(1)).numberParsed(new Number("4"));
    verify(parsingHandler, times(2)).operatorParsed(any());

    verifyNoMoreInteractions(parsingHandler);
  }

}