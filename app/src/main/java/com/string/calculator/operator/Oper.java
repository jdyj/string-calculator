package com.string.calculator.operator;

import java.util.HashMap;
import java.util.Map;

public enum Oper {

  plus('+'),
  minus('-'),
  open('('),
  close(')'),
  divide('/'),
  multiply('*');

  private final char sign;

  Oper(char sign) {
    this.sign = sign;
  }

  public char getSign() {
    return sign;
  }

  public static final Map<Character, Oper> map = new HashMap<>();

  static {
    for (Oper o : Oper.values()) {
      map.put(o.getSign(), o);
    }
  }

  public static boolean findOperator(char c) {
    return map.get(c) != null;
  }

}
