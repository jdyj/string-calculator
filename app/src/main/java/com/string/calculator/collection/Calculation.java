package com.string.calculator.collection;

import com.string.calculator.Indexed;
import com.string.calculator.OperatorSign;
import java.util.List;

public interface Calculation {

  void 피연산자계산중이야(List<Indexed> history);

  void 괄호계산중이야(OperatorSign bracket);

}
