package com.string.calculator.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Stream;

// 이름 고민
public class CharacterStream {

  private final List<Character> chars;

  public Stream<Character> getStream() {
    return chars.stream();
  }

  public CharacterStream(List<Character> chars) {
    // 리스트 참조만 가져와서 외부에서 바뀌면 여기도 바뀜
    // 주의할 것
    this.chars = new ArrayList<>(chars);
  }

  // 이름 고민
  public void forEachLastAware(BiConsumer<Character, Boolean> elementParsed) {
    for (int i = 0; i < chars.size(); i++) {
      Character c = chars.get(i);
      boolean last = i == chars.size() - 1;
      elementParsed.accept(c, last);
    }
  }
}
