package com.string.calculator.output.web;

import org.springframework.boot.SpringApplication;

@org.springframework.boot.autoconfigure.SpringBootApplication
public class SpringBootApplication {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootApplication.class, args);
    System.out.println("http://localhost:8080/result");
  }


}
