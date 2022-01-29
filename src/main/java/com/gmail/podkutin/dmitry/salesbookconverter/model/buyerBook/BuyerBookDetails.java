package com.gmail.podkutin.dmitry.salesbookconverter.model.buyerBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class BuyerBookDetails {

    @XmlAttribute(name = "Период")
    private String period;

    @XmlAttribute(name = "ОтчетГод")
    private String fiscalYear;

    @XmlElement(name = "Всего")
    private BuyerTotal total;
}
