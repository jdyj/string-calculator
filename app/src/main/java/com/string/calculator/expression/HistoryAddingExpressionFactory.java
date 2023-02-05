package com.string.calculator.expression;

import com.string.calculator.collection.Calculation;

public class HistoryAddingExpressionFactory implements ExpressionFactory {

  private final Calculation calculation;

  public HistoryAddingExpressionFactory(Calculation calculation) {
    this.calculation = calculation;
  }

  @Override
  public Expression create(Expression expression) {
    return new HistoryAddingExpression(calculation, expression);
  }
}
