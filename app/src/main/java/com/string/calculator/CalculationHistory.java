package com.string.calculator;

import com.string.calculator.collection.Calculation;
import java.util.Collections;
import java.util.List;

public class CalculationHistory implements Calculation {

  private final StringBuilder stringBuilder = new StringBuilder();

  @Override
  public String toString() {
    return stringBuilder.toString();
  }

  // 오브젝트를 없애기ㅣ 위해 -> 추상화
  // 인덱스, 표현이 필요하다
  @Override
  public void 중이야(List<Indexed> history) {
    Collections.sort(history);
    for (Indexed indexed : history) {
      stringBuilder.append(indexed).append(" ");
    }
    stringBuilder.append("\n");
  }

}
