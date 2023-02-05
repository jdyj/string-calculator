package com.string.calculator.format;

import com.string.calculator.Setting;

public class FormatFactory {

  private final Category category;
  private final Setting setting;

  public FormatFactory(Setting setting) {
    int formatInput = Integer.parseInt(setting.getFormat());
    this.category = getCategory(formatInput);
    this.setting = setting;
  }

  public Format create(String result, String calculationProcess) {
    String process = calculationProcess;

    if (!setting.onCalculationProcess()) {
      process = null;
    }

    if (category == Category.JSON) {
      return new JsonFormat(result, process);
    }

    if (category == Category.XML) {
      return new XmlFormat(result, process);
    }

    return new PlainFormat(result, process);
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
