package com.gmail.podkutin.dmitry.salesbookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SalesBook {

    @XmlAttribute(name = "xmlns:xsi")
    private  String attribute;

    @XmlElement(name = "Документ")
    private Document document;

    public SalesBook(String attribute, Document document) {
        this.attribute = attribute;
        this.document = document;
    }
}

