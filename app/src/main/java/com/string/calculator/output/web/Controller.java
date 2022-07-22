package com.string.calculator.output.web;

import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

  private final String result;
  private final String format;

  public Controller(@Value("${result}") String result, @Value("${format}") String format) {
    this.result = result;
    this.format = format;
  }

  @GetMapping(value = "/result")
  public ResponseEntity<String> resultJson() {
    HttpHeaders responseHeaders = new HttpHeaders();
    if (format.equals("PLAIN")) {
      responseHeaders.add("Content-Type", "text/html; charset=utf-8");
    } else if (format.equals("JSON")) {
      responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
    } else if (format.equals("XML")) {
      responseHeaders.add("Content-Type", "application/xml; charset=UTF-8");
    }
    return new ResponseEntity<>(result, responseHeaders, HttpStatus.OK);
  }


}
