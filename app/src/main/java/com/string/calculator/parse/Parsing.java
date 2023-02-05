package com.string.calculator.parse;

import com.string.calculator.Operator;
import com.string.calculator.OperatorSign;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.ExpressionFactory;
import com.string.calculator.expression.LongExpression;
import java.util.List;

/**
 * 1. 동작 중심으로 바뀐 인터페이스랑 파싱과 머신과 연결하기. 2. 인덱스 끼워넣기... 관심사에만 집중할수 있도록
 */
public class Parsing {

  private final ParsingHandler parsingHandler;
  private final NumberPiece numberPiece = new NumberPiece();
  private final ExpressionFactory expressionFactory;
  private int index = 0;

  public Parsing(ParsingHandler parsingHandler, ExpressionFactory expressionFactory) {
    this.parsingHandler = parsingHandler;
    this.expressionFactory = expressionFactory;
  }

  public void parse(String input) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    CharacterStream characterStream = new CharacterStream(chars);
    characterStream.forEachLastAware(this::execute);
  }

  private void execute(Character c, boolean last) {
    if (Operator.isSupportedOperator(c)) {
      if (c == Operator.closeBracket.getSign()) {
        checkNumberPiece();
        parsingHandler.closeBracketFound(new OperatorSign(Operator.closeBracket, index));
        index++;
        return;
      }

      Operator operator = Operator.valueOf(c);
      operatorParsed(operator);
      return;
    }

    if (canNumberParsed(c)) {
      numberParsed(numberPiece);
      return;
    }

    if (isNumberPiece(c)) {
      if (isMinusSign(c)) {
        operatorParsed(Operator.plus);
      }
      numberPiece.add(c);
      if (last) {
        if (numberPiece.hasNumber()) {
          numberParsed(numberPiece);
        }
      }
    }
  }

  private void operatorParsed(Operator operator) {
    checkNumberPiece();
    parsingHandler.operatorParsed(new OperatorSign(operator, index));
    index++;
  }

  private void checkNumberPiece() {
    if (numberPiece.hasNumber()) {
      numberParsed(numberPiece);
    }
  }

  private void numberParsed(NumberPiece numberPiece) {
    try {
      Expression expression =
          new LongExpression(Long.parseLong(numberPiece.getNumber()), index);
      parsingHandler.numberParsed(expression);
      index++;
    } catch (Exception e) {

    } finally {
      numberPiece.makeEmpty();
    }
  }

  private boolean isNumberPiece(Character c) {
    boolean isNumeric = (c >= '0' && c <= '9');

    return isNumeric || isMinusSign(c);
  }

  private boolean isMinusSign(Character c) {
    return c == '-';
  }

  private boolean canNumberParsed(Character c) {
    if (numberPiece.getNumber().equals("-")) {
      return false;
    }
    return c == ' ' && numberPiece.hasNumber();
  }


}

