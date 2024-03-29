package com.string.calculator.format;

final class PlainFormat implements Format {

  private final String result;
  private final String calculationProcess;

  public PlainFormat(String result, String calculationProcess) {
    this.result = result;
    this.calculationProcess = calculationProcess;
  }

  @Override
  public String make() {
    if (isFraction()) {
      StringBuilder sb = new StringBuilder();
      String[] split = result.split("/");
      int numeratorLength = split[0].length();
      int denominatorLength = split[1].length();
      int barCount = Math.max(numeratorLength, denominatorLength);
      if (isMinus()) {
        split[0] = "  " + split[0].substring(1);
        numeratorLength -= 1;
        split[1] = "  " + split[1];
      }
      if (numeratorLength < denominatorLength) {
        int space = (barCount - numeratorLength) / 2;
        for (int i = 0; i < space; i++) {
          split[0] = " " + split[0];
        }
      } else if (numeratorLength > denominatorLength) {
        int space = (barCount - split[1].length()) / 2;
        for (int i = 0; i < space; i++) {
          split[1] = " " + split[1];
        }
      }
      sb.append("\n");
      sb.append(split[0]);
      sb.append("\n");
      if (isMinus()) {
        sb.append("- ");
      }
      sb.append("-".repeat(barCount));
      sb.append("\n");
      sb.append(split[1]);

      sb.append("\n");
      sb.append(calculationProcess);
      sb.append("\n");
      return sb.toString();
    }

    return result;
  }

  private boolean isFraction() {
    return result.contains("/");
  }

  private boolean isMinus() {
    return result.contains("-");
  }

}
