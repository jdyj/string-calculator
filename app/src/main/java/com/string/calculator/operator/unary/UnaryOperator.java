package com.string.calculator.operator.unary;

import com.string.calculator.expression.Expression;

public interface UnaryOperator {

  Expression apply(Expression expression);

}
