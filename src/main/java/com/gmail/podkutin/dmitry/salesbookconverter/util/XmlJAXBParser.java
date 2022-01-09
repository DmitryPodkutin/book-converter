package com.gmail.podkutin.dmitry.salesbookconverter.util;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class XmlJAXBParser {

    private final Marshaller marshaller;
    private final Unmarshaller unmarshaller;

    public XmlJAXBParser(Class<?>... classesToBeBound) {
        try {
            JAXBContext ctx = JAXBContext.newInstance(classesToBeBound);
            marshaller = ctx.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1251");
            unmarshaller = ctx.createUnmarshaller();
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T unmarshall(File file) {
        try {
            return ((T) unmarshaller.unmarshal(file));
        } catch (JAXBException | ClassCastException e) {
            throw new IllegalStateException(e);
        }
    }

    public void marshall(Object instance, File file) {
        try {
            marshaller.marshal(instance, file);
        } catch (JAXBException e) {
            throw new IllegalStateException(e);
        }
    }
}