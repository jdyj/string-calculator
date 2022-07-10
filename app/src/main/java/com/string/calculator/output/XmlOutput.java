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
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class XmlOutput implements Output {

  private final String result;

  public XmlOutput(String result) {
    this.result = result;
  }

  @Override
  public void print() {
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

}
