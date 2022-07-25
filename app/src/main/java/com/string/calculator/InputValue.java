package com.string.calculator;

public class InputValue {

  private String value;

  public static InputValue getInstance() {
    return InnerInstanceClass.instance;
  }

  public synchronized String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }

  private InputValue() {
  }

  private static class InnerInstanceClass {

    private static final InputValue instance = new InputValue();
  }
}
