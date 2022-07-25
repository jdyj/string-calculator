package com.string.calculator.output;

import com.string.calculator.format.Category;
import java.util.Scanner;

public class OutputFactory {

  public Output create(String outputFormat, Category category, String[] args) {

    System.out.println("출력 위치를 선택하세요.");
    System.out.println("1. 표준 출력, 2. 파일 출력, 3. 웹으로 출력");
    Scanner scanner = new Scanner(System.in);

    int input = scanner.nextInt();
    if (isConsoleOutput(input)) {
      return new ConsoleOutput(outputFormat);
    }

    if (isFileOutput(input)) {
      return new FileOutput(outputFormat);
    }

    return new WebOutput(outputFormat, category, args);

  }

  private boolean isFileOutput(int positionInput) {
    return positionInput == 2;
  }

  private boolean isConsoleOutput(int positionInput) {
    return positionInput == 1;
  }

}
