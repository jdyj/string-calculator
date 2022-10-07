package com.string.calculator.operator.bi;

import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.FractionExpression;
import com.string.calculator.expression.LongExpression;
import com.string.calculator.expression.UnaryExpression;

public interface BiOperator {

  Expression apply(LongExpression left, LongExpression right);

  Expression apply(LongExpression left, FractionExpression right);

  Expression apply(FractionExpression left, LongExpression right);

  Expression apply(FractionExpression left, FractionExpression right);

  Expression apply(UnaryExpression left, UnaryExpression right);

  Expression apply(UnaryExpression left, BinaryExpression right);

  Expression apply(BinaryExpression left, UnaryExpression right);

  Expression apply(BinaryExpression left, BinaryExpression right);

}
