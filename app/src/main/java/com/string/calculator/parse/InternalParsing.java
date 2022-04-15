package com.string.calculator.parse;

import java.util.List;
import java.util.function.BiConsumer;

final class InternalParsing {

  public void iterate(List<Character> chars, BiConsumer<Character, Boolean> elementParsed) {
    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      elementParsed.accept(c, last);
    }
  }
}
