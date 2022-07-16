package com.string.calculator.output.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final String result;

  public Controller(@Value("${result}") String result) {
    this.result = result;
  }

  @GetMapping("/result")
  public String result() {
    return result;
  }


}
