package com.string.calculator;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

class OutputFormatTests {

  OutputFormat outputFormat;

  @BeforeEach
  void init() {
    outputFormat = new OutputFormat("3908233437982773353/250000");
  }

  @Test
  void jsonTest() {
    outputFormat.json();
    try {
      Object parse = new JSONParser().parse(
          new FileReader("/Users/jojaeyeong/Desktop/result.json"));

      JSONObject jsonObject = (JSONObject) parse;
      String result = jsonObject.get("result").toString();
      assertEquals("3908233437982773353/250000", result);
    } catch (IOException | ParseException e) {
      e.printStackTrace();
    }
  }

  @Test
  void xmlTest() {
    outputFormat.xml();
    try {
      DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
      DocumentBuilder builder = factory.newDocumentBuilder();
      Document document = builder.parse(new File("/Users/jojaeyeong/Desktop/result.xml"));
      NodeList nodeList = document.getElementsByTagName("result");

      assertEquals("3908233437982773353/250000", nodeList.item(0).getTextContent());
    } catch (IOException | ParserConfigurationException | SAXException e) {
      e.printStackTrace();
    }
  }


}