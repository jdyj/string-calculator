package com.string.calculator.format;

import com.string.calculator.Setting;

public class FormatFactory {

  private final Category category;

  public FormatFactory(Setting setting) {
    int formatInput = Integer.parseInt(setting.getFormat());
    this.category = getCategory(formatInput);
  }

  public Format create(String result) {
    if (category == Category.JSON) {
      return new JsonFormat(result);
    }

    if (category == Category.XML) {
      return new XmlFormat(result);
    }

    return new PlainFormat(result);
  }

  public Category getCategory() {
    return category;
  }

  private Category getCategory(int formatInput) {
    Category category = null;
    if (formatInput == 1) {
      category = Category.JSON;
    } else if (formatInput == 2) {
      category = Category.XML;
    } else if (formatInput == 3) {
      category = Category.PLAIN;
    }
    return category;
  }


}
