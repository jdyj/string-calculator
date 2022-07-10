package com.string.calculator.output;

public class PlainOutput implements Output {

  private final String result;

  public PlainOutput(String result) {
    this.result = result;
  }

  @Override
  public void print() {
    System.out.println("num : " + result);
  }

}
