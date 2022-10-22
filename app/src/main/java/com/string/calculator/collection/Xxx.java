package com.string.calculator.collection;

import com.string.calculator.expression.Expression;
import com.string.calculator.operator.bi.BiOperator;

public interface Xxx {

  void calculate(Expression expression);

  Expression createExpression(BiOperator biOperator);

  Expression getResult();

}
