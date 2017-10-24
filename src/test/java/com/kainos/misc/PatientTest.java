package com.kainos.misc;

import org.junit.Before;
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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

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

        String expression = "(/Envelope/Body/migResponse/serviceContent/ehrExtractResponsev2-0/content/structured/openHealthRecord/adminDomain/person/forenames)";
        Stream<Node> nodeStream = getNodeStreamFromXpath(document, expression);
        List<Person> people = nodeStream.map(this::getPersonFromNode).collect(Collectors.toList());
        people.forEach(p -> System.out.println(p.getForename()));
        assertEquals(12, people.size());
    }

    public Person getPersonFromNode(Node node) {
        return new Person("", node.getFirstChild().getNodeValue(), "");
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
