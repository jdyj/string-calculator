package com.string.calculator.web;

import com.string.calculator.Calculator;
import com.string.calculator.Setting;
import com.string.calculator.format.FormatFactory;
import com.string.calculator.input.InputFactory;
import com.string.calculator.output.Output;
import com.string.calculator.output.OutputFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Controller {

  private final RestTemplate restTemplate;
  private final Setting setting;

  public Controller(RestTemplate restTemplate, Setting setting) {
    this.restTemplate = restTemplate;
    this.setting = setting;
  }

  @GetMapping(value = "/result")
  public ResponseEntity<String> resultJson() {
    InputFactory inputFactory = new InputFactory(setting);
    String value = inputFactory.create().enter();

    Calculator calculator = new Calculator();
    String result = calculator.calculate(value).getValue();

    FormatFactory formatFactory = new FormatFactory(setting);
    String outputFormat = formatFactory.create(result).make();

    HttpHeaders responseHeaders = new HttpHeaders();
    addContentType(formatFactory, responseHeaders);

    return new ResponseEntity<>(outputFormat, responseHeaders, HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> input(@RequestBody InputRequest inputRequest) {
    Calculator calculator = new Calculator();
    String result = calculator.calculate(inputRequest.getValue()).getValue();

    FormatFactory formatFactory = new FormatFactory(setting);
    String outputFormat = formatFactory.create(result).make();

    OutputFactory outputFactory = new OutputFactory(setting);
    Output output = outputFactory.create(outputFormat, formatFactory.getCategory());
    output.print();

    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  private void addContentType(FormatFactory formatFactory, HttpHeaders responseHeaders) {
    switch (formatFactory.getCategory()) {
      case PLAIN -> responseHeaders.add("Content-Type", "text/html; charset=utf-8");
      case JSON -> responseHeaders.add("Content-Type", "application/json; charset=UTF-8");
      case XML -> responseHeaders.add("Content-Type", "application/xml; charset=UTF-8");
    }
  }

}
