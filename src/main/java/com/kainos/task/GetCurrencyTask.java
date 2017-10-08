package com.kainos.task;

import net.webservicex.Country;
import net.webservicex.CountrySoap;
import org.javatuples.Pair;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.ByteArrayInputStream;
import java.io.IOException;

public class GetCurrencyTask {

    final String country;

    public GetCurrencyTask(String country) {
        this.country = country;
    }

    public Pair<String, String> execute() {

        System.out.printf("Thread name is %s time now is %d%n", Thread.currentThread().getName(), System.currentTimeMillis());

        Country countryClient = new Country();
        CountrySoap client = countryClient.getCountrySoap();
        String xmlResult = client.getCurrencyByCountry(country);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new ByteArrayInputStream(xmlResult.getBytes()));
            XPath xpath = XPathFactory.newInstance().newXPath();
            String expression = "(/NewDataSet/Table/CurrencyCode)[1]";
            NodeList list = (NodeList) xpath.compile(expression).evaluate(document, XPathConstants.NODESET);
            return Pair.with(country, list.item(0).getFirstChild().getNodeValue());

        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            e.printStackTrace();
        }
        return Pair.with("", "");
    }
}
