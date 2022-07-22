package com.string.calculator.output;

final class ConsoleOutput implements Output {

  private final String outputFormat;

  public ConsoleOutput(String outputFormat) {
    this.outputFormat = outputFormat;
  }

  @Override
  public void print() {
    System.out.println("num : " + outputFormat);
  }
}
