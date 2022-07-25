package com.string.calculator.input;

import java.util.Scanner;

final class ConsoleInput implements Input {

  @Override
  public String enter() {
    Scanner scanner = new Scanner(System.in);
    System.out.print("num : ");
    String input = "";
    if (scanner.hasNextLine()) {
      input = scanner.nextLine();
    }

    return input;
  }
}
