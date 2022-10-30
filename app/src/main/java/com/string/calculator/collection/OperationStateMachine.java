package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import com.string.calculator.Operator;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.ExpressionFactory;
import com.string.calculator.operator.bi.BiOperator;
import com.string.calculator.operator.bi.OperatorFactory;
import com.string.calculator.parse.ParsingHandler;
import java.util.Date;

public class OperationStateMachine implements ParsingHandler {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final StateHandler stateHandler;
  private final ExpressionFactory expressionFactory;

  public OperationStateMachine(StateHandler stateHandler,
      ExpressionFactory expressionFactory) {
    this.stateHandler = stateHandler;
    this.expressionFactory = expressionFactory;
  }

  // 도메인은 잊어라..
  // 문제..가 있다
  // 뭐가됐든지 간에 문제가 있다
  // 계산 -> 문제
  // 계산을 해주는 기능이 핵심
  // 계산을 해주는 로직을 도메인
  // 문제 영역 -> 도메인
  // 문제들 사이의 위계질서
  // 모델링 잘못하면
  // 개념 변경이 없으면 코드 변경이 없고..
  // 개념 변경이 작으면 코드 변경이 작다,
  // 개념 변경이 크면 코드 변경도 크다
  //


  public Expression getCalculatedValue() {

    // 후위 연산자 세팅
    while (!operatorCollection.isEmpty()) {
      OperatorSign operatorSign = operatorCollection.getLastElementAndRemove();
      numberCollection.add(operatorSign);
    }

    // 계산 히스토리 보내기
    // 더러운 곳은 한곳에.. 그래야 리팩토링 하는 지점이 한눈에 보일 수 있다.
//    List<Indexed> first = new ArrayList<>(numberCollection.getQueue());
//    Collections.sort(first);
//    calculation.피연산자계산중이야(first);

    // 의존성
    // 생성자, parameter, //-static, field, //-setter
    // 생성자, parameter
    // 언제 어떻게? 편한거는 파라미터
    // 어떻게하면 생성자로 넘길수 있는가 생성자로 넘기면 좋은가???????..
    // 1 + 2
    // 1 2 +
    // 스택을 감싸고 있는 로직도 옮겨야 돼서 어려움
    numberCollection.forEach((lastElement) -> {
      if (lastElement instanceof OperatorSign) {
        if (isBracket((OperatorSign) lastElement)) {
//          calculation.괄호계산중이야((OperatorSign) lastElement);
        } else {
          OperatorFactory operatorFactory = new OperatorFactory();
          BiOperator biOperator = operatorFactory.create((OperatorSign) lastElement);
          // Expression 인터페이스의 하위 구현체를 Factory에서 생성하겠다.
          Expression expression = expressionFactory.create(
              // 1번 - 파라미터 개수가 너무 많다.
              // 2번 - 다 파라미터로 넘기는 기분이다. 관련없는 것도 넘기는 기분
              // 3번 - 객체지향스럽지 않다.

              // state가 "계산"을 모르게 이름을 짜고싶다.....
              stateHandler.hereBiOperator(biOperator));
          stateHandler.hereExpression(expression.evaluate());
        }
      } else if (lastElement instanceof Expression) {
        stateHandler.hereExpression((Expression) lastElement);
      }
    });

    return expressionFactory.create(stateHandler.getResult()).evaluate();
  }

  @Override
  public void operatorParsed(OperatorSign operatorSign) {
    if (existHighOperatorSign(operatorSign)) {
      OperatorSign lastElement = operatorCollection.getLastElementAndRemove();
      numberCollection.add(lastElement);
    }
    operatorCollection.add(operatorSign);
  }

  @Override
  public void numberParsed(Expression number) {
    numberCollection.add(number);
  }

  @Override
  public void closeBracketFound(OperatorSign operatorSign) {
    numberCollection.add(operatorSign);
    while (!isOpenBracket()) {
      OperatorSign lastElement = operatorCollection.getLastElementAndRemove();
      numberCollection.add(lastElement);
    }
    OperatorSign openBracket = operatorCollection.getLastElementAndRemove();
    numberCollection.add(openBracket);
  }

  private boolean isOpenBracket() {
    return (operatorCollection.getLastElement().getOperator() == Operator.openBracket);
  }

  private boolean existHighOperatorSign(OperatorSign operatorSign) {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    Operator lastOperator = operatorCollection.getLastElement().getOperator();
    if (lastOperator == Operator.openBracket) {
      return false;
    }

    return lastOperator.getPriority() > operatorSign.getOperator().getPriority();
  }

  private boolean isBracket(OperatorSign operatorSign) {
    boolean isCloseBracket = operatorSign.getOperator() == Operator.closeBracket;
    boolean isOpenBracket = operatorSign.getOperator() == Operator.openBracket;
    return isOpenBracket || isCloseBracket;
  }


}
