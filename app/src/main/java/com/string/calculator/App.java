/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package com.string.calculator;

import java.util.Scanner;

public class App {

  // 필드로 상태를 가지면서 해봐라 그 중에 스태틱으로 할때와 안할때의 차이점
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    System.out.print("num : ");
    String input = scanner.nextLine();

    Run run = new Run();
    String result = run.calculate(input);

    System.out.println(result);
  }

}
