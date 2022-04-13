package com.string.calculator.parse;

import java.util.List;
import java.util.function.BiConsumer;

final class InternalParsing {

  public void iterate(String input, BiConsumer<Character, Boolean> elementParsed) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      elementParsed.accept(c, last);
    }
  }
}
