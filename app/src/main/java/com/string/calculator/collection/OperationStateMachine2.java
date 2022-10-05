package com.string.calculator.collection;

import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;
import com.string.calculator.Operator;
import com.string.calculator.expression.LongExpression;
import com.string.calculator.parse.ParsingHandler2;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class OperationStateMachine2 implements ParsingHandler2 {

  private final NumberCollection2 numberCollection = new NumberCollection2();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final Stack<Expression> stack = new Stack<>();
  private final Stack<OperatorSign> bracketStack = new Stack<>();
  private final Calculate2 calculate = new Calculate2();
  private final Calculation calculation;

  public OperationStateMachine2(Calculation calculation) {
    this.calculation = calculation;
  }

  public Expression getCalculatedValue() {

    while (!operatorCollection.isEmpty()) {
      OperatorSign operatorSign = operatorCollection.getLastElementAndRemove();
      numberCollection.add(operatorSign);
    }
    List<Object> first = new ArrayList<>(numberCollection.getQueue());
    calculation.중이야(first);




    while (!numberCollection.isEmpty()) {
      Object lastElement = numberCollection.getLastElementAndRemove();

      if (lastElement instanceof OperatorSign && !isBracket((OperatorSign) lastElement)) {
        addStack((OperatorSign) lastElement);
        List<Object> history = new ArrayList<>(numberCollection.getQueue());
        history.addAll(stack);
        history.addAll(bracketStack);
        calculation.중이야(history);
      } else if (lastElement instanceof OperatorSign && isBracket((OperatorSign) lastElement)) {
        if (((OperatorSign) lastElement).getOperator() == Operator.openBracket) {
          bracketStack.pop();
        } else {
          bracketStack.add((OperatorSign) lastElement);
        }
      } else if (lastElement instanceof Expression) {
        stack.add((Expression) lastElement);
      }
    }

    return stack.pop();
  }

  @Override
  public void operatorParsed(OperatorSign operatorSign) {
    if (existHighOperatorSign(operatorSign)) {
      OperatorSign lastElement = operatorCollection.getLastElementAndRemove();
      numberCollection.add(lastElement);
      operatorCollection.add(operatorSign);
      return;
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
    OperatorSign openBracket = operatorCollection.getLastElement();
    numberCollection.add(openBracket);
    operatorCollection.removeLast();
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

  private void addStack(OperatorSign operatorSign) {
    Expression right = stack.pop();
    Expression left = stack.pop();
    Expression result = calculate.one(left, right, operatorSign);
    stack.add(result);
  }


}
