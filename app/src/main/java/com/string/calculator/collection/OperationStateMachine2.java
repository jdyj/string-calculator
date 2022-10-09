package com.string.calculator.collection;

import com.string.calculator.Indexed;
import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;
import com.string.calculator.Operator;
import com.string.calculator.parse.ParsingHandler2;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperationStateMachine2 implements ParsingHandler2 {

  private final NumberCollection2 numberCollection = new NumberCollection2();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final Stack<Expression> stack = new Stack<>();
  private final Calculate2 calculate = new Calculate2();
  private final Calculation calculation;

  public OperationStateMachine2(Calculation calculation) {
    this.calculation = calculation;
  }

  public Expression getCalculatedValue() {

    // 후위 연산자 세팅
    while (!operatorCollection.isEmpty()) {
      OperatorSign operatorSign = operatorCollection.getLastElementAndRemove();
      numberCollection.add(operatorSign);
    }

    // 계산 히스토리 보내기
    List<Indexed> first = new ArrayList<>(numberCollection.getQueue());
    calculation.피연산자계산중이야(first);
    numberCollection.forEach((lastElement) -> {
      if (lastElement instanceof OperatorSign) {
        if (isBracket((OperatorSign) lastElement)) {
          calculation.괄호계산중이야((OperatorSign) lastElement);
        } else {
          Expression right = stack.pop();
          Expression left = stack.pop();
          Expression result = calculate.one(left, right, (OperatorSign) lastElement);
          stack.add(result);

          // 히스토리
          List<Indexed> history = new ArrayList<>(numberCollection.getQueue());
          history.addAll(stack);
          calculation.피연산자계산중이야(history);
        }
      } else if (lastElement instanceof Expression) {
        stack.add((Expression) lastElement);
      }
    });

    return stack.pop();
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


  // 계산 + 히스토리
  private void addStackWithHistory(OperatorSign operatorSign) {
    Expression right = stack.pop();
    Expression left = stack.pop();
    Expression result = calculate.one(left, right, operatorSign);
    stack.add(result);

    // 히스토리
    List<Indexed> history = new ArrayList<>(numberCollection.getQueue());
    history.addAll(stack);
    calculation.피연산자계산중이야(history);
  }


}
