package com.gmail.podkutin.dmitry.salesbookconverter.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
@Data
@NoArgsConstructor
public class Total {

    @XmlAttribute(name = "СтТовУчНалВсего")
    private String totalWithNds;

    @XmlAttribute(name = "СтТовРубКоп20")
    private String costOfGoodsWithoutNds;

    @XmlAttribute(name = "СумНДСРубКоп20")
    private String totalNds;
}
