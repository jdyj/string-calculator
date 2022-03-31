package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;
import com.string.calculator.collection.NumberCollection;
import com.string.calculator.collection.OperatorCollection;
import java.util.List;

/**
 * 얘는 딱 인풋을 받으면 숫자들은 숫자 스택에, 연산자는 연산자스택에 넣어주는 역할만 하고싶은데 <-- 이것도 책임이 많은편인건가 내가 설계한 계산기 특성상 높은 우선순위의
 * 연산자가 있으면 연산을 해줘야 하는 상황....
 */
public class Run {

  private final NumberCollection numberCollection = new NumberCollection();
  private final OperatorCollection operatorCollection = new OperatorCollection();
  private final NumberPiece numberPiece = new NumberPiece();
  private final Calculate calculate;

  public Run() {
    this.calculate = new Calculate(new OperationFactory());
  }

  public String calculate(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (Character c : chars) {
      parse(c);

      // 우선순위 연산자 탐색
      if (existHighOperatorSign()) {
        addNumber();
      }
    }
    checkLast();
    return getResult();
  }

  private void checkLast() {

    // 파싱 - 피연산자
    if (numberPiece.hasNumber()) {
      numberCollection.add(numberPiece.getNumber());
    }

  }

  private String getResult() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.size() > 1) {
      addNumber();
    }

    return numberCollection.getOne();
  }

  private void parse(Character c) {
    // 파싱 - 연산자
    if (OperatorSign.isSupportedOperator(c)) {
      operatorCollection.add(OperatorSign.valueOf(c));
    }

    // 파싱 - 피연산자
    if (canAddNumberToCollection(c)) {
      numberCollection.add(numberPiece.getNumber());
    }

    // 파싱 - 피연산자
    if (isNumberPiece(c)) {
      numberPiece.add(c);
    }
  }

  private boolean isNumberPiece(Character c) {
    return c >= '0' && c <= '9';
  }

  private void addNumber() {
    String leftValue = numberCollection.getOne();
    String rightValue = numberCollection.getOne();
    OperatorSign operatorSign = operatorCollection.getOne();
    String result = calculate.one(leftValue, rightValue, operatorSign);
    numberCollection.add(result);
  }

  private boolean existHighOperatorSign() {
    if (operatorCollection.isEmpty()) {
      return false;
    }

    // 빠져야 하는 코드인데 현재 코드를 고치면 안됨...
    if (operatorCollection.size() >= numberCollection.size()) {
      return false;
    }

    OperatorSign lastOperator = operatorCollection.peek();
    return lastOperator == OperatorSign.divide || lastOperator == OperatorSign.multiply;
  }

  private boolean canAddNumberToCollection(char c) {
    return c == ' ' && numberPiece.hasNumber();
  }


}
