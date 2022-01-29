package com.gmail.podkutin.dmitry.bookconverter.util;

import com.gmail.podkutin.dmitry.bookconverter.util.exception.ValidationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class XmlUtil {

    public static Document getDocumentObjectModel(File xmlFile) {
        //check xmlFile - xml or not xml
        try {
            DocumentBuilder parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            return parser.parse(xmlFile);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Schema getSchemaXsd(File schemaFile) {
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            return factory.newSchema(schemaFile);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void validate(File fileForValidate, File schemaFile) {
        try {
            Validator validator = Objects.requireNonNull(getSchemaXsd(schemaFile),"SchemaXsd not be Null").newValidator();
            validator.validate(new StreamSource(fileForValidate)); //new DOMSource(XmlUtil.getDocumentObjectModel(fileForValidate))
        } catch (SAXException | IOException e) {
            throw new ValidationException(e.getMessage());
        }
    }

    public static boolean tagIsPresentInXMLFile(Document document, String tagName) {
        return document.getElementsByTagName(tagName).getLength() != 0;
    }

}
