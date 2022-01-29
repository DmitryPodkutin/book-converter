package com.gmail.podkutin.dmitry.bookconverter.model.salesBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class SalesBook {

    @XmlAttribute(name = "xmlns:xsi")
    private String attribute;

    @XmlElement(name = "Документ")
    private SalesDocument salesDocument;

    public SalesBook(SalesDocument salesDocument) {
        this.attribute = "http://www.w3.org/2001/XMLSchema-instance";
        this.salesDocument = salesDocument;
    }
}

