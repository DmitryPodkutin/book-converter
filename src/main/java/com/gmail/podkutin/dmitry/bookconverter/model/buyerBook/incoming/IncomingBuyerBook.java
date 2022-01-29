package com.gmail.podkutin.dmitry.bookconverter.model.buyerBook.incoming;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;

@XmlRootElement(name = "Файл")
@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class IncomingBuyerBook {

    @XmlAttribute(name = "xmlns:xsi")
    private  String attribute;

    @XmlElement(name = "Документ")
    private IncomingBuyerDocument document;
}

