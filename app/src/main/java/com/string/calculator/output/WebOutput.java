package com.string.calculator.output;

import com.string.calculator.format.Category;
import com.string.calculator.output.web.SpringBootApplication;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.yaml.snakeyaml.Yaml;

final class WebOutput implements Output {

  private final String outputFormat;
  private final Category category;
  private final String[] args;

  public WebOutput(String outputFormat, Category category, String[] args) {
    this.outputFormat = outputFormat;
    this.category = category;
    this.args = args;
  }

  @Override
  public void print() {
    String folder = System.getProperty("user.dir");
    Map<String, Object> data = new HashMap<>();
    data.put("result", outputFormat);
    data.put("format", category.name());

    Yaml yaml = new Yaml();
    try {
      FileWriter writer = new FileWriter(folder + "/application.yml");
      yaml.dump(data, writer);
    } catch (IOException e) {
      e.printStackTrace();
    }
    SpringBootApplication.main(args);
  }
}
