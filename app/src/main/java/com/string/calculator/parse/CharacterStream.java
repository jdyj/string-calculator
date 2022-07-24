package com.string.calculator.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

final class CharacterStream {

  private final List<Character> chars;

  public Stream<Character> getStream() {
    return chars.stream();
  }

  public CharacterStream(List<Character> chars) {
    this.chars = new ArrayList<>(chars);
  }

  public void forEachLastAware(BiConsumer<Character, Boolean> elementParsed) {
    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      elementParsed.accept(c, last);
    }
  }
}
