package com.string.calculator.output;

import java.io.FileWriter;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.json.simple.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class JsonOutput implements Output {

  private final String result;

  public JsonOutput(String result) {
    this.result = result;
  }

  @Override
  public void print() {
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("result", result);

    try {
      FileWriter fileWriter = new FileWriter("/Users/jojaeyeong/Desktop/result.json");
      fileWriter.write(jsonObject.toJSONString());
      fileWriter.flush();
      fileWriter.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
