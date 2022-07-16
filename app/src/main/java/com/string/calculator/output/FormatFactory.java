package com.string.calculator.output;

public class FormatFactory {

  public Format create(String result, Category category) {
    if (category == Category.JSON) {
      return new JsonFormat(result);
    }

    if (category == Category.XML) {
      return new XmlFormat(result);
    }

    return new PlainFormat(result);

  }

}
