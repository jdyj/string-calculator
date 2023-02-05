package com.string.calculator;

import com.string.calculator.collection.Calculation;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class CalculationGetter implements Calculation {

  private List<String> history = new ArrayList<>();

  public List<String> getHistory() {
    return history;
  }

  @Override
  public void 피연산자계산중이야(List<Indexed> history) {

    // 여기에 정렬 넣으려고 했음
    // 근데 비즈니스 로직을 넣는게 됨
    // 애초에 넘어올 때 정렬이 돼서 넘어와야 함
    // 대박이네요 ...
    // 충격 ...

    this.history.add(history.stream()
        .map(String::valueOf)
        .collect(Collectors.joining(" ")));
  }

  @Override
  public void 괄호계산중이야(OperatorSign bracket) {

  }
}
