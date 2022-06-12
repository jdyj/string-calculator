package com.string.calculator.parse;

import com.string.calculator.OperatorSign;

class ParsingCharacterWithLast {

  private final NumberPiece numberPiece = new NumberPiece();
  private final ParsingHandler parsingHandler;

  public ParsingCharacterWithLast(ParsingHandler parsingHandler) {
    this.parsingHandler = parsingHandler;
  }

  public void execute(Character c, boolean last) {
    if (OperatorSign.isSupportedOperator(c)) {
      parsingHandler.operatorParsed(OperatorSign.valueOf(c));
      return;
    }

    if (canNumberParsed(c)) {
      parsingHandler.numberParsed(numberPiece.getNumber());
      numberPiece.makeEmpty();
      return;
    }

    if (isNumberPiece(c)) {
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          parsingHandler.numberParsed(numberPiece.getNumber());
          numberPiece.makeEmpty();
        }
      }
    }
  }

  /**
   * 어떤 private 메소드를 public 메소드로 바꿀거냐?
   * 기준에 대해서 고민해보기.
   * 주관적인 견해가 나올수 밖에 없다.
   * 다만 합리적인 이유가 있어야 한다.
   *
   * 테스트를 해야할 것 같은 private 메소드
   * 테스트를 해야할 것 같은 코드는 무엇인가?
   * 로직이 담겨있는 코드
   * 로직이 담겨있다는 기준은 무엇인가?
   * 여러 if 문, for 문 등이 있고 코드를 읽었을 때 한눈에 읽을 수 있을 것 같은 코드
   * 코드 라인 수가 낮으면 로직도 아닌가?
   * 그건 아닌데 .. 코드 라인 수가 낮으면 굳이 테스트를 안해도 괜찮지 않을까?
   * 다시 생각해보니 코드 라인 수가 낮아도 테스트를 해야 하면 public 메소드로 바꾸는게 맞는 것 같음.
   * 그러면 다시 테스트를 해야할 코드는 무엇인가?
   * 핵심적인 코드
   * 핵심적인 코드의 기준은 무엇인가?
   * 확실한건 아래의 isNumberPiece 와 같은 코드는 핵심적인 코드가 아니다.
   * 왜? -> if 문의 조건을 위한 코드이기 때문에 핵심적인 코드라고 말할 수 없다.
   * 그렇다면? 애플리케이션의 기능 및 부가적인 기능들 ??
   *
   * 어디까지 테스트 할것인가를 정하는 작업
   *
   * 예) 자동차 판매
   * 자동차를 테스트 하고싶다
   *
   * 테스트 - 기술적으로 판단해선 안된다.
   * 클래스 or 메소드가 갖는 의미
   * 사용되는 곳이 어딘지를 따라서 테스트를 할지말지 고민...
   * 최종 사용자 입장에선 Parsing 도 할필요 없다.
   * -> 세분도를 정해야 함.
   *
   * 통합 테스트, 단위 테스트
   *
   * 세분도
   * 경계
   * 내가 중요하다고 생각되는 부분
   *
   * 기존 -> private 메소드로 다 나눠놨음 -> 테스트 불가
   *
   * 파싱, 계산 분리
   *
   * 세부사항에 대해서 테스트를 하면 의미가 없어질 가능성이 높다.
   * -> 세부사항이라는 것은 자주 바뀔 가능성이 있다. 안정적이지 못하다.
   * 추상화 된것 -> 인터페이스
   *
   * 테스트 코드, 프로덕션 코드
   *
   * 세부사항이라는 것이 주관적이다.
   * 많은 사람들이 세부사항을 정하는 기준.
   * -> 많은 패턴들
   */
  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
  }

  private boolean canNumberParsed(char c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
