package com.string.calculator.input;

import com.string.calculator.Setting;

public class InputFactory {

  private final Setting setting;

  public InputFactory(Setting setting) {
    this.setting = setting;
  }

  /**
   * 요구사항 : 각 입력 방법이외의 입력 방법이 들어가면 안된다.
   */
  public Input create() {

    int input = Integer.parseInt(setting.getInput());
    if (isConsoleInput(input)) {
      return new ConsoleInput();
    }

    if (isFileInput(input)) {
      return new FileInput();
    }

    return new WebInput(setting.getArgs());
  }

  private boolean isFileInput(int input) {
    return input == 2;
  }

  private boolean isConsoleInput(int input) {
    return input == 1;
  }

}
