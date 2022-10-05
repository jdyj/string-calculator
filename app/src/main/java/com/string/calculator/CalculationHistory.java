package com.string.calculator;

import com.string.calculator.collection.Calculation;
import com.string.calculator.expression.LongExpression;
import java.util.List;

public class CalculationHistory implements Calculation {

  private final StringBuilder stringBuilder = new StringBuilder();
  private Integer number = 1;

  @Override
  public String toString() {
    return stringBuilder.toString();
  }

  // 오브젝트를 없애기ㅣ 위해 -> 추상화
  // 인덱스, 표현이 필요하다
  @Override
  public void 중이야(List<Object> history) {

    history.stream().sorted();
    history.sort((o1, o2) -> {
      if (o1 instanceof LongExpression && o2 instanceof LongExpression) {
        return ((LongExpression) o1).compareTo(((LongExpression) o2));
      } else if (o1 instanceof LongExpression && o2 instanceof OperatorSign) {
        return ((LongExpression) o1).getIndex().compareTo(((OperatorSign) o2).getIndex());
      } else if (o1 instanceof OperatorSign && o2 instanceof LongExpression) {
        return ((OperatorSign) o1).getIndex().compareTo(((LongExpression) o2).getIndex());
      } else if (o1 instanceof OperatorSign && o2 instanceof OperatorSign) {
        return ((OperatorSign) o1).getIndex().compareTo(((OperatorSign) o2).getIndex());
      }
      return 0;
    });
    for (Object object : history) {
      if (object instanceof LongExpression) {
        System.out.print(((LongExpression) object).getNumber() + " ");
      } else if (object instanceof OperatorSign) {
        System.out.print(((OperatorSign) object).getOperator().getSign() + " ");
      }
    }
    System.out.println();
  }

}
