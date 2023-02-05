package com.string.calculator.expression;

import com.string.calculator.Indexed;

// 얘를 만들려면
// 상태저장, 우선순위 구분,
// Bi, Long, 상황에 맞는 Expression
public interface Expression extends Indexed {

  Expression evaluate();

}
