package com.string.calculator;

import java.util.HashMap;
import java.util.Map;

public enum Operator {

  plus('+', 0),
  divide('/', 1),
  multiply('*', 1),
  openBracket('(', 2),
  closeBracket(')', 2),
  modular('%', 1);

  private final char sign;
  private final int priority;

  Operator(char sign, int priority) {
    this.sign = sign;
    this.priority = priority;
  }

  public char getSign() {
    return sign;
  }

  public int getPriority() {
    return priority;
  }

  public static final Map<Character, Operator> map = new HashMap<>();

  static {
    for (Operator o : Operator.values()) {
      map.put(o.getSign(), o);
    }
  }

  public static boolean isSupportedOperator(char c) {
    return map.containsKey(c);
  }

  public static Operator valueOf(char c) {
    return map.get(c);
  }

}
