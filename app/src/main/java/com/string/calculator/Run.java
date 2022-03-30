package com.string.calculator;

import com.string.calculator.calculate.OperationFactory;
import com.string.calculator.collection.NumberCollection;
import com.string.calculator.collection.OperatorCollection;
import java.util.List;
import java.util.function.Consumer;

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

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;

      parse(c, last,
          (ch) -> operatorCollection.add(OperatorSign.valueOf(ch)),
          () -> numberCollection.add(numberPiece.getNumber())
      );

      // 높은 우선순위 연산자 확인
      if (existHighOperatorSign()) {
        addNumber();
      }
    }

    return getResult();
  }

  private String getResult() {
    numberCollection.reverse();
    operatorCollection.reverse();

    while (numberCollection.size() > 1) {
      addNumber();
    }

    return numberCollection.getOne();
  }

  private void parse(Character c, boolean last,
      Consumer<Character> operationCollectionAdd,
      Runnable numberCollectionAdd) {

    if (OperatorSign.isSupportedOperator(c)) {
      operationCollectionAdd.accept(c);
    }

    if (canAddNumberToCollection(c)) {
      numberCollectionAdd.run();
    }

    if (isNumberPiece(c)) {
      numberPiece.add(c);
    }

    if (last) {
      if (numberPiece.hasNumber()) {
        numberCollection.add(numberPiece.getNumber());
      }
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
