package com.string.calculator.input;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FilenameFilter;
import java.io.IOException;

final class FileInput implements Input {

  @Override
  public String enter() {
    try {
      FilenameFilter filter = (dir, name) -> name.startsWith("input");
      String folder = System.getProperty("user.dir");

      File file = new File(folder);
      File[] files = file.listFiles(filter);

      BufferedReader bufferedReader = new BufferedReader(new FileReader(files[0]));
      return bufferedReader.readLine();
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalStateException("파일이 존재하지 않습니다.");
    }
  }
}
