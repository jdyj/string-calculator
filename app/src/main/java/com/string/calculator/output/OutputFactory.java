package com.string.calculator.output;

import com.string.calculator.format.Category;
import com.string.calculator.output.web.SpringBootApplication;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import org.yaml.snakeyaml.Yaml;

public class OutputFactory {

  private final String[] args;

  public OutputFactory(String[] args) {
    this.args = args;
  }

  public Output create(String outputFormat, Category category) {

    System.out.println("출력 위치를 선택하세요.");
    System.out.println("1. 표준 출력, 2. 파일 출력, 3. 웹으로 출력");
    Scanner scanner = new Scanner(System.in);

    int positionInput = scanner.nextInt();
    if (positionInput == 1) {
      return new ConsoleOutput(outputFormat);
    }

    if (positionInput == 2) {
      return new FileOutput(outputFormat);
    }

    return new WebOutput(outputFormat, category, args);

  }

}
