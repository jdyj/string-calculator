package com.string.calculator.collection;

import com.string.calculator.expression.Expression;
import com.string.calculator.operator.bi.BiOperator;

public interface StateHandler {

  void hereExpression(Expression expression);

  Expression hereBiOperator(BiOperator biOperator);

  Expression getResult();

}
