package com.string.calculator.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class Yaml {

  public void write(Map<String, Object> data) {
    String folder = System.getProperty("user.dir");
    org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
    try {
      FileWriter writer = new FileWriter(folder + "/application.yml");
      yaml.dump(data, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public String readValue() {
    String folder = System.getProperty("user.dir");
    org.yaml.snakeyaml.Yaml yaml = new org.yaml.snakeyaml.Yaml();
    try {
      InputStream inputStream = new FileInputStream(folder + "/application.yml");
      Map<String, Object> data = yaml.load(inputStream);
      return (String) data.get("value");
    } catch (FileNotFoundException e) {
      e.printStackTrace();
    }
    return "";
  }

}
