package com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.result;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class ResultBuyerBook {

    @XmlAttribute(name = "xmlns:xsi")
    private  String attribute;

    @XmlElement(name = "Документ")
    private ResultBuyerDocument document;

    public ResultBuyerBook(ResultBuyerDocument document) {
        this.document = document;
    }
}

