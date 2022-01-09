package com.gmail.podkutin.dmitry.salesbookconverter.util;

import com.gmail.podkutin.dmitry.salesbookconverter.util.exception.ValidationException;
import org.glassfish.jaxb.core.v2.TODO;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;

public class XmlUtil {

    public static Document getDocumentObjectModel(File xmlFile) {
        //check xmlFile - xml or not xml
        DocumentBuilder parser;
        Document document = null;
        try {
            parser = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            document = parser.parse(xmlFile);
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
        return document;
    }

    public static Schema getSchemaXsd(File schemaFile) {
        Schema schema = null;
        SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            schema = factory.newSchema(schemaFile);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return schema;
    }

    public static void validate(File fileForValidate, File schemaFile) {
        try {
            Validator validator = getSchemaXsd(schemaFile).newValidator();
            validator.validate(new StreamSource(fileForValidate)); //new DOMSource(XmlUtil.getDocumentObjectModel(fileForValidate))
        } catch (SAXException | IOException e) {
            throw new ValidationException(e.getMessage());
        }
    }
}
