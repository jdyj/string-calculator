package com.string.calculator.expression;

import com.string.calculator.Indexed;
import com.string.calculator.operator.bi.BiOperator;
import com.string.calculator.operator.bi.MultiplyOperator;
import com.string.calculator.operator.bi.PlusOperator;
import com.string.calculator.operator.unary.BracketOperator;

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

  public static void main(String[] args) {

    Expression expression = new BinaryExpression(new LongExpression(2L, 0), new PlusOperator(),
        new LongExpression(3L, 1));

    Expression expression2 = new BinaryExpression(new LongExpression(3L, 2), new PlusOperator(),
        new LongExpression(7L, 3));

    Expression unaryExpression = new UnaryExpression(expression, new BracketOperator());

    Expression expression3 = new BinaryExpression(unaryExpression, new MultiplyOperator(),
        expression2);

    System.out.println(expression3.evaluate());
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
