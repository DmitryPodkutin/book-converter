package com.gmail.podkutin.dmitry.bookconverter.model.buyerBook;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class BuyerTotal {

    @XmlAttribute(name = "СтТовУчНалВсего")
    private String totalWithNds;

    @XmlAttribute(name = "СумНДСВыч")
    private String totalNds;

    @XmlElement(name = "ВтЧисле")
    IncludingTotal includingTotal;

    public BuyerTotal(String totalNds) {
        this.totalNds = totalNds;
    }

    @XmlAccessorType(XmlAccessType.FIELD)
    @Data
    public static class IncludingTotal {
        @XmlAttribute(name = "СтТовБезНДС")
        String  valueOfGoodsExcludingTax;
    }
}
