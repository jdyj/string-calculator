package com.string.calculator;

public class CalculationProcess {

  private final StringBuilder stringBuilder = new StringBuilder();

  public CalculationProcess appendAndSpacing(String value) {
    stringBuilder.append(value).append(" ");
    return this;
  }

  public CalculationProcess appendAndSpacing(char value) {
    stringBuilder.append(value).append(" ");
    return this;
  }

  public CalculationProcess append(String value) {
    stringBuilder.append(value);
    return this;
  }

  public void appendln() {
    stringBuilder.append(System.getProperty("line.separator"));
  }

  @Override
  public String toString() {
    return stringBuilder.toString();
  }

}
