package com.string.calculator;

import java.util.HashMap;
import java.util.Map;

public enum OperatorSign {

  plus('+'),
  subtract('-'),
  divide('/'),
  multiply('*'),
  openBracket('('),
  closeBracket(')');

  private final char sign;

  OperatorSign(char sign) {
    this.sign = sign;
  }

  public char getSign() {
    return sign;
  }

  public static final Map<Character, OperatorSign> map = new HashMap<>();

  static {
    for (OperatorSign o : OperatorSign.values()) {
      map.put(o.getSign(), o);
    }
  }

  public static boolean isSupportedOperator(char c) {
    return map.containsKey(c);
  }

  public static OperatorSign valueOf(char c) {
    return map.get(c);
  }

}
