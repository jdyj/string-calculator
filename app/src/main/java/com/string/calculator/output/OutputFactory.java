package com.string.calculator.output;

public class OutputFactory {

  public Output create(String result, Category category) {
    if (category == Category.JSON) {
      return new JsonOutput(result);
    }

    if (category == Category.XML) {
      return new XmlOutput(result);
    }

    return new PlainOutput(result);

  }

}
