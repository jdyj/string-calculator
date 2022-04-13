package com.string.calculator.parse;

final class NumberPiece {

  private final StringBuilder stringBuilder = new StringBuilder();

  public void add(Character numberPiece) {
    stringBuilder.append(numberPiece);
  }

  public String getNumber() {
    return stringBuilder.toString();
  }

  public void makeEmpty() {
    stringBuilder.setLength(0);
  }

  public boolean hasNumber() {
    return !stringBuilder.isEmpty();
  }

  public int size() {
    return stringBuilder.length();
  }


}
