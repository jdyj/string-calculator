package com.string.calculator;

import com.string.calculator.collection.Calculation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

public class CalculationHistory implements Calculation {

  private final StringBuilder stringBuilder = new StringBuilder();
  private final Stack<Indexed> bracketStack = new Stack<>();
  private final List<Indexed> history = new ArrayList<>();

  @Override
  public String toString() {
    return stringBuilder.toString();
  }

  // 오브젝트를 없애기ㅣ 위해 -> 추상화
  // 인덱스, 표현이 필요하다
  @Override
  public void 피연산자계산중이야(List<Indexed> history) {
//    List<Indexed> indexeds = new ArrayList<>(history);
    history.addAll(bracketStack);
    Collections.sort(history);
    for (Indexed indexed : history) {
      System.out.print(indexed + " ");
    }
    System.out.println();
  }

  @Override
  public void 괄호계산중이야(OperatorSign bracket) {
    if (bracket.getOperator() == Operator.openBracket) {
      bracketStack.pop();
    } else {
      bracketStack.add(bracket);
    }
  }
}
