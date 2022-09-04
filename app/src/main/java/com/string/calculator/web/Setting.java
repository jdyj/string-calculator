package com.string.calculator.web;

import org.springframework.stereotype.Component;

@Component
public class Setting {

  private final String[] args;

  public Setting() {
    this.args = new String[]{};
  }

  public String getInput() {
    return args[0];
  }

  public String getFormat() {
    return args[1];
  }

  public String getOutput() {
    return args[2];
  }

  public boolean onCalculationProcess() {
    return args[3].equals("1");
  }

  public String[] getArgs() {
    return args;
  }

}
