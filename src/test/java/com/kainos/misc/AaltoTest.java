package com.kainos.misc;

import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class AaltoTest  {

    @Test
    public void testAalto() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException{

        File xmlFile = new File("./src/test/resources/mig-patient-trace.xml");
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(xmlFile);

        XPath xpath = XPathFactory.newInstance().newXPath();
        String expression = "(/Envelope/Body/migResponse/serviceContent/extendedTraceResponse/serviceProfiles/values/value)";
        NodeList list = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        Stream<Node> nodeStream = IntStream.range(0, list.getLength()).mapToObj(list::item);
        nodeStream.forEach(n -> System.out.println(n.getFirstChild().getNodeValue()));
        assertEquals(list.getLength(), 12);
    }
}
