package com.string.calculator.output;


import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

final class FileOutput implements Output {

  private final String outputFormat;

  public FileOutput(String outputFormat) {
    this.outputFormat = outputFormat;
  }

  @Override
  public void print() {
    String folder = System.getProperty("user.dir");
    File file = new File(folder + "/output.txt");

    try {
      if (!file.exists()) {
        file.createNewFile();
      }

      FileWriter fileWriter = new FileWriter(file);
      BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

      bufferedWriter.write(outputFormat);

      bufferedWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }


  }
}
