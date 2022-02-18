package com.string.calculator;

import java.util.List;
import java.util.ListIterator;


public class Sign {

  public boolean decision(List<Integer> left, List<Integer> right) {
    ListIterator<Integer> leftIter = left.listIterator();
    ListIterator<Integer> rightIter = right.listIterator();
    if (left.size() > right.size()) {
      return true;
    }
    if (left.size() < right.size()) {
      return false;
    }

    while (leftIter.hasPrevious() && rightIter.hasPrevious()) {
      Integer leftValue = leftIter.previous();
      Integer rightValue = rightIter.previous();
      if (leftValue > rightValue) {
        return true;
      }
      if (leftValue < rightValue) {
        return false;
      }
    }
    return true;
  }

}
