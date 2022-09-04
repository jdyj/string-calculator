package com.string.calculator.runner;

import com.string.calculator.Calculator;
import com.string.calculator.Setting;
import com.string.calculator.format.FormatFactory;
import com.string.calculator.input.InputFactory;
import com.string.calculator.output.Output;
import com.string.calculator.output.OutputFactory;
import com.string.calculator.web.SpringBootApplication;

public class ApplicationRunner {

  private final Setting setting;

  public ApplicationRunner(Setting setting) {
    this.setting = setting;
  }

  public void runApplication() {
    if (isWebInput()) {
      SpringBootApplication.main(setting.getArgs());
      return;
    }

    InputFactory inputFactory = new InputFactory(setting);
    String value = inputFactory.create().enter();

    Calculator calculator = new Calculator();
    String result = calculator.calculate(value).getValue();
    String calculationProcess = calculator.getCalculationProcess().toString();

    FormatFactory formatFactory = new FormatFactory(setting);
    String outputFormat = formatFactory.create(result, calculationProcess).make();

    OutputFactory outputFactory = new OutputFactory(setting);
    Output output = outputFactory.create(outputFormat, formatFactory.getCategory());
    output.print();

  }

  private boolean isPlainFormat() {
    return setting.getFormat().equals("3");
  }

  private boolean isXmlFormat() {
    return setting.getFormat().equals("2");
  }

  private boolean isJsonFormat() {
    return setting.getFormat().equals("1");
  }

  private boolean isWebInput() {
    return setting.getInput().equals("3");
  }

  private boolean isFileInput() {
    return setting.getInput().equals("2");
  }

  private boolean isWebOutput() {
    return setting.getInput().equals("3");
  }

  private boolean isFileOutput() {
    return setting.getOutput().equals("2");
  }

  private boolean isConsoleOutput() {
    return setting.getOutput().equals("1");
  }

  private boolean isConsoleInput() {
    return setting.getOutput().equals("1");
  }


}
