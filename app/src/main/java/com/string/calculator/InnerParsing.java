package com.string.calculator;

import java.util.List;
import java.util.function.BiConsumer;

public class InnerParsing {

  public void iterate(String input, BiConsumer<Character, Boolean> forloop) {
    List<Character> chars = input.chars()
        .mapToObj(c -> (char) c)
        .toList();

    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      forloop.accept(c, last);
    }
  }
}
