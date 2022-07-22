package com.string.calculator.format;

import java.util.Scanner;

public class FormatFactory {

  private final Category category;

  public FormatFactory() {
    System.out.println("출력 포맷을 선택하세요.");
    System.out.println("1. json, 2. xml, 3. plain-text");
    Scanner scanner = new Scanner(System.in);

    int formatInput = scanner.nextInt();
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
