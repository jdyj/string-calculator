package com.string.calculator.expression;

import com.string.calculator.Indexed;
import com.string.calculator.operator.bi.BiOperator;

public class BinaryExpression implements Expression {

  private final Expression left;
  private final BiOperator biOperator;
  private final Expression right;

  public BinaryExpression(Expression left, BiOperator biOperator,
      Expression right) {
    this.left = left;
    this.biOperator = biOperator;
    this.right = right;
  }

  public Expression getLeft() {
    return left;
  }

  public BiOperator getBiOperator() {
    return biOperator;
  }

  public Expression getRight() {
    return right;
  }

  @Override
  public Expression evaluate() {

    Expression left = this.left;
    Expression right = this.right;

    if (left instanceof HistoryAddingExpression) {
      left = ((HistoryAddingExpression) left).getExpression().evaluate();
    }

    if (right instanceof HistoryAddingExpression) {
      right = ((HistoryAddingExpression) right).getExpression().evaluate();
    }

    if (left instanceof UnaryExpression && right instanceof BinaryExpression) {
      return biOperator.apply((UnaryExpression) left, (BinaryExpression) right);
    } else if (left instanceof BinaryExpression && right instanceof UnaryExpression) {
      return biOperator.apply((BinaryExpression) left, (UnaryExpression) right);
    } else if (left instanceof UnaryExpression && right instanceof UnaryExpression) {
      return biOperator.apply((UnaryExpression) left, (UnaryExpression) right);
    } else if (left instanceof BinaryExpression && right instanceof BinaryExpression) {
      return biOperator.apply((BinaryExpression) left, (BinaryExpression) right);
    }

    if (left instanceof LongExpression && right instanceof LongExpression) {
      return biOperator.apply((LongExpression) left, (LongExpression) right);
    } else if (left instanceof FractionExpression && right instanceof LongExpression) {
      return biOperator.apply((FractionExpression) left, (LongExpression) right);
    } else if (left instanceof LongExpression && right instanceof FractionExpression) {
      return biOperator.apply((LongExpression) left, (FractionExpression) right);
    } else if (left instanceof FractionExpression && right instanceof FractionExpression) {
      return biOperator.apply((FractionExpression) left, (FractionExpression) right);
    }

    throw new IllegalStateException();
  }

  @Override
  public String toString() {
    return left.toString() + biOperator.toString() + right.toString();
  }

  @Override
  public Integer value() {
    return left.value();
  }

  @Override
  public int compareTo(Indexed o) {
    return this.value().compareTo(o.value());
  }
}
