package com.string.calculator.output;

import com.string.calculator.format.Category;
import com.string.calculator.utils.Yaml;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import org.springframework.boot.SpringApplication;

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
    Map<String, Object> data = new HashMap<>();
    data.put("result", outputFormat);
    data.put("format", category.name());

    Yaml yaml = new Yaml();
    yaml.write(data);
    if (availablePort("127.0.0.1", 8080)) {
    }
    System.out.println("GET http://localhost:8080/result");
  }

  public boolean availablePort(String host, int port) {
    boolean result = true;

    try {
      (new Socket(host, port)).close();
      result = false;
    } catch (Exception e) {

    }
    return result;
  }

}
