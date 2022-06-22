package com.string.calculator;

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

public class OutputFormat {

  private final String result;

  public OutputFormat(String result) {
    this.result = result;
  }

  public void json() {
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

  public void xml() {
    DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
    try {
      DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();

      Document document = documentBuilder.newDocument();
      Element resultElement = document.createElement("result");
      document.appendChild(resultElement);
      resultElement.setTextContent(result);

      FileWriter fileWriter = new FileWriter("/Users/jojaeyeong/Desktop/result.xml");

      TransformerFactory transformerFactory = TransformerFactory.newInstance();
      Transformer transformer = transformerFactory.newTransformer();
      transformer.setOutputProperty(OutputKeys.INDENT, "yes");
      DOMSource source = new DOMSource(document);
      StreamResult streamResult = new StreamResult(fileWriter);

      transformer.transform(source, streamResult);

    } catch (IOException | ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
  }

  public void plainText() {
    System.out.println(result);
  }

}
