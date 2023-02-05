package com.string.calculator.operator.bi;

import com.string.calculator.expression.BinaryExpression;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.FractionExpression;
import com.string.calculator.expression.LongExpression;
import com.string.calculator.expression.UnaryExpression;

public class MultiplyOperator implements BiOperator {


  @Override
  public Expression apply(LongExpression left, LongExpression right) {
    return new LongExpression(left.getNumber() * right.getNumber(), left.getIndex());
  }

  @Override
  public Expression apply(LongExpression left, FractionExpression right) {
    return null;
  }

  @Override
  public Expression apply(FractionExpression left, LongExpression right) {
    return null;
  }

  @Override
  public Expression apply(FractionExpression left, FractionExpression right) {
    return null;
  }

  @Override
  public Expression apply(UnaryExpression left, UnaryExpression right) {
    return applyExpression(left, right);
  }

  @Override
  public Expression apply(UnaryExpression left, BinaryExpression right) {
    return applyExpression(left, right);
  }

  @Override
  public Expression apply(BinaryExpression left, UnaryExpression right) {
    return applyExpression(left, right);
  }

  @Override
  public Expression apply(BinaryExpression left, BinaryExpression right) {
    return applyExpression(left, right);
  }

  @Override
  public String toString() {
    return "*";
  }

  private Expression applyExpression(Expression left, Expression right) {
    Expression evaluateLeft = left.evaluate();
    Expression evaluateRight = right.evaluate();
    if (evaluateLeft instanceof LongExpression && evaluateRight instanceof LongExpression) {
      return this.apply((LongExpression) evaluateLeft, (LongExpression) evaluateRight);
    } else if (evaluateLeft instanceof FractionExpression
        && evaluateRight instanceof LongExpression) {
      return this.apply((FractionExpression) evaluateLeft, (LongExpression) evaluateRight);
    } else if (evaluateLeft instanceof LongExpression
        && evaluateRight instanceof FractionExpression) {
      return this.apply((LongExpression) evaluateLeft, (FractionExpression) evaluateRight);
    } else if (evaluateLeft instanceof FractionExpression
        && evaluateRight instanceof FractionExpression) {
      return this.apply((FractionExpression) evaluateLeft, (FractionExpression) evaluateRight);
    }
    throw new IllegalStateException();
  }
}
