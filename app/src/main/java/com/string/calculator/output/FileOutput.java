package com.string.calculator.output;


final class FileOutput implements Output {

  private final String outputFormat;

  public FileOutput(String outputFormat) {
    this.outputFormat = outputFormat;
  }

  @Override
  public void print() {
    String folder = System.getProperty("user.dir");


  }
}
