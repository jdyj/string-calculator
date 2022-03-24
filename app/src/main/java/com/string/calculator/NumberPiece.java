package com.string.calculator;

public class NumberPiece {

  private final StringBuilder stringBuilder = new StringBuilder();

  public void add(Character numberPiece) {
    stringBuilder.append(numberPiece);
  }

  public String getNumber() {
    String number = stringBuilder.toString();
    stringBuilder.setLength(0);
    return number;
  }

  public boolean hasNumber() {
    return !stringBuilder.isEmpty();
  }

  public int size() {
    return stringBuilder.length();
  }


}
