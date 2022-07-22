package com.string.calculator.format;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
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

public class XmlFormat implements Format {

  private final String result;

  public XmlFormat(String result) {
    this.result = result;
  }

  @Override
  public String make() {
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
      transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
      DOMSource source = new DOMSource(document);
      StreamResult streamResult = new StreamResult(fileWriter);

      transformer.transform(source, streamResult);
      Writer out = new StringWriter();
      transformer.transform(source, new StreamResult(out));
      return out.toString();

    } catch (IOException | ParserConfigurationException | TransformerException e) {
      e.printStackTrace();
    }
    return "";
  }

}
