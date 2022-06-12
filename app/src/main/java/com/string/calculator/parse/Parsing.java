package com.string.calculator.parse;

import java.util.List;

public class Parsing {

  private final ParsingHandler parsingHandler;

  public Parsing(ParsingHandler parsingHandler) {
    this.parsingHandler = parsingHandler;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    CharacterStream characterStream = new CharacterStream(chars);


    // 어떻게 해야 올바르게 인터페이스를 뽑아냈다고 할 수 있을까? 고민
    ParsingCharacterWithLast parsingCharacterWithLast = new ParsingCharacterWithLast(
        parsingHandler);
    characterStream.forEachLastAware(parsingCharacterWithLast::execute);
  }

  /**
   * 극과 극
   * 이거 더 몇줄 읽는다고 뭐가 달라지냐?
   * -> 쌓이고 쌓여서 몇천 몇만 줄이 private 이어야 하는데 테스트를 위해 public
   * 뭐가 달라지지?
   * -> 시간이 많이 든다. -> 생산성이 떨어진다. -> 실수도 할 수 있다. -> 테스트를 못하는 비용보다 더 크다
   *
   * 1. private 메소드를 테스트 해야하나?
   * 2. private 메소드를 테스트 해야했을때, 어떻게 해야하는가?
   * 3. private 메소드를 테스트하지 않기로 했을때 해당 메소드만을 테스트를 어떻게 하지?
   *
   * 복잡한 private 메소드를 테스트 한다
   * 장점 : 오류 빠르게 찾을 수 있음
   * 단점 : IDE의 도움을 받지 못함 -> 생산성이 떨어진다.
   *
   * 복잡한 private 메소드를 테스트하지 않는다.
   * 장점 : 테스트 작성하는데에 시간을 들이지 않는다.
   * 단점 : 디버깅이 힘들다.
   * 소프트웨어 개발은 길게 봐야한다
   *
   * 구조적으로 해결한다.
   * 없애는 방법 : 신분 상승
   *
   */
//  private void execute(Character c, boolean last) {
//    if (OperatorSign.isSupportedOperator(c)) {
//      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
//      return;
//    }
//
//    if (canNumberParsed(c)) {
//      parsingHandler.numberParsed(numberPiece.getNumber());
//      numberPiece.makeEmpty();
//      return;
//    }
//
//    if (isNumberPiece(c)) {
//      numberPiece.add(c);
//      if (last) {
//        if (numberPiece.hasNumber()) {
//          parsingHandler.numberParsed(numberPiece.getNumber());
//          numberPiece.makeEmpty();
//        }
//      }
//    }
//  }
//
//  private boolean isNumberPiece(Character c) {
//    return c >= '0' && c <= '9';
//  }
//
//  private boolean canNumberParsed(char c) {
//    return c == ' ' && numberPiece.hasNumber();
//  }


}
