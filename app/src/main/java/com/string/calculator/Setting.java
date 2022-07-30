package com.string.calculator;

public class Setting {

  private final String[] args;

  public Setting(String[] args) {
    this.args = args;
  }

  public String getInputValue() {
    return args[0];
  }

  public String getFormat() {
    return args[1];
  }

  public String getOutput() {
    return args[2];
  }

  public String[] getArgs() {
    return args;
  }


}
