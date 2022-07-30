package com.string.calculator.output;

import com.string.calculator.Setting;
import com.string.calculator.format.Category;

public class OutputFactory {

  private final Setting setting;

  public OutputFactory(Setting setting) {
    this.setting = setting;
  }

  public Output create(String outputFormat, Category category) {

    int input = Integer.parseInt(setting.getOutput());
    if (isConsoleOutput(input)) {
      return new ConsoleOutput(outputFormat);
    }

    if (isFileOutput(input)) {
      return new FileOutput(outputFormat);
    }

    return new WebOutput(outputFormat, category, setting.getArgs());

  }

  private boolean isFileOutput(int positionInput) {
    return positionInput == 2;
  }

  private boolean isConsoleOutput(int positionInput) {
    return positionInput == 1;
  }

}
