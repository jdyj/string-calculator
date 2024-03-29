package com.string.calculator.runner;

import com.string.calculator.CalculationHistory;
import com.string.calculator.Calculator;
import com.string.calculator.ExpressionToString;
import com.string.calculator.Setting;
import com.string.calculator.collection.Calculation;
import com.string.calculator.calculate.Calculate;
import com.string.calculator.expression.Expression;
import com.string.calculator.expression.HistoryAddingExpressionFactory;
import com.string.calculator.format.FormatFactory;
import com.string.calculator.input.InputFactory;
import com.string.calculator.output.Output;
import com.string.calculator.output.OutputFactory;

public class ApplicationRunner {

  private final Setting setting;

  public ApplicationRunner(Setting setting) {
    this.setting = setting;
  }

  public void runApplication() {
    if (isWebInput()) {
      return;
    }

    InputFactory inputFactory = new InputFactory(setting);
    String value = inputFactory.create().enter();

    Calculation calculation = new CalculationHistory();
    Calculator calculator = new Calculator(new HistoryAddingExpressionFactory(calculation),
        new Calculate());
    Expression expression = calculator.calculate(value);

    ExpressionToString expressionToString = new ExpressionToString(expression);
    String result = expressionToString.toString();

    FormatFactory formatFactory = new FormatFactory(setting);
    String outputFormat = formatFactory.create(result, "").make();

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
