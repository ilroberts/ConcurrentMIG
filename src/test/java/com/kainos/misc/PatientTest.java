package com.kainos.misc;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PatientTest {

    private Document document;

    @Before
    public void init() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {
        document = getDocumentFromFile("./src/test/resources/4853379371-investigations.xml");
    }

    @Test
    public void testRetrievePatient() throws  XPathExpressionException {

        String expression = "(/Envelope/Body/migResponse/serviceContent/ehrExtractResponsev2-0/patient/id)";
        Optional<Node> patientNode = getNodeStreamFromXpath(document, expression).findFirst();
        String patientId = patientNode.map(n -> n.getFirstChild().getNodeValue()).orElse("");
        assertEquals("118fd62d-98f4-4b30-b324-99c00b7de212", patientId);
    }

    @Test
    public void testRetrievePeople() throws XPathExpressionException, ParserConfigurationException, SAXException, IOException {

        String expression = "(/Envelope/Body/migResponse/serviceContent/ehrExtractResponsev2-0/content/structured/openHealthRecord/adminDomain/person)";

        Stream<Node> nodes = getNodeStreamFromXpath(document, expression);
        Stream<Node> elementNodes = nodes.filter(n -> n.getNodeType() == Node.ELEMENT_NODE);
        elementNodes.forEach(e -> {
            Element element = (Element) e;

            Optional<String> id = getTextFromChildElement(element, "q1:id");
            System.out.println("id = " + id.orElse(""));

            NodeList forenames = ((Element) e).getElementsByTagName("q1:forenames");
            Element forenameElement = (Element) forenames.item(0);
            NodeList textFNList = forenameElement.getChildNodes();

            System.out.println("Forenames : " +
                    ((Node)textFNList.item(0)).getNodeValue().trim());

            NodeList surname = ((Element) e).getElementsByTagName("q1:surname");
            Element surnameElement = (Element) surname.item(0);
            NodeList textLNList = surnameElement.getChildNodes();

            Optional<String> surnameText;
            if(textLNList.getLength() == 0) {
                surnameText = Optional.empty();
            } else {
                surnameText = Optional.of(textLNList.item(0).getNodeValue().trim());
            }
            System.out.println("surname = " + surnameText.orElse(""));
        });

        assertTrue(true);
    }

    public Optional<String> getTextFromChildElement(Element parentElement, String tagName) {

        Optional<String> result;
        NodeList nodeList = parentElement.getElementsByTagName(tagName);
        Element childElement = (Element) nodeList.item(0);
        NodeList textList = childElement.getChildNodes();
        if(textList.getLength() == 0) {
            return Optional.empty();
        } else {
            return Optional.of(textList.item(0).getNodeValue().trim());
        }
    }


    public Stream<Node> getNodeStreamFromXpath(Document document, String expression) throws XPathExpressionException {

        XPath xpath = XPathFactory.newInstance().newXPath();
        NodeList list = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
        return IntStream.range(0, list.getLength()).mapToObj(list::item);
    }



    public Document getDocumentFromFile(String filename) throws ParserConfigurationException, SAXException, IOException{
        File xmlFile = new File(filename);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        return builder.parse(xmlFile);
    }
}
