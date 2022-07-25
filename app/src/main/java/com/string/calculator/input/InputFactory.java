package com.string.calculator.input;

import java.util.Scanner;

public class InputFactory {

  /**
   * 요구사항 : 각 입력 방법이외의 입력 방법이 들어가면 안된다.
   *
   */
  public Input create(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.println("입력 방법을 선택하세요.");
    System.out.println("1. 표준 입력, 2. 파일 입력, 3. 웹으로 입력");

    int input = scanner.nextInt();
    if (isConsoleInput(input)) {
      return new ConsoleInput();
    }

    if (isFileInput(input)) {
      return new FileInput();
    }

    return new WebInput(args);
  }

  private boolean isFileInput(int input) {
    return input == 2;
  }

  private boolean isConsoleInput(int input) {
    return input == 1;
  }

}
